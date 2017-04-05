package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.exception.TariffParseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static by.epam.vladlitvin.action.NumberValidator.*;

/**
 * Class for parsing Tariff and Client parameters.
 */
class UnitParser {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final String PARAMETER_REGEX = "=\".+?\";";
    private static final String INTEGER_REGEX = "\\d{1,6}";
    private static final String BIG_DECIMAL_REGEX = "(\\d{1,6})(\\.\\d{1,6})?";
    private static final String STRING_REGEX = "\".+?\"";
    private final static String SPACE_REGEX ="\\s+";

    static String removeSpaces(String inPut) {
        String[] strings = inPut.split(SPACE_REGEX);
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    static BigDecimal findBigDecimalParameter(String inPut, String parameterName) throws TariffParseException {
        Pattern pattern = Pattern.compile(parameterName + PARAMETER_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        BigDecimal result;

        if(matcher.find()) {
            String bigDecimalParameter = matcher.group();
            result = findBigDecimal(bigDecimalParameter);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data " +
                    "format in \"findParameterName\" method");
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only " +
                    "one parameter with this \"" + parameterName + "\" name");
        }
        return result;
    }

    static int findIntegerParameter(String inPut, String parameterName) throws TariffParseException {
        Pattern pattern = Pattern.compile(parameterName + PARAMETER_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        int result;

        if(matcher.find()) {
            String integerParameter = matcher.group();
            result = findInteger(integerParameter);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data " +
                    "format in \"findIntegerParameter\" method");
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only " +
                    "one parameter with this \"" + parameterName + "\" name");
        }
        return result;
    }

    static String findStringParameter(String inPut, String parameterName) throws TariffParseException {
        Pattern pattern = Pattern.compile(parameterName + PARAMETER_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        String result;

        if(matcher.find()) {
            String stringParameter = matcher.group();
            result = findString(stringParameter);
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data " +
                    "format in \"findStringParameter\" method");
        } if (matcher.find()) {
            throw new TariffParseException(UnitParser.class + " Can be only " +
                    "one parameter with this \"" + parameterName + "\" name");
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
        } if (matcher.find() || !bigDecimalValid(result)) {
            throw new TariffParseException(UnitParser.class
                    + "\"findBigDecimal\" method. Can be only one real number" +
                    " in quotes or value can be upper then 0 ");
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
        } if (matcher.find() || !integerValid(result)) {
            throw new TariffParseException(UnitParser.class
                    + " \"findInteger\" method. Can be only one integer" +
                    " number in quotes or value can be upper then 0 ");
        }
        return result;
    }

    private static String findString(String inPut) throws TariffParseException {
        Pattern pattern = Pattern.compile(STRING_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        StringBuilder result = new StringBuilder();

        if(matcher.find()) {
            String string = matcher.group();
            string = string.substring(1, (string.length() - 1));
            String[] words = string.split("_");

            for (String word: words) {
                result.append(word + " ");
            }
        } else {
            throw new TariffParseException(UnitParser.class + " Illegal data " +
                    "format in \"findString\" method" );
        }
        return result.toString();
    }

}
