package currencyconverter.provider;

import currencyconverter.currency.CurrencyRepository;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface IDataProvider {
    CurrencyRepository requireRemoteData() throws IOException, ParserConfigurationException, SAXException;
}
