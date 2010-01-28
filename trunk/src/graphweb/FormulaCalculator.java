package graphweb;

import org.nfunk.jep.JEP;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 13 janv. 2010
 * Time: 00:02:52
 * To change this template use File | Settings | File Templates.
 */
public class FormulaCalculator {
    private Formula formula;
    private String variableName = "x";
    private double initialValue = 0;
    private JEP jepEngine;

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(double initialValue) {
        this.initialValue = initialValue;
    }

    public JEP getJepEngine() {
        return jepEngine;
    }

    public void setJepEngine(JEP jepEngine) {
        this.jepEngine = jepEngine;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public FormulaCalculator(Formula formula) {
        this.formula = formula;
        try {
            jepEngine = new JEP();
            jepEngine.addStandardConstants();
            jepEngine.addStandardFunctions();
            jepEngine.addVariable(variableName, initialValue);
            jepEngine.parseExpression(this.formula.getExpression());


        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public double getYValue(double xValue) {
        jepEngine.setVarValue("x", xValue);
        Object a = jepEngine.getValue();
        return (Double) a;
    }

}
