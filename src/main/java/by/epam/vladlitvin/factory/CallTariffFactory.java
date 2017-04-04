package by.epam.vladlitvin.factory;

import by.epam.vladlitvin.entity.price.CallPrice;
import by.epam.vladlitvin.entity.price.InternetPrice;
import by.epam.vladlitvin.entity.price.SMSPrice;
import by.epam.vladlitvin.entity.tariff.CallTariff;
import by.epam.vladlitvin.exception.ValueLessZeroException;

import java.math.BigDecimal;

/**
 * Created by vlad_ on 3/26/2017.
 */
public class CallTariffFactory extends AbstractTariffFactory<CallTariff> {

    @Override
    public CallTariff createInstance(CallPrice callPrice, InternetPrice inetPrice,
                                     SMSPrice smsPrice, String name,
                                     BigDecimal subscriptionFee,
                                     int param) throws ValueLessZeroException {
        return new CallTariff(name, callPrice, inetPrice,
                smsPrice, subscriptionFee, param);


    }



}
