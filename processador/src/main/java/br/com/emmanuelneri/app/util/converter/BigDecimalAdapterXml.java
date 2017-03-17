package br.com.emmanuelneri.app.util.converter;

import com.google.common.base.Strings;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

public class BigDecimalAdapterXml extends XmlAdapter<String, BigDecimal> {

    @Override
    public BigDecimal unmarshal(String value) {
        try {
            return Strings.isNullOrEmpty(value) ? null : new BigDecimal(value);
        } catch (NumberFormatException nfe) {
           return null;
        }
    }

    @Override
    public String marshal(BigDecimal value) {
        return value != null ? value.toEngineeringString() : null;
    }
}
