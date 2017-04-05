package by.epam.vladlitvin.entity.tariff;

import by.epam.vladlitvin.exception.ValueLessZeroException;
import by.epam.vladlitvin.type.TypeTariff;
import by.epam.vladlitvin.entity.price.InternetPrice;
import by.epam.vladlitvin.entity.price.CallPrice;
import by.epam.vladlitvin.entity.price.SMSPrice;
import static by.epam.vladlitvin.action.PriceRounder.*;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by vlad_ on 3/25/2017.
 */
public abstract class AbstractTariff {
    private TypeTariff typeTariff;
    private String name;
    private CallPrice callPrice;
    private InternetPrice internetPrice;
    private SMSPrice messagePrice;
    private BigDecimal subscriptionFee;

    public AbstractTariff(String name, CallPrice callPrice,
                          InternetPrice internetPrice, SMSPrice messagePrice,
                          BigDecimal subscriptionFee, TypeTariff typeTariff) {
            this.name = name;
            this.callPrice = callPrice;
            this.internetPrice = internetPrice;
            this.messagePrice = messagePrice;
            this.subscriptionFee = roundBySix(subscriptionFee);
            this.typeTariff = typeTariff;
    }

    public TypeTariff getTypeTariff() {
        return typeTariff;
    }

    public String getName() {
        return name;
    }

    public CallPrice getCallPrice() {
        return callPrice;
    }

    public InternetPrice getInternetPrice() {
        return internetPrice;
    }

    public SMSPrice getMessagePrice() {
        return messagePrice;
    }

    public BigDecimal getSubscriptionFee() {
        return subscriptionFee;
    }

    @Override
    public String toString() {
        return "\n typeTariff = " + typeTariff +
                ",\n name = '" + name + '\'' +
                ",\n callPrice = " + callPrice +
                ",\n internetPrice = " + internetPrice +
                ",\n messagePrice = " + messagePrice +
                ",\n subscriptionFee = " + subscriptionFee +
        "ruble";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractTariff)) {
            return false;
        }

        AbstractTariff tariff = (AbstractTariff) o;

        if (typeTariff != tariff.typeTariff) {
            return false;
        }
        if (!name.equals(tariff.name)) {
            return false;
        }
        if (!callPrice.equals(tariff.callPrice)) {
            return false;
        }
        if (!internetPrice.equals(tariff.internetPrice)) {
            return false;
        }
        if (!messagePrice.equals(tariff.messagePrice)) {
            return false;
        }
        return subscriptionFee.equals(tariff.subscriptionFee);
    }

    @Override
    public int hashCode() {
        int result = typeTariff.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + callPrice.hashCode();
        result = 31 * result + internetPrice.hashCode();
        result = 31 * result + messagePrice.hashCode();
        result = 31 * result + subscriptionFee.hashCode();
        return result;
    }
}
