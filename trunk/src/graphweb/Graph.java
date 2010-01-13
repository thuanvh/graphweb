package graphweb;

import net.sourceforge.jeval.Evaluator;
import org.apache.commons.jexl.Expression;
import org.apache.commons.jexl.ExpressionFactory;
import org.apache.commons.jexl.JexlContext;
import org.apache.commons.jexl.JexlHelper;
import org.nfunk.jep.JEP;

import java.awt.*;
import java.awt.geom.Point2D;
import java.net.URLEncoder;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 22 déc. 2009
 * Time: 20:23:30
 * To change this template use File | Settings | File Templates.
 */
public class Graph {
    Vector<Point.Double[]> pointList;

    String errorMessage;
    Formula[] formulaList;
    GraphConfiguration graphConfiguration;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Formula[] getFormulaList() {
        return formulaList;
    }

    public void setFormulaList(Formula[] formulaList) {
        this.formulaList = formulaList;
    }

    public void setFormulaList(String expression[], int minX, int maxX) {
        for (int i = 0; i < expression.length; i++) {
            Formula f = new Formula();
            f.setExpression(expression[i]);
            f.setMinX(minX);
            f.setMaxX(maxX);
        }
    }

    public GraphConfiguration getGraphConfiguration() {
        return graphConfiguration;
    }

    public void setGraphConfiguration(GraphConfiguration graphConfiguration) {
        this.graphConfiguration = graphConfiguration;
    }

    public Vector<Point.Double[]> getPointList() {
        return pointList;
    }

    public void setPointList(Vector<Point.Double[]> pointList) {
        this.pointList=pointList;
    }

    public static void main(String args[]) {
        try {
            System.out.println(URLEncoder.encode("x+2"));
        } catch (Exception e) {

        }
        /*try {
            JEP jep=new JEP();
            jep.addStandardConstants();
            jep.addStandardFunctions();
            jep.addVariable("x",10);
            jep.parseExpression("x^3");

            jep.setVarValue("x",11);                        
            Object a=jep.getValue();
            System.out.println(a);
//            GraphManager gm=new GraphManager();
//            gm.getImage();
            *//*Evaluator e = new Evaluator();
            String a = e.evaluate("pow(2.5,5)+sin(35+23)");
            System.out.println(a);*//*
        } catch (Exception e) {
            System.out.println(e);

        }*/
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
