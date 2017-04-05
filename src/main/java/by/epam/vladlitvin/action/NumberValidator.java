package by.epam.vladlitvin.action;

import java.math.BigDecimal;

/**
 * Created by vlad_ on 4/5/2017.
 */
public class NumberValidator {
    private final static int NULL_NUMBER = 0;

    public static boolean bigDecimalValid(BigDecimal number) {
        return (number.compareTo(new BigDecimal(NULL_NUMBER)) >= 0);
    }

    public static boolean integerValid(int number) {
        return (number >= NULL_NUMBER);
    }
}
