package currencyconverter.ui;

import currencyconverter.currency.Currency;
import currencyconverter.currency.CurrencyRepository;

import java.util.ArrayList;

public abstract class PrintCurrencyList {

    public static void print(CurrencyRepository repository) {
        ArrayList<Currency> list = repository.getList();

        for(Currency curr: list) {
            System.out.println("Nazwa waluty: " + curr.getName());
            System.out.println("Kod waluty: " + curr.getCode());
            System.out.println("Przelicznik: " + curr.getConverter());
            System.out.println("Kurs średni: " + curr.getRate());
            System.out.println();
        }
    }

}
