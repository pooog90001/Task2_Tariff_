package by.epam.vladlitvin.action;

import by.epam.vladlitvin.entity.price.InternetPrice;
import by.epam.vladlitvin.entity.tariff.AbstractTariff;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Iterator;

import static by.epam.vladlitvin.action.PriceRounder.*;

/**
 * Created by vlad_ on 3/30/2017.
 */
public class TariffFinder {

    public static AbstractTariff findTariffByName
            (ArrayList<AbstractTariff> tariffs , String name) {

        Iterator<AbstractTariff> iterator = tariffs.listIterator();
        name = name.toLowerCase().trim();

        while (iterator.hasNext()) {
            AbstractTariff tariff = iterator.next();
            String string = tariff.getName().toLowerCase().trim();

            if (string.equals(name)) {
                return tariff;
            }
        }
        return null;
    }

    public static AbstractTariff findTariffByInternetPrice
            (ArrayList<AbstractTariff> tariffs , BigDecimal internetPrice) {

        Iterator<AbstractTariff> iterator = tariffs.listIterator();
        internetPrice = roundBySix(internetPrice);

        while (iterator.hasNext()) {
            AbstractTariff tariff = iterator.next();
            BigDecimal price = tariff.getInternetPrice().getInternet();
            price = roundBySix(price);

            if (internetPrice.compareTo(price) == 0) {
                return tariff;
            }
        }
        return null;
    }
}
