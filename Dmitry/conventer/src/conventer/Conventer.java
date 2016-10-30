
package conventer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Dmitry
 */
public class Conventer {
    
    public List<String> examples;
    private final Graph graph;
    
    Conventer() {
        graph = new Graph();
        examples = new ArrayList();
    }

    public void input(String filename) throws FileNotFoundException {
        File file;
        file = new File(filename);
        Scanner scan = new Scanner(file);
        
        while (scan.hasNextLine()) {
            examples.add(scan.nextLine());
        }
    
    }
    
    public String parse(String example) {
        double weight;
        String[] elements = example.split(" ");
        
        if (elements[0].equals("?")) {
            weight = graph.bws(elements[3], elements[1]);
            if (weight == 0) {
                return "";
            }
            double value = weight * Double.parseDouble(elements[2]);
            elements[0] = String.valueOf(value);
        }
        else if (elements[2].equals("?")) {
            weight = graph.bws(elements[1], elements[3]);
            if (weight == 0) {
                return "";
            }
            double value = weight * Double.parseDouble(elements[0]);
            elements[2] = String.valueOf(value);
        }
        else {
            weight = Double.parseDouble(elements[2]) / 
                     Double.parseDouble(elements[0]);
            graph.addEdge(elements[1], elements[3], weight);
        }
        
        StringBuilder str = new StringBuilder();
        for (String elem: elements) {
            str.append(elem);
            str.append(" ");
        }
        
        return str.toString();
    }
    
    public void run(String filename) throws FileNotFoundException
    {
        input(filename);
        for (String example: examples) {
            System.out.println(parse(example));
        }
    }
    
}
