package br.com.emmanuelneri.app.util.converter;

import com.google.common.base.Strings;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateAdapterXml extends XmlAdapter<String, LocalDate> {

    public final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate unmarshal(String value) {
        try {
            return Strings.isNullOrEmpty(value) ? null : LocalDate.parse(value, dateFormatter);
        } catch (DateTimeParseException dtpe) {
            return null;
        }
    }

    @Override
    public String marshal(LocalDate value) {
        return value != null ? value.toString() : null;
    }
}
