package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.entity.price.CallPrice;
import by.epam.vladlitvin.entity.price.InternetPrice;
import by.epam.vladlitvin.entity.price.SMSPrice;
import by.epam.vladlitvin.exception.TariffParseException;
import by.epam.vladlitvin.exception.ValueLessZeroException;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static by.epam.vladlitvin.parser.UnitParser.*;

/**
 * Created by vlad_ on 3/28/2017.
 */
class PriceParser {
    private static final String SMS_PRICE_REGEX = "SMSPrice\\{.+?}";
    private static final String СALL_PRICE_REGEX = "CallPrice\\{.+?}";
    private static final String INTERNET_PRICE_REGEX = "InternetPrice\\{.+?}";

    static SMSPrice findSMSPrice(String inPut) throws TariffParseException, ValueLessZeroException {
        Pattern pattern = Pattern.compile(SMS_PRICE_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        SMSPrice result;


            if (matcher.find()) {
                String smsPrice = matcher.group();
                BigDecimal withingNetwork = findWithinNetwork(smsPrice);
                BigDecimal otherNetwork = findOtherNetwork(smsPrice);
                BigDecimal otherCountry = findOtherCountry(smsPrice);
                result = new SMSPrice(withingNetwork,
                        otherNetwork, otherCountry);
            } else {
                throw new TariffParseException(PriceParser.class + " Illegal data format in \"findSMSPrice\" method");
            }
            if (matcher.find()) {
                throw new TariffParseException(PriceParser.class + " Can be only one \"SMSPrice\" class");
            }

        return result;
    }

    static CallPrice findCallPrice(String inPut) throws TariffParseException, ValueLessZeroException {
        Pattern pattern = Pattern.compile(СALL_PRICE_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        CallPrice result;


            if (matcher.find()) {
                String callPrice = matcher.group();
                BigDecimal withingNetwork = findWithinNetwork(callPrice);
                BigDecimal otherNetwork = findOtherNetwork(callPrice);
                BigDecimal otherCountry = findOtherCountry(callPrice);
                result = new CallPrice(withingNetwork,
                        otherNetwork, otherCountry);

            } else {
                throw new TariffParseException(PriceParser.class + " Illegal data format in \"findCallPrice\" method. ");
            }
            if (matcher.find()) {
                throw new TariffParseException(PriceParser.class + "Can be only one \"CallPrice\" class. ");
            }

        return result;
    }

    static InternetPrice findInternetPrice(String inPut) throws TariffParseException, ValueLessZeroException {
        Pattern pattern = Pattern.compile(INTERNET_PRICE_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        InternetPrice result;


            if (matcher.find()) {
                String internetPrice = matcher.group();
                BigDecimal internet = findInternet(internetPrice);
                result = new InternetPrice(internet);

            } else {
                throw new TariffParseException(PriceParser.class + " Illegal data format in \"findInternetPrice\" method");
            }
            if (matcher.find()) {
                throw new TariffParseException(PriceParser.class + " Can be only one \"InternetPrice\" class");
            }

        return result;
    }
}
