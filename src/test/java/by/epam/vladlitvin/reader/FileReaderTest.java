package by.epam.vladlitvin.reader;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by vlad_ on 3/28/2017.
 */
public class FileReaderTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void readFile() throws Exception {
        FileReader.readFile("resources/tariffs.txt");
    }

}