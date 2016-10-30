/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conventer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dmitriy
 */
public class GraphTest {
    
    public GraphTest() {
    }

    /**
     * Test of getNum method, of class Graph.
     */
    @Test
    public void testGetNum() {
        System.out.println("getNum");
        Graph graph = new Graph();
        graph.addEdge("one", "three", 1);
        graph.addEdge("one", "two", 1);
        graph.addEdge("two", "one", 5);
        assertEquals(0, graph.getNum("one"));
        assertEquals(2, graph.getNum("two"));
        assertEquals(1, graph.getNum("three"));
    }

    /**
     * Test of addEdge method, of class Graph.
     */
    @Test
    public void testAddEdge() {
        System.out.println("addEdge");
        String name1 = "one";
        String name2 = "two";
        double weight = 1.5;
        double delta = 0.01;
        Graph graph = new Graph();
        graph.addEdge("ku", "two", 8);
        assertEquals(8, graph.adj.get(0).get(0).weight.doubleValue(), delta);
        assertEquals(0.125, graph.adj.get(1).get(0).weight.doubleValue(), delta);
        graph.addEdge(name1, name2, weight);   
        assertEquals(weight, graph.adj.get(2).get(0).weight.doubleValue(), delta);
        assertEquals(1/weight, graph.adj.get(1).get(1).weight.doubleValue(), delta);
        double weight2 = 20.1;
        graph.addEdge(name1, name2, weight2);
        assertEquals(weight2, graph.adj.get(2).get(0).weight.doubleValue(), delta);
        assertEquals(1/weight, graph.adj.get(1).get(1).weight.doubleValue(), delta);
    }

    /**
     * Test of bws method, of class Graph.
     */
    @Test
    public void testBws() {
        System.out.println("bws");
        String root = "one";
        String node = "four";
        Graph graph = new Graph();
        graph.addEdge("one",  "two",  1);
        graph.addEdge("one",  "zero", 2);
        graph.addEdge("zero", "one",  3);
        graph.addEdge("five", "four", 4);
        graph.addEdge("five", "ten",  5);
        graph.addEdge("ten",  "zero", 6);
        graph.addEdge("ten",  "qqq",  7);

        double expResult = 0.2666;
        double delta = 0.01;
        double result = graph.bws(root, node);
        assertEquals(expResult, result, delta);

    }
    
}
