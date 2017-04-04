package by.epam.vladlitvin.reader;

import by.epam.vladlitvin.exception.FileReadException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;


/**
 * Created by vlad_ on 3/28/2017.
 */
public class TariffFileReaderTest {

    private String string;

    @Before
    public void setUp() throws Exception {
        string = new String();
    }

    @Test
    public void readFile() throws FileReadException {
      string = TariffFileReader.readFile("resources/tariffs.txt");
      assertThat(string, is(notNullValue()));
    }

    @Test(expected = RuntimeException.class)
    public void wrongRead() throws FileReadException {
        string = TariffFileReader.readFile("sdfsdf");
        assertThat(string, is(nullValue()));
    }

    @Test
    public void readFileTwo() throws FileReadException {
        String expectedString =
                "Client [clientName=\"Владимир_Петрович\";tariffName =\"Для_умного_дома\";];" +
                "Client [clientName=\"Макс\";tariffName =\"Для_умного_дома\";];" +
                "Client [clientName=\"Лёша\";tariffName =\"ГугЛить_не_перегуглить\";];" +
                "Client [clientName=\"Влад\";tariffName =\"Безграничное_общение\";];";

        string = TariffFileReader.readFile("resources/clients.txt");
        assertThat(string, is(expectedString));
    }
}