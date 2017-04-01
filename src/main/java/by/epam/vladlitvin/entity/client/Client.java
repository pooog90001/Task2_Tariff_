package by.epam.vladlitvin.entity.client;

import by.epam.vladlitvin.entity.tariff.AbstractTariff;

/**
 * Created by vlad_ on 3/30/2017.
 */
public class Client {

    private String clientName;
    private AbstractTariff tariff;

    public Client(String clientName, AbstractTariff tariff) {
        this.clientName = clientName;
        this.tariff = tariff;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public AbstractTariff getTariff() {
        return tariff;
    }

    public void setTariff(AbstractTariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientName='" + clientName + '\'' +
                ", tariff=" + tariff +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }

        Client client = (Client) o;

        if (!clientName.equals(client.clientName)) {
            return false;
        }
        return tariff.equals(client.tariff);
    }

    @Override
    public int hashCode() {
        int result = clientName.hashCode();
        result = 31 * result + tariff.hashCode();
        return result;
    }
}
