package currencyconverter.provider;

import currencyconverter.provider.loader.NConverter;
import currencyconverter.provider.loader.ResourceLoader;
import currencyconverter.provider.parser.XmlParser;
import currencyconverter.currency.CurrencyRepository;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class NDataProvider implements IDataProvider {

    @Override
    public CurrencyRepository requireRemoteData() throws IOException, ParserConfigurationException, SAXException {
        String url = "https://www.nbp.pl/kursy/xml/LastA.xml";

        byte[] data = ResourceLoader.getResource(url);
        XmlParser parser = new XmlParser();
        return NConverter.remoteRepository(data, parser);
    }
}
