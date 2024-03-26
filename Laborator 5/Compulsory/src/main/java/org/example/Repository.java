package org.example;
import org.exceptions.RepositoryException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Repository {
    private Path masterDirectory;

    public Repository(String masterDirectoryPath) {
        this.masterDirectory = Path.of("D:\\OneDrive\\Documente\\GitHub\\Programare-Avansata\\Laborator 5\\Company");
    }

    public void displayRepositoryContent() {
        try {
            displayContent(masterDirectory);
        } catch (IOException e) {
            throw new RepositoryException("Error reading repository content: " + e.getMessage());
        }
    }

    private void displayContent(Path directory) throws IOException {
        Files.list(directory)
                .forEach(file -> {
                    try {
                        if (Files.isDirectory(file)) {
                            displayContent(file);
                        } else {
                            System.out.println(file.getFileName());
                        }
                    } catch (IOException e) {
                        throw new RepositoryException("Error reading file: " + file + ": " + e.getMessage());
                    }
                });
    }
}