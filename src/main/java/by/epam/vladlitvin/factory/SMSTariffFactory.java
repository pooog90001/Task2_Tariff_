package by.epam.vladlitvin.factory;

import by.epam.vladlitvin.entity.price.CallPrice;
import by.epam.vladlitvin.entity.price.InternetPrice;
import by.epam.vladlitvin.entity.price.SMSPrice;
import by.epam.vladlitvin.entity.tariff.SMSTariff;
import by.epam.vladlitvin.exception.ValueLessZeroException;

import java.math.BigDecimal;

/**
 * Created by vlad_ on 3/29/2017.
 */
public class SMSTariffFactory extends AbstractTariffFactory<SMSTariff> {

    @Override
    public SMSTariff createInstance(CallPrice callPrice,
                                    InternetPrice inetPrice,
                                    SMSPrice smsPrice,
                                    String name,
                                    BigDecimal subscriptionFee,
                                    int param) throws ValueLessZeroException {
        return new SMSTariff(name, callPrice, inetPrice,
                smsPrice, subscriptionFee, param);
    }
}
