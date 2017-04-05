package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.action.TariffFinder;
import by.epam.vladlitvin.entity.client.Client;
import by.epam.vladlitvin.entity.tariff.AbstractTariff;
import by.epam.vladlitvin.exception.FileReadException;
import by.epam.vladlitvin.reader.TariffFileReader;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static by.epam.vladlitvin.parser.ClientParser.*;
import static by.epam.vladlitvin.reader.TariffFileReader.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by vlad_ on 4/4/2017.
 */
public class ClientParserTest {
    private ArrayList<Client>  clients;

    @Before
    public void setUp() {
        clients = new ArrayList<>();
    }

    @Test
    public void findClientsOne() throws FileReadException {
        ArrayList<AbstractTariff> tariffs = TariffParser.tariffParse
                (readFile("resources\\tariffs.txt"));
        clients = findClients(readFile("resources\\clients.txt"), tariffs);
        assertThat(clients.size(), is(4));
    }

    @Test
    public void findClientsTwo() throws FileReadException {
        clients = findClients(readFile("resources\\clients.txt"),
                new ArrayList<AbstractTariff>());
        assertThat(clients.size(), is(0));
    }
}