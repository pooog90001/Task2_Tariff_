package by.epam.vladlitvin.main;

import by.epam.vladlitvin.action.TariffFinderTest;
import by.epam.vladlitvin.parser.ClientParserTest;
import by.epam.vladlitvin.parser.TariffParserTest;
import by.epam.vladlitvin.reader.TariffFileReaderTest;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by vlad_ on 4/5/2017.
 */
@Suite.SuiteClasses({TariffFinderTest.class,
        ClientParserTest.class,
        TariffParserTest.class,
        TariffFileReaderTest.class})

@RunWith(Suite.class)
public class AppTest extends TestCase {

}
