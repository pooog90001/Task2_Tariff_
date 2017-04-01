package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.exception.TariffParseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vlad_ on 3/28/2017.
 */
class UnitParser {
    private static final String INTERNET_REGEX = "internet=\".+?\";";
    private static final String WITHIN_NETWORK_REGEX = "withinNetwork=\".+?\";";
    private static final String OTHER_NETWORK_REGEX = "otherNetwork=\".+?\";";
    private static final String OTHER_COUNTRY_REGEX = "otherCountry=\".+?\";";
    private static final String FREE_MEGABYTES_REGEX = "freeMegabytes=\".+?\";";
    private static final String FREE_MINUTES_REGEX = "freeMinutes=\".+?\";";
    private static final String FREE_SMS_REGEX = "freeSMS=\".+?\";";
    private static final String SUBSCRIPTION_FEE_REGEX = "subscriptionFee=\".+?\";";
    private static final String TARIFF_NAME_REGEX = "tariffName=\".+?\";";
    private static final String CLIENT_NAME_REGEX = "clientName=\".+?\";";
    private static final String INTEGER_REGEX = "\\d{1,6}";
    private static final String BIG_DECIMAL_REGEX = "(\\d{1,6})(\\.\\d{1,6})?";
    private static final String STRING_REGEX = "\".+?\"";
    private final static String SPACE_REGEX ="\\s+";

    private final static Logger LOGGER = LogManager.getLogger();

    static String removeSpaces(String inPut) {
        String[] strings = inPut.split(SPACE_REGEX);
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    static BigDecimal findInternet(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(INTERNET_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        BigDecimal result;
        String internetPrice;
        if(matcher.find()) {
            internetPrice = matcher.group();
            result = findBigDecimal(internetPrice);
        } else {
            throw new TariffParseException(" Illegal data format in \"findInternet\" method");
        } if (matcher.find()) {
            throw new TariffParseException(" Can be only one \"internet\" parameter");
        }
        return result;
    }

    static BigDecimal findWithinNetwork(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(WITHIN_NETWORK_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        BigDecimal result;

        if(matcher.find()) {
            String withinNetworkPrice = matcher.group();
            result = findBigDecimal(withinNetworkPrice);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data format in \"findWithinNetwork\" method");
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only one \"withinNetwork\" parameter");
        }
        return result;
    }

    static BigDecimal findOtherNetwork(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(OTHER_NETWORK_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        BigDecimal result;

        if(matcher.find()) {
            String otherNetworkPrice = matcher.group();
            result = findBigDecimal(otherNetworkPrice);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data format in \"findOtherNetwork\" method");
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only one \"otherNetwork\" parameter");
        }
        return result;
    }

    static BigDecimal findOtherCountry(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(OTHER_COUNTRY_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        BigDecimal result;

        if(matcher.find()) {
            String otherCountryPrice = matcher.group();
            result = findBigDecimal(otherCountryPrice);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data format in \"findOtherCountry\" method");
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only one \"otherCountry\" parameter");
        }
        return result;
    }

    static BigDecimal findSubscriptionFee(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(SUBSCRIPTION_FEE_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        BigDecimal result;

        if(matcher.find()) {
            String subscriptionFee = matcher.group();
            result = findBigDecimal(subscriptionFee);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data format in \"findSubscriptionFee\" method");
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only one \"subscriptionFee\" parameter");
        }
        return result;
    }

    static int findFreeSMS(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(FREE_SMS_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        int result;

        if(matcher.find()) {
            String freeSMS = matcher.group();
            LOGGER.log(Level.DEBUG, freeSMS);
            result = findInteger(freeSMS);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data format in \"findFreeSMS\" method");
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only one \"freeSMS\" parameter");
        }
        return result;
    }

    static int findFreeMegabytes(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(FREE_MEGABYTES_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        int result;

        if(matcher.find()) {
            String internetPrice = matcher.group();
            result = findInteger(internetPrice);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data format in \"findFreeMinutes\" method");
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only one \"findFreeMegabytes\" parameter");
        }
        return result;
    }

    static int findFreeMinutes(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(FREE_MINUTES_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        int result;

        if(matcher.find()) {
            String internetPrice = matcher.group();
            result = findInteger(internetPrice);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data format in \"findFreeMinutes\" method");
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only one \"freeMinutes\" parameter");
        }
        return result;
    }

    static String findTariffName(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(TARIFF_NAME_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        String result;

        if(matcher.find()) {
            String name = matcher.group();
            result = findString(name);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data format in \"findTariffName\" method" );
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only one \"tariffName\" parameter");
        }
        return result;
    }

    static String findClientName(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(CLIENT_NAME_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        String result;

        if(matcher.find()) {
            String name = matcher.group();
            result = findString(name);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data format in \"findClientName\" method" );
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only one \"clientName\" parameter");
        }
        return result;
    }


    private static BigDecimal findBigDecimal(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(BIG_DECIMAL_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        BigDecimal result;

        if(matcher.find()) {
            String bigDecimal = matcher.group();
            result = new BigDecimal(bigDecimal);
        } else {
            throw new TariffParseException(UnitParser.class
                    + " Illegal data format in \"findBigDecimal\" method");
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class
                    + "\"findBigDecimal\" method. Can be only one real number in quotes");
        }
        return result;
    }

    private static int findInteger(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(INTEGER_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        int result;

        if(matcher.find()) {
            String integer = matcher.group();
            LOGGER.log(Level.DEBUG, integer);
            result = Integer.parseInt(integer);
        } else {
            throw new TariffParseException(UnitParser.class
                    + " Illegal data format in \"findInteger\" method" );
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class
                    + " \"findInteger\" method. Can be only one integer number in quotes");
        }
        return result;
    }

    private static String findString(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(STRING_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        StringBuilder result = new StringBuilder();

        if(matcher.find()) {
            String string = matcher.group();
            string = string.substring(1, string.length()-1); // это волшебные числа??
            String[] words = string.split("_");
            for (String word: words) {
                result.append(word + " ");
            }
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data format in \"findString\" method" );
        }
        return result.toString();
    }
}
