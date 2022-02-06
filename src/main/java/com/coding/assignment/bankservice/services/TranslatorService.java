package com.coding.assignment.bankservice.services;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TranslatorService {
    private ResourceBundleMessageSource messageSource;

    public TranslatorService(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Nullable
    public String toLocale(String code, Object... args) {
        if (code == null) {
            return null;
        }
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, code, locale);
    }

}
