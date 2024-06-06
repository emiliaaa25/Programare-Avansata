package org.example.com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.*;

public class Info {
    public void displayInfo(Locale locale, ResourceBundle messages) {
        System.out.println(messages.getString("country") + " " + locale.getDisplayCountry());
        System.out.println(messages.getString("language") + " " + locale.getDisplayLanguage());
        System.out.println(messages.getString("currency") + " " + getCurrency(locale));
        System.out.println(messages.getString("weekdays") + " " + getWeekDays(locale));
        System.out.println(messages.getString("months") + " " + getMonths(locale));
        System.out.println(messages.getString("today") + " " + getToday(locale));
    }

    private String getCurrency(Locale locale) {
        try {
            Currency currency = Currency.getInstance(locale);
            return currency.getCurrencyCode() + " (" + currency.getDisplayName() + ")";
        } catch (IllegalArgumentException e) {
            return "Currency not available for this locale";
        }
    }

    private String getWeekDays(Locale locale) {
        DateFormatSymbols symbols = new DateFormatSymbols(locale);
        String[] weekdays = symbols.getWeekdays();
        return String.join(", ", Arrays.copyOfRange(weekdays, 1, weekdays.length));
    }

    private String getMonths(Locale locale) {
        DateFormatSymbols symbols = new DateFormatSymbols(locale);
        String[] months = symbols.getMonths();
        return String.join(", ", Arrays.copyOfRange(months, 0, months.length - 1));
    }

    private String getToday(Locale locale) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
        return dateFormat.format(new Date());
    }
}