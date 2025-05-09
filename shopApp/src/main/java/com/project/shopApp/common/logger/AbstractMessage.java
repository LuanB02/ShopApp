package com.project.shopApp.common.logger;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;

import java.util.Locale;

@PropertySource("classpath:messages.properties")
@Data
public class AbstractMessage {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String key) {
        return messageSource.getMessage(key, null, Locale.ENGLISH);
    }
}