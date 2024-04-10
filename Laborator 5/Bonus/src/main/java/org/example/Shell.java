package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Shell {
    public static void main(String[] args) throws IOException {
        String input;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            input = buffer.readLine();
            Command command = createCommand(input);
            if (command != null) {
                command.execute();
            } else if (input.equals("exit")) {
                return;
            } else {
                System.out.println("Other command: " + input);
            }
        }
    }

    private static Command createCommand(String input) {
        if (input.contains("view")) {
            return new View(input);
        } else if (input.equals("report")) {
            return new Report(new Repository("D:\\OneDrive\\Documente\\GitHub\\Programare-Avansata\\Laborator 5\\Company"));
        } else if (input.equals("export")) {
            return new Export(new Repository("D:\\OneDrive\\Documente\\GitHub\\Programare-Avansata\\Laborator 5\\Company"), "export.json");
        }else if (input.equals("excel")) {
            FindMaximalGroup graph=new FindMaximalGroup();
            graph.putPeopleInExcel(100);
            graph.getPeopleFromExcel("D:\\OneDrive\\Documente\\GitHub\\Programare-Avansata\\Laborator 5\\Company\\Abilities.xlsx");
            graph.buildGraph();
            List<String> cliques=graph.findMaximalCliques();
            System.out.println("Maximal Clique:");
            for (String clique : cliques) {
                    System.out.println("- " + clique);
                }
                System.out.println();
            }
        return null;
    }


}
