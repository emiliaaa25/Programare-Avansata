package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.exceptions.ShellException;

import java.io.File;
import java.io.IOException;

public class Export implements Command {
    private final Repository repository;
    private final String exportPath;

    public Export(Repository repository, String exportPath) {
        this.repository = repository;
        this.exportPath = exportPath;
    }

    @Override
    public void execute() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File outputFile = new File(exportPath);
            objectMapper.writeValue(outputFile, repository.getDocuments());
            System.out.println("Repository documents exported to JSON file: " + outputFile.getName());
        } catch (IOException e) {
            throw new ShellException("Error trying to export a JSON file: " + e.getMessage());
        }
    }
}
