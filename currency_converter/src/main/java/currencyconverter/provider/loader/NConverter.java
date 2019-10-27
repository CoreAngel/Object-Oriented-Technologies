package currencyconverter.provider.loader;

import currencyconverter.currency.Currency;
import currencyconverter.currency.CurrencyRepository;
import currencyconverter.provider.parser.XmlParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;

public abstract class NConverter {

    public static CurrencyRepository remoteRepository(byte[] resource, XmlParser parser) throws IOException, SAXException, ParserConfigurationException {

        Document xml = parser.parse(resource);

        NodeList currencies = xml.getElementsByTagName("pozycja");

        int currenciesSize = currencies.getLength();
        CurrencyRepository repository = new CurrencyRepository();

        Currency baseCurrency = new Currency(
                "złoty polski",
                "PLN",
                1,
                new BigDecimal("1")
        );
        repository.addCurrency(baseCurrency);

        for (int i = 0; i < currenciesSize; ++i) {
            Element currency = (Element) currencies.item(i);

            String name = currency.getElementsByTagName("nazwa_waluty").item(0).getTextContent();
            String converterString = currency.getElementsByTagName("przelicznik").item(0).getTextContent();
            int converter = Integer.parseInt(converterString);
            String code = currency.getElementsByTagName("kod_waluty").item(0).getTextContent();
            String rateString = currency.getElementsByTagName("kurs_sredni").item(0).getTextContent();
            BigDecimal rate = new BigDecimal(rateString.replace(',', '.'));

            repository.addCurrency(new Currency(name, code, converter, rate));
        }

        return repository;
    }

}
