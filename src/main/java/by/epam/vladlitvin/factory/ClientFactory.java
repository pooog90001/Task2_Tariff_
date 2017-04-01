package by.epam.vladlitvin.factory;

import by.epam.vladlitvin.entity.client.Client;
import by.epam.vladlitvin.entity.tariff.AbstractTariff;

/**
 * Created by vlad_ on 3/31/2017.
 */
public class ClientFactory {

    public static Client createClient(String clientName, AbstractTariff tariff) {
        return new Client(clientName, tariff);
    }
}
