package by.epam.vladlitvin.action;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by vlad_ on 3/31/2017.
 */
public class PriceRounder {
    private final static int INT_SIX = 6;
    public static BigDecimal roundBySix(BigDecimal number) {
        return number.round(new MathContext(6));
    }
}
