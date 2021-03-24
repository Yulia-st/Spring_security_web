package com.itstep;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@Component
public class LocalDateConverter implements Converter<String, LocalDate>{
	private final DateTimeFormatter formatter;
	 
    public LocalDateConverter(String dateFormat) {
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
    }
	@Override
	public LocalDate convert(String source) {
		// TODO Auto-generated method stub
		
		if (source == null || source.isEmpty()) {
            return null;
        }
		return LocalDate.parse(source, formatter);
	}

}
