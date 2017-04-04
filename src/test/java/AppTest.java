package EpamTasks;

import by.epam.vladlitvin.action.TariffFinderTest;
import by.epam.vladlitvin.parser.ClientParserTest;
import by.epam.vladlitvin.parser.TariffParserTest;
import by.epam.vladlitvin.reader.TariffFileReaderTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Unit test for simple App.
 */
@Suite.SuiteClasses({TariffFinderTest.class,
        ClientParserTest.class,
        TariffParserTest.class,
        TariffFileReaderTest.class})
@RunWith(Suite.class)
public class AppTest 
    extends TestCase {

}
