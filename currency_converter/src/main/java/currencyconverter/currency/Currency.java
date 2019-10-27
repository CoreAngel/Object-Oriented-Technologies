package currencyconverter.currency;

import java.math.BigDecimal;

public class Currency {

    private String name;
    private String code;
    private int converter;
    private BigDecimal rate;

    public Currency() {}

    public Currency(String name, String code, int converter, BigDecimal rate) {
        this.name = name;
        this.code = code;
        this.converter = converter;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getConverter() {
        return converter;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setConverter(int converter) {
        this.converter = converter;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        return this.code.equals(((Currency)o).getCode());
    }

}
