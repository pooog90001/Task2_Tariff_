package by.epam.vladlitvin.action;

import by.epam.vladlitvin.entity.tariff.AbstractTariff;

import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Comparator.comparing;

/**
 * Created by vlad_ on 3/30/2017.
 */
public class TariffSorter {

    public static void sortBySubscriptionFee(ArrayList<AbstractTariff> tariffs) {
        tariffs.sort(comparing(AbstractTariff::getSubscriptionFee));
    }

    public static void sortByName(ArrayList<AbstractTariff> tariffs) {
        tariffs.sort(comparing(AbstractTariff::getName));
    }

    public static void sortByNameAndSubscriptionFee (ArrayList<AbstractTariff> tariffs) {
        tariffs.sort(comparing(AbstractTariff::getName)
                .thenComparing(AbstractTariff::getSubscriptionFee));
    }
}
