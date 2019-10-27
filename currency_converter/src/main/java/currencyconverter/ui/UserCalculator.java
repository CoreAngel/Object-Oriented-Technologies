package currencyconverter.ui;

import currencyconverter.provider.IDataProvider;
import currencyconverter.currency.Calculator;
import currencyconverter.currency.Currency;
import currencyconverter.currency.CurrencyRepository;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public abstract class UserCalculator {

    public static Optional<BigDecimal> calc(String from, String to, String value, IDataProvider provider) throws ParserConfigurationException, SAXException, IOException {

        CurrencyRepository repository = provider.requireRemoteData();

        Optional<Currency> fromCurrency = repository.getValueByCode(from);
        Optional<Currency> toCurrency = repository.getValueByCode(to);

        BigDecimal valueDecimal = new BigDecimal(value);

        if(fromCurrency.isPresent() && toCurrency.isPresent()) {
            Calculator calculator = new Calculator(fromCurrency.get(), toCurrency.get());
            calculator.setValue(valueDecimal);

            return Optional.of(calculator.calc());
        }

        return Optional.empty();

    }

}
