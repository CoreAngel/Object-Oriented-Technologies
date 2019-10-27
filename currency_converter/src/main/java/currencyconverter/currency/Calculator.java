package currencyconverter.currency;

import currencyconverter.currency.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    private Currency from;
    private Currency to;
    BigDecimal value;

    public Calculator(Currency from, Currency to) {
        this.from = from;
        this.to = to;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal calc() {
        BigDecimal costOneFromInBaseCurrency = from.getRate().divide(new BigDecimal(Integer.toString(from.getConverter())), 1000, RoundingMode.HALF_DOWN);
        BigDecimal costValueInBaseCurrency = costOneFromInBaseCurrency.multiply(value);
        BigDecimal costOneToInBaseCurrency = to.getRate().divide(new BigDecimal(Integer.toString(to.getConverter())),1000, RoundingMode.HALF_DOWN);
        return costValueInBaseCurrency.divide(costOneToInBaseCurrency, 4, RoundingMode.HALF_DOWN);
    }

}
