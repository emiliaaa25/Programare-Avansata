package org.example;

import org.exceptions.ShellException;

import java.awt.*;
import java.io.File;

public class View implements Command {

    String command;

    View(String command) {
        this.command = command;
    }

    @Override
    public void execute() {
        try {
            if (command.length() < 5) {
                System.out.println("Please provide a file path after 'view'.");
                return;
            }

            String filePath = command.substring(5).trim();
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist");
                return;
            }

            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (Exception e) {
            throw new ShellException("Error trying to view a document: " + e.getMessage());
        }
    }

}
