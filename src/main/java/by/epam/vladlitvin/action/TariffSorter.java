package by.epam.vladlitvin.action;

import by.epam.vladlitvin.entity.tariff.AbstractTariff;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by vlad_ on 3/30/2017.
 */
public class TariffSorter {

    public static void sortBySubscriptionFee(ArrayList<AbstractTariff> tariffs) {
        tariffs.sort(Comparator.comparing(AbstractTariff::getSubscriptionFee));
    }

    public static void sortByName(ArrayList<AbstractTariff> tariffs) {
        tariffs.sort(Comparator.comparing(AbstractTariff::getName));
    }

    public static void sortByNameAndSubscriptionFee (ArrayList<AbstractTariff> tariffs) {
        tariffs.sort(Comparator.comparing(AbstractTariff::getName)
                .thenComparing(AbstractTariff::getSubscriptionFee));
    }
}
