package org.example;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.exceptions.ShellException;
import org.jgrapht.alg.clique.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class FindMaximalGroup {
    private final Map<String, String> employees;
    private final SimpleGraph<String,DefaultEdge> graph;

    public FindMaximalGroup(){
        employees=new HashMap<>();
        graph=new SimpleGraph<>(DefaultEdge.class);
    }

    public void getPeopleFromExcel(String path){
        try {
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook page=new XSSFWorkbook(file);
            XSSFSheet sheet=page.getSheetAt(0);
            for(int i=1;i<=sheet.getLastRowNum();i++){
                XSSFRow row=sheet.getRow(i);
                String id=String.valueOf(row.getCell(0));
                String abilities=String.valueOf(row.getCell(2));
                employees.put(id,abilities);
            }
        }
        catch (IOException e) {
            throw new ShellException("Error trying to open excel document");
        }

    }

    public void buildGraph() {
        for (String id : employees.keySet()) {
            graph.addVertex(id);
        }

        for (String id1 : employees.keySet()) {
            for (String id2 : employees.keySet()) {
                if (!id1.equals(id2)) {
                    String abilities1 = employees.get(id1);
                    String abilities2 = employees.get(id2);
                    if (abilities1.equals(abilities2)) {
                        graph.addEdge(id1, id2);
                    }
                }
            }
        }
    }
    public List<String> findMaximalCliques() {
        BronKerboschCliqueFinder<String, DefaultEdge> cliqueFinder =
                new BronKerboschCliqueFinder<>(graph);
        List<String> maximalCliques = new ArrayList<>();

        for (Set<String> clique : cliqueFinder) {
            maximalCliques.add(clique.toString());
        }

        return maximalCliques;
    }

}
