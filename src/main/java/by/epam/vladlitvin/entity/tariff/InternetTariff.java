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
public class InternetTariff extends AbstractTariff {
    public static final TypeTariff typeTariff = TypeTariff.FOR_INTERNET;
    private int freeMegabytes;

    public InternetTariff(String name, CallPrice callPrice,
                          InternetPrice internetPrice,
                          SMSPrice messagePrice,
                          BigDecimal subscriptionFee,
                          int freeMegabytes) throws ValueLessZeroException {

        super(name, callPrice, internetPrice, messagePrice, subscriptionFee);

        if (freeMegabytes >= 0) {
            this.freeMegabytes = freeMegabytes;
        } else {
            throw new ValueLessZeroException();
        }
    }
}
