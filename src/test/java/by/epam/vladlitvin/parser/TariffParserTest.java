package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.action.TariffFinder;
import by.epam.vladlitvin.entity.client.Client;
import by.epam.vladlitvin.entity.tariff.AbstractTariff;
import by.epam.vladlitvin.reader.FileReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by vlad_ on 3/29/2017.
 */
public class TariffParserTest {
    @Test
    public void tariffParse() throws Exception {

        ArrayList<AbstractTariff> tariffs = TariffParser.tariffParse
                (FileReader.readFile("resources\\tariffs.txt"));
        ArrayList<Client>  clients = ClientParser.findClients
                (FileReader.readFile("resources\\clients.txt"), tariffs);
        AbstractTariff tariff = TariffFinder.findTariffByInternetPrice(tariffs, new BigDecimal(0.040));

        assertThat(clients.size(), is(4));
    }

}