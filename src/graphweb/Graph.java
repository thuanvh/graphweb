package graphweb;

import net.sourceforge.jeval.Evaluator;
import org.apache.commons.jexl.Expression;
import org.apache.commons.jexl.ExpressionFactory;
import org.apache.commons.jexl.JexlContext;
import org.apache.commons.jexl.JexlHelper;


/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 22 déc. 2009
 * Time: 20:23:30
 * To change this template use File | Settings | File Templates.
 */
public class Graph {
    public static void main(String args[]) {
        try {
            Evaluator e = new Evaluator();
            String a = e.evaluate("pow(2.5,5)+sin(35+23)");
            System.out.println(a);
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public static void main1(String args[]) {
        try {
            // Create an expression object
            String jexlExp = "(x+2/3)";
            Expression e = ExpressionFactory.createExpression(jexlExp);

            // Create a context and add data
            JexlContext jc = JexlHelper.createContext();
            jc.getVars().put("x", 2);

            // Now evaluate the expression, getting the result
            Object o = e.evaluate(jc);
            System.out.println(o);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
