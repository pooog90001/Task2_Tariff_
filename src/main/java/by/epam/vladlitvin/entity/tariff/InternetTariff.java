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
    private int freeMegabytes;

    public InternetTariff(String name, CallPrice callPrice,
                          InternetPrice internetPrice,
                          SMSPrice messagePrice,
                          BigDecimal subscriptionFee,
                          int freeMegabytes) throws ValueLessZeroException {

        super(name, callPrice, internetPrice, messagePrice,
                subscriptionFee, TypeTariff.FOR_INTERNET);

        if (freeMegabytes >= 0) {
            this.freeMegabytes = freeMegabytes;
        } else {
            throw new ValueLessZeroException();
        }
    }

    @Override
    public String toString() {
        return "\nInternetTariff { " + super.toString() +
                ",\n freeMegabytes=" + freeMegabytes +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InternetTariff)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        InternetTariff that = (InternetTariff) o;

        return freeMegabytes == that.freeMegabytes;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + freeMegabytes;
        return result;
    }
}
