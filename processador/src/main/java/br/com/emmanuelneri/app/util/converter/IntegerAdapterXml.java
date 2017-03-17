package br.com.emmanuelneri.app.util.converter;

import com.google.common.base.Strings;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class IntegerAdapterXml extends XmlAdapter<String, Integer> {

    @Override
    public Integer unmarshal(String value) {
        try {
            return Strings.isNullOrEmpty(value) ? null : Integer.valueOf(value);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    @Override
    public String marshal(Integer value) {
        return value != null ? value.toString() : null;
    }
}
