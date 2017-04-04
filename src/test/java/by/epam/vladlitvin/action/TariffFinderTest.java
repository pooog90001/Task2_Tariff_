package by.epam.vladlitvin.action;

import by.epam.vladlitvin.entity.tariff.AbstractTariff;
import by.epam.vladlitvin.exception.FileReadException;
import by.epam.vladlitvin.parser.TariffParser;
import by.epam.vladlitvin.reader.TariffFileReader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static by.epam.vladlitvin.parser.TariffParser.*;
import static by.epam.vladlitvin.reader.TariffFileReader.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by vlad_ on 3/30/2017.
 */
public class TariffFinderTest {
    ArrayList<AbstractTariff> tariffs;

    @Before
    public void setUp() throws FileReadException {
        tariffs = tariffParse(readFile("resources/tariffs.txt"));
    }

    @Test
    public void findTariffByName1() throws Exception {
        AbstractTariff expectedTatiff = tariffs.get(0);
        AbstractTariff tariff = TariffFinder.findTariffByName(tariffs,"для умнОГо дома");
        assertThat(tariff, is(expectedTatiff));
    }

    @Test
    public void findTariffByName() throws Exception {
        AbstractTariff tariff = TariffFinder.findTariffByName(tariffs,"для умghjgjного дома");
        assertThat(tariff, is(nullValue()));
    }

    @Test
    public void findTariffByInternetPrice1() throws Exception {
        AbstractTariff expectedTatiff = tariffs.get(0);
        AbstractTariff tariff = TariffFinder.findTariffByInternetPrice(tariffs, new BigDecimal("0.04"));
        assertThat(tariff, is(expectedTatiff));
    }

    @Test
    public void findTariffByInternetPrice() throws Exception {
        AbstractTariff tariff = TariffFinder.findTariffByInternetPrice(tariffs,new BigDecimal("0.3"));
        assertThat(tariff, is(nullValue()));
    }

}