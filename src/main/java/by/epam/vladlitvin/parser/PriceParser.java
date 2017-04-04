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
 * Class for Tariff price parsing.
 */
class PriceParser {
    private static final String SMS_PRICE_REGEX = "SMSPrice\\{.+?}";
    private static final String СALL_PRICE_REGEX = "CallPrice\\{.+?}";
    private static final String INTERNET_PRICE_REGEX = "InternetPrice\\{.+?}";

    private static final String INTERNET = "internet";
    private static final String WITHIN_NETWORK = "withinNetwork";
    private static final String OTHER_NETWORK = "otherNetwork";
    private static final String OTHER_COUNTRY = "otherCountry";



    static SMSPrice findSMSPrice(String inPut) throws TariffParseException, ValueLessZeroException {
        Pattern pattern = Pattern.compile(SMS_PRICE_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        SMSPrice result;


            if (matcher.find()) {
                String smsPrice = matcher.group();
                BigDecimal withinNetwork = findBigDecimalParameter(smsPrice, WITHIN_NETWORK);
                BigDecimal otherNetwork = findBigDecimalParameter(smsPrice, OTHER_NETWORK);
                BigDecimal otherCountry = findBigDecimalParameter(smsPrice, OTHER_COUNTRY);
                result = new SMSPrice(withinNetwork,
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
                BigDecimal withinNetwork = findBigDecimalParameter(callPrice, WITHIN_NETWORK);
                BigDecimal otherNetwork = findBigDecimalParameter(callPrice, OTHER_NETWORK);
                BigDecimal otherCountry = findBigDecimalParameter(callPrice, OTHER_COUNTRY);
                result = new CallPrice(withinNetwork,
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
                BigDecimal internet = findBigDecimalParameter(internetPrice, INTERNET);
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
