/*
 * conventer
 */
package conventer;

import java.io.FileNotFoundException;

/**
 * @author Dmitry
 */
public class Main {
     public static void main(String[] args) throws FileNotFoundException {
       
        Conventer conventer = new Conventer();
        conventer.run("input.txt");
    }
}
