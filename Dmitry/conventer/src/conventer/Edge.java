
package conventer;

import java.math.BigDecimal;

/**
 * @author Dmitry
 */
class Edge {
    public int num;
    public BigDecimal weight;
    
    Edge(int num, double weight) {
        this.num = num;
        this.weight = new BigDecimal(weight);
    }

}
