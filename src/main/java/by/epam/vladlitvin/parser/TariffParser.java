package by.epam.vladlitvin.parser;


import by.epam.vladlitvin.entity.price.CallPrice;
import by.epam.vladlitvin.entity.price.InternetPrice;
import by.epam.vladlitvin.entity.price.SMSPrice;
import by.epam.vladlitvin.entity.tariff.AbstractTariff;
import by.epam.vladlitvin.entity.tariff.InternetTariff;
import by.epam.vladlitvin.entity.tariff.SMSTariff;
import by.epam.vladlitvin.entity.tariff.CallTariff;
import by.epam.vladlitvin.exception.TariffParseException;
import by.epam.vladlitvin.exception.ValueLessZeroException;
import by.epam.vladlitvin.factory.CallTariffFactory;
import by.epam.vladlitvin.factory.InternetTariffFactory;
import by.epam.vladlitvin.factory.SMSTariffFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static by.epam.vladlitvin.parser.UnitParser.*;
import static by.epam.vladlitvin.parser.PriceParser.*;

/**
 * Created by vlad_ on 3/27/2017.
 */
public class TariffParser {
    private final static String TARIFF_REGEX = "FOR_(SMS|CALLS|INTERNET)\\[.+?\\];";
    private final static String CALLS_TARIFF_REGEX = "FOR_CALLS\\[.+?\\];";
    private final static String SMS_TARIFF_REGEX = "FOR_SMS\\[.+?\\];";
    private final static String INTERNET_TARIFF_REGEX = "FOR_INTERNET\\[.+?\\];";
    private final static Logger LOGGER = LogManager.getLogger();

    public static ArrayList<AbstractTariff> tariffParse(String inPut){
        String line = removeSpaces(inPut);
        Pattern pattern = Pattern.compile(TARIFF_REGEX);
        Matcher matcher = pattern.matcher(line);
        ArrayList<AbstractTariff> tariffs = new ArrayList<>();

        while (matcher.find()) {
            String string = matcher.group();
            try {
                LOGGER.log(Level.DEBUG, string);
                if (string.matches(CALLS_TARIFF_REGEX)) {
                    tariffs.add(findCallTariff(string));
                } else if (string.matches(SMS_TARIFF_REGEX)) {
                    tariffs.add(findSMSTariff(string));
                } else if (string.matches(INTERNET_TARIFF_REGEX)) {
                    tariffs.add(findInternetTariff(string));
                }
            } catch (TariffParseException e) {
                LOGGER.log(Level.ERROR,"Can't find correct data for create Tariff " + e.toString());
            } catch (ValueLessZeroException e) {
                LOGGER.log(Level.ERROR,"Can't create Tariff. Number less zero. " + e.toString());
            }
        }
        return tariffs;
    }


    private static CallTariff findCallTariff(String inPut)
            throws TariffParseException, ValueLessZeroException {

        CallPrice callPrice = findCallPrice(inPut);
        SMSPrice smsPrice = findSMSPrice(inPut);
        InternetPrice internetPrice = findInternetPrice(inPut);
        String name = findTariffName(inPut);
        int freeMinutes = findFreeMinutes(inPut);
        BigDecimal subscriptionFee = findSubscriptionFee(inPut);
        CallTariffFactory factory = new CallTariffFactory();
        return factory.createInstance(callPrice, internetPrice,
                smsPrice, name, subscriptionFee, freeMinutes);

    }

    private static SMSTariff findSMSTariff(String inPut)
            throws TariffParseException, ValueLessZeroException {


        LOGGER.log(Level.DEBUG, inPut);
        CallPrice callPrice = findCallPrice(inPut);
        SMSPrice smsPrice = findSMSPrice(inPut);
        InternetPrice internetPrice = findInternetPrice(inPut);
        String name = findTariffName(inPut);
        int freeSMS = findFreeSMS(inPut);
        BigDecimal subscriptionFee = findSubscriptionFee(inPut);
        SMSTariffFactory factory = new SMSTariffFactory();
        return factory.createInstance(callPrice, internetPrice,
                smsPrice, name, subscriptionFee, freeSMS);

    }

    private static InternetTariff findInternetTariff(String inPut)
            throws TariffParseException, ValueLessZeroException {

        CallPrice callPrice = findCallPrice(inPut);
        SMSPrice smsPrice = findSMSPrice(inPut);
        InternetPrice internetPrice = findInternetPrice(inPut);
        String name = findTariffName(inPut);
        int freeMegabytes = findFreeMegabytes(inPut);
        BigDecimal subscriptionFee = findSubscriptionFee(inPut);
        InternetTariffFactory factory = new InternetTariffFactory();
        return factory.createInstance(callPrice, internetPrice,
                smsPrice, name, subscriptionFee, freeMegabytes);

    }

}

