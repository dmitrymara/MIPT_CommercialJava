
package conventer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

/**
 * @author Dmitry
 */
public class Graph {
    public ArrayList<ArrayList<Edge>> adj;
    
    public ArrayList<String> names;
    
    Graph() {
        adj     = new ArrayList<>(); 
        names   = new ArrayList<>();
    }
    
    public int getNum(String name) {
        return names.indexOf(name);
    }
    
    public void addEdge(String name1, String name2, double weight) {
        int num1 = getNum(name1);
        if (num1 == -1) {
            num1 = names.size();
            names.add(name1);
            adj.add(new ArrayList<>());
        }
        
        int num2 = getNum(name2);
        if (num2 == -1) {
            num2 = names.size();
            names.add(name2);
            ArrayList<Edge> edges = new ArrayList<>();
            adj.add(edges);
        }
        
        found: {
            for (Edge node: adj.get(num1)) {
                if (node.num == num2) {
                    node.weight = new BigDecimal(weight);
                    break found;
                }
            }
            Edge edge = new Edge(num2, weight);
            Edge reverse_edge = new Edge(num1, 1/weight);
            adj.get(num1).add(edge);
            adj.get(num2).add(reverse_edge);
        }
    
    }
    
    public double bws(String root, String node) {
        int rootNum = getNum(root);
        int nodeNum = getNum(node);
        if (rootNum == -1 || nodeNum == -1) { 
            return 0; 
        }
        
        BigDecimal[] weights = new BigDecimal[names.size()];
        Arrays.fill(weights, BigDecimal.ZERO);
        
        int[] parents = new int[names.size()];
        Arrays.fill(parents, -1);
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(rootNum);
        weights[rootNum] = weights[rootNum].add(BigDecimal.ONE);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (Edge edge: adj.get(current))
            {
                if (weights[edge.num].equals(BigDecimal.ZERO)) {
                    weights[edge.num] = weights[current].multiply(edge.weight, MathContext.DECIMAL128);
                    parents[edge.num] = current;
                    queue.add(edge.num);
                }
            }
        }
        return weights[nodeNum].doubleValue();
    }
}        