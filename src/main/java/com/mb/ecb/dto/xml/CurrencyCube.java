package com.mb.ecb.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cube")
public class CurrencyCube {

    @XmlAttribute(name = "currency", required = true)
    protected String currency;

    @XmlAttribute(name = "rate", required = true)
    protected BigDecimal rate;

    public CurrencyCube() {

    }

    public CurrencyCube(final String currency, final BigDecimal rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
