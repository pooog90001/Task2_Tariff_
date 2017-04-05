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
    private int freeMinutes;


    public CallTariff(String name, CallPrice callPrice,
                      InternetPrice internetPrice, SMSPrice messagePrice,
                      BigDecimal subscriptionFee, int freeMinutes) {
        super(name, callPrice, internetPrice,
                messagePrice, subscriptionFee, TypeTariff.FOR_CALLS);

        this.freeMinutes = freeMinutes;

    }

    @Override
    public String toString() {
        return "\nCallTariff { " + super.toString() +
                "\n freeMinutes = " + freeMinutes +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CallTariff)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        CallTariff that = (CallTariff) o;

        return freeMinutes == that.freeMinutes;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + freeMinutes;
        return result;
    }
}
