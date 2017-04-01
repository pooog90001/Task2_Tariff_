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
    private String name;
    private CallPrice callPrice;
    private InternetPrice internetPrice;
    private SMSPrice messagePrice;
    private BigDecimal subscriptionFee;

    public AbstractTariff(String name, CallPrice callPrice,
                          InternetPrice internetPrice, SMSPrice messagePrice,
                          BigDecimal subscriptionFee) throws ValueLessZeroException {

        if (subscriptionFee.compareTo(new BigDecimal("0")) >=  0) {
            this.name = name;
            this.callPrice = callPrice;
            this.internetPrice = internetPrice;
            this.messagePrice = messagePrice;
            this.subscriptionFee = roundBySix(subscriptionFee);
        } else {
            throw new ValueLessZeroException();
        }
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
}
