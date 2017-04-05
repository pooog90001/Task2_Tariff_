package by.epam.vladlitvin.parser;


import by.epam.vladlitvin.entity.price.CallPrice;
import by.epam.vladlitvin.entity.price.InternetPrice;
import by.epam.vladlitvin.entity.price.SMSPrice;
import by.epam.vladlitvin.entity.tariff.AbstractTariff;
import by.epam.vladlitvin.entity.tariff.InternetTariff;
import by.epam.vladlitvin.entity.tariff.SMSTariff;
import by.epam.vladlitvin.entity.tariff.CallTariff;
import by.epam.vladlitvin.exception.TariffParseException;
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
 * Class for Tariff persing.
 */
public class TariffParser {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String TARIFF_REGEX = "FOR_(SMS|CALLS|INTERNET)\\[.+?\\];";
    private final static String CALLS_TARIFF_REGEX = "FOR_CALLS\\[.+?\\];";
    private final static String SMS_TARIFF_REGEX = "FOR_SMS\\[.+?\\];";
    private final static String INTERNET_TARIFF_REGEX = "FOR_INTERNET\\[.+?\\];";
    private static final String FREE_MEGABYTES = "freeMegabytes";
    private static final String FREE_MINUTES = "freeMinutes";
    private static final String FREE_SMS = "freeSMS";
    private static final String SUBSCRIPTION_FEE = "subscriptionFee";
    private static final String TARIFF_NAME = "tariffName";


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
                LOGGER.log(Level.ERROR,"Can't find correct " +
                        "data for create Tariff " + e.toString());
            }
        }
        return tariffs;
    }


    private static CallTariff findCallTariff(String inPut)
            throws TariffParseException {

        CallPrice callPrice = findCallPrice(inPut);
        SMSPrice smsPrice = findSMSPrice(inPut);
        InternetPrice internetPrice = findInternetPrice(inPut);
        String name = findStringParameter(inPut, TARIFF_NAME);
        int freeMinutes = findIntegerParameter(inPut, FREE_MINUTES);
        BigDecimal subscriptionFee = findBigDecimalParameter(inPut, SUBSCRIPTION_FEE);
        CallTariffFactory factory = new CallTariffFactory();
        return factory.createInstance(callPrice, internetPrice,
                smsPrice, name, subscriptionFee, freeMinutes);

    }

    private static SMSTariff findSMSTariff(String inPut)
            throws TariffParseException {


        LOGGER.log(Level.DEBUG, inPut);
        CallPrice callPrice = findCallPrice(inPut);
        SMSPrice smsPrice = findSMSPrice(inPut);
        InternetPrice internetPrice = findInternetPrice(inPut);
        String name = findStringParameter(inPut, TARIFF_NAME);
        int freeSMS = findIntegerParameter(inPut, FREE_SMS);
        BigDecimal subscriptionFee = findBigDecimalParameter(inPut, SUBSCRIPTION_FEE);
        SMSTariffFactory factory = new SMSTariffFactory();
        return factory.createInstance(callPrice, internetPrice,
                smsPrice, name, subscriptionFee, freeSMS);

    }

    private static InternetTariff findInternetTariff(String inPut)
            throws TariffParseException {

        CallPrice callPrice = findCallPrice(inPut);
        SMSPrice smsPrice = findSMSPrice(inPut);
        InternetPrice internetPrice = findInternetPrice(inPut);
        String name = findStringParameter(inPut, TARIFF_NAME);
        int freeMegabytes = findIntegerParameter(inPut, FREE_MEGABYTES);
        BigDecimal subscriptionFee = findBigDecimalParameter(inPut, SUBSCRIPTION_FEE);
        InternetTariffFactory factory = new InternetTariffFactory();
        return factory.createInstance(callPrice, internetPrice,
                smsPrice, name, subscriptionFee, freeMegabytes);

    }

}

