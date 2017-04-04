package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.entity.client.Client;
import by.epam.vladlitvin.entity.tariff.AbstractTariff;
import by.epam.vladlitvin.exception.TariffParseException;
import by.epam.vladlitvin.factory.ClientFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.vladlitvin.factory.ClientFactory.*;
import static by.epam.vladlitvin.parser.UnitParser.*;
import static by.epam.vladlitvin.action.TariffFinder.*;
/**
 * Created by vlad_ on 3/30/2017.
 */
public class ClientParser {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String CLIENT_REGEX = "Client\\[.+?\\];";
    private static final String CLIENT_NAME = "clientName";
    private static final String TARIFF_NAME = "tariffName";

    public static ArrayList<Client> findClients(String inPut,
                                                ArrayList<AbstractTariff> tariffs){
        String line = removeSpaces(inPut);
        Pattern pattern = Pattern.compile(CLIENT_REGEX);
        Matcher matcher = pattern.matcher(line);
        ArrayList<Client> clients = new ArrayList<>();

        while (matcher.find()) {
            String string = matcher.group();

            try {
                String clientName = findStringParameter(string, CLIENT_NAME);
                String tariffName = findStringParameter(string, TARIFF_NAME);
                AbstractTariff tariff = findTariffByName(tariffs, tariffName);

                if (tariff != null) {
                    clients.add(createClient(clientName, tariff));
                } else {
                    LOGGER.log(Level.WARN,"Error create Client. ");
                }

            } catch (TariffParseException e) {
                LOGGER.log(Level.ERROR,"Error create Client. " + e.getMessage());
            }
        }
        return clients;
    }

}
