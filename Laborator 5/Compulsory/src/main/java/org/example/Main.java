package org.example;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository("master_directory");
        repository.displayRepositoryContent();
    }
}
