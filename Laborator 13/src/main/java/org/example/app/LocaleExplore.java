package org.example.app;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Locale currentLocale = Locale.getDefault();
        ResourceBundle messages = ResourceBundle.getBundle("app.MessagesBundle", currentLocale);

        while (true) {
            System.out.print(messages.getString("prompt"));
            String command = scanner.nextLine();

            if ("exit".equalsIgnoreCase(command)) {
                break;
            }

            executeCommand(command, currentLocale, messages);
        }

        scanner.close();
    }

    private static void executeCommand(String command, Locale currentLocale, ResourceBundle messages) {
        if ("display".equalsIgnoreCase(command)) {
            org.example.com.DisplayLocales displayLocales = new org.example.com.DisplayLocales();
            displayLocales.display();

        } else if ("test".equalsIgnoreCase(command)) {
            org.example.com.SetLocale setLocale = new org.example.com.SetLocale("en");
            System.out.println("Current locale: " + setLocale.getCurrentLocale().getDisplayName());

            setLocale = new org.example.com.SetLocale("es");
            currentLocale = setLocale.getCurrentLocale();
            messages = ResourceBundle.getBundle("app.MessagesBundle", currentLocale);
            System.out.println("Current locale: " + setLocale.getCurrentLocale().getDisplayName());

            org.example.com.Info info = new org.example.com.Info();
            info.displayInfo(setLocale.getCurrentLocale(), messages);

            System.out.println(messages.getString("executing") + command);
        } else {
            System.out.println(messages.getString("executing") + command);
        }
    }
}