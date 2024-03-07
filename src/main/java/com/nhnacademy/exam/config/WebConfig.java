package com.nhnacademy.exam.config;

import com.nhnacademy.exam.formatter.CsvToDepartmentFormatter;
import com.nhnacademy.exam.formatter.TextToDepartmentFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new TextToDepartmentFormatter());
        registry.addConverter(new CsvToDepartmentFormatter());
    }

}
