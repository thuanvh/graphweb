package graphweb;

import java.net.URLEncoder;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 22 déc. 2009
 * Time: 20:19:48
 * To change this template use File | Settings | File Templates.
 */
public class GraphManager extends ServletService {
    public boolean verify(Graph graph) {
        return false;
    }

    public String generateGraph(Graph graph) {
        return "";
    }

    public int getYValue(int x) {
        return 0;
    }

    public String generateEmbedCode(Graph graph) {
        return "";
    }

    public String getSerialCode(Graph graph) {
        return "";
    }

    public GraphManager() {
        init();
    }

    /**
     * Load configuration default for main form
     *
     * @return
     */
    public String init() {
        //formuleExpList = "x^2";
        width = 200;
        height = 200;
        minX = -1;
        maxX = 1;

        return "init";
    }

    /**
     * execute function main of the index.jsp
     *
     * @return
     * @throws Exception
     */
    @Override
    public String execute() throws Exception {
        loadFormuleExp();
        //getImage();
        return "success";
    }
    private String formuleExps[];
    private Vector<String> formuleExpList;
    private String formuleExp0;
    private String formuleExp1;

    public String getFormuleExp0() {
        formuleExp0 =formuleExps[0];
        return formuleExp0;
    }

    public void setFormuleExp0(String formuleExp0) {
        this.formuleExp0 = formuleExp0;
    }

    public String getFormuleExp1() {
        formuleExp0 =formuleExps[1];
        return formuleExp1;
    }

    public void setFormuleExp1(String formuleExp1) {
        this.formuleExp1 = formuleExp1;
    }

    private int width;
    private int height;
    private int minX;
    private int maxX;
    private int maxNumberOfExpression = 2;

    public Vector getFormuleExpList() {
        return formuleExpList;
    }

    public void setFormuleExpList(Vector formuleExpList) {
        this.formuleExpList = formuleExpList;
    }

    /**
     * load formule expression list
     */
    public void loadFormuleExp() {
        formuleExps=new String[maxNumberOfExpression];
        formuleExpList = new Vector();
        for (int i = 0; i < maxNumberOfExpression; i++) {
            String exp = request.getParameter("formuleExp" + i);
            formuleExps[i]=exp;
            System.out.println(exp);
            if (exp != null && exp.compareTo("") != 0) {
                formuleExpList.add(exp);
            }
        }

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    /**
     * get width of graph
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * set width of graph
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    private String graphParas;

    /**
     * Create a url for graph
     */
    public String getGraphParas() {
        try {
            String exp = "";
            for (int i = 0; i < formuleExpList.size(); i++) {
                exp += URLEncoder.encode(formuleExpList.get(i)+ GraphConfiguration.EXPRESSION_SEPARATOR, "utf8") ;
            }

            graphParas = "/displaygraph?e=" + exp + "&xl=" + minX + "&xu=" + maxX + "&w=" + width + "&h=" + height;
            return graphParas;
        } catch (Exception e) {
            return "";
        }
    }

}
