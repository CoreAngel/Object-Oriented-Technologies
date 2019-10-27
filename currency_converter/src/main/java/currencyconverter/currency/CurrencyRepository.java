package currencyconverter.currency;

import currencyconverter.currency.Currency;

import java.util.ArrayList;
import java.util.Optional;

public class CurrencyRepository {

    private ArrayList<Currency> list = new ArrayList<>();

    public ArrayList<Currency> getList() {
        return list;
    }

    public void addCurrency(Currency currency) {
        list.add(currency);
    }

    public Optional<Currency> getValueByCode(String code) {

        Currency currency = new Currency();
        currency.setCode(code);

        int index = list.indexOf(currency);
        if (index != -1) {
            return Optional.of(list.get(index));
        }

        return Optional.empty();

    }
}
