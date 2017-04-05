package by.epam.vladlitvin.factory;

import by.epam.vladlitvin.entity.price.CallPrice;
import by.epam.vladlitvin.entity.price.InternetPrice;
import by.epam.vladlitvin.entity.price.SMSPrice;
import by.epam.vladlitvin.entity.tariff.AbstractTariff;
import by.epam.vladlitvin.exception.ValueLessZeroException;

import java.math.BigDecimal;

/**
 * Created by vlad_ on 3/26/2017.
 */
public abstract class AbstractTariffFactory<T extends AbstractTariff> {

    public abstract  T createInstance(CallPrice callPrice, InternetPrice inetPrice,
                                      SMSPrice smsPrice, String name, BigDecimal subscriptionFee,
                                      int param);


}
