package org.example.com;

import java.util.Locale;

public class SetLocale {
    private Locale currentLocale;

    public SetLocale(String languageTag) {
        this.currentLocale = Locale.forLanguageTag(languageTag);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}