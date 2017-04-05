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
public class SMSTariff extends AbstractTariff {
    private int freeSMS;

    public SMSTariff(String name, CallPrice callPrice,
                     InternetPrice internetPrice,
                     SMSPrice messagePrice,
                     BigDecimal subscriptionFee,
                     int freeSMS) {
        super(name, callPrice, internetPrice,
                messagePrice, subscriptionFee, TypeTariff.FOR_SMS);

        this.freeSMS = freeSMS;


    }

    @Override
    public String toString() {
        return "\nSMSTariff { " + super.toString() +
                "\n freeSMS=" + freeSMS +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SMSTariff)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        SMSTariff smsTariff = (SMSTariff) o;

        return freeSMS == smsTariff.freeSMS;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + freeSMS;
        return result;
    }
}
