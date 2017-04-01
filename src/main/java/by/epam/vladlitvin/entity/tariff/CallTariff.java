package by.epam.vladlitvin.entity.tariff;

import by.epam.vladlitvin.entity.price.CallPrice;
import by.epam.vladlitvin.entity.price.InternetPrice;
import by.epam.vladlitvin.entity.price.SMSPrice;
import by.epam.vladlitvin.exception.ValueLessZeroException;
import by.epam.vladlitvin.type.TypeTariff;

import java.math.BigDecimal;

/**
 * Created by vlad_ on 3/26/2017.
 */
public class CallTariff extends AbstractTariff {
    public static final TypeTariff typeTariff = TypeTariff.FOR_CALLS;
    private int freeMinutes;


    public CallTariff(String name, CallPrice callPrice,
                      InternetPrice internetPrice,
                      SMSPrice messagePrice,
                      BigDecimal subscriptionFee,
                      int freeMinutes) throws ValueLessZeroException {
        super(name, callPrice, internetPrice,
                messagePrice, subscriptionFee);

        if (freeMinutes >= 0) {
            this.freeMinutes = freeMinutes;
        } else {
            throw new ValueLessZeroException();
        }
    }
}
