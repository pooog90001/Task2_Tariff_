package by.epam.vladlitvin.reader;

import by.epam.vladlitvin.exception.FileReadException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by vlad_ on 3/18/2017.
 */
public class TariffFileReader {
    private final static Logger LOGGER = LogManager.getLogger(TariffFileReader.class.getName());

    public static String readFile(String fileName) throws FileReadException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader( new FileReader(fileName))){
            reader.lines().forEach(s -> stringBuilder.append(s.trim()));
            LOGGER.log(Level.INFO,"With file " + fileName + "read: \n"
                     + stringBuilder.toString());

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.FATAL,"This file not found " + e);
            throw new RuntimeException(e.getMessage(), e);

        } catch (IOException e) {
            throw new FileReadException("The file is unreadable", e);
        }

        return stringBuilder.toString();
    }

}
