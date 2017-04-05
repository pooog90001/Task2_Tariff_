package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.action.TariffFinder;
import by.epam.vladlitvin.entity.client.Client;
import by.epam.vladlitvin.entity.tariff.AbstractTariff;
import by.epam.vladlitvin.exception.FileReadException;
import by.epam.vladlitvin.reader.TariffFileReader;

import static by.epam.vladlitvin.reader.TariffFileReader.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by vlad_ on 3/29/2017.
 */
public class TariffParserTest {
    private ArrayList<AbstractTariff> tariffs;

    @Before
    public void setUp() {
        tariffs = new ArrayList<>();
    }

    @Test
    public void tariffParseOne() throws FileReadException {
        tariffs = TariffParser.tariffParse
                (readFile("resources\\tariffs.txt"));
        assertThat(tariffs.size(), is(3));
    }

    @Test
    public void tariffParseTwo() {
        tariffs = TariffParser.tariffParse ("FOFD");
        assertThat(tariffs.size(), is(0));
    }
}