package graphweb;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 22 déc. 2009
 * Time: 20:23:47
 * To change this template use File | Settings | File Templates.
 */
public class GraphConfiguration {
    public static String EXPRESSION_SEPARATOR="_";
    
    private Double rationOfCoordination;
    private Double stepX;
    private String colourOfGraph;
    private String colourOfAxis;
    private int width;
    private int height;
    private int regionX1;
    private int regionX2;

    public String getColourOfAxis() {
        return colourOfAxis;
    }

    public void setColourOfAxis(String colourOfAxis) {
        this.colourOfAxis = colourOfAxis;
    }

    public String getColourOfGraph() {
        return colourOfGraph;
    }

    public void setColourOfGraph(String colourOfGraph) {
        this.colourOfGraph = colourOfGraph;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Double getRationOfCoordination() {
        return rationOfCoordination;
    }

    public void setRationOfCoordination(Double rationOfCoordination) {
        this.rationOfCoordination = rationOfCoordination;
    }

    public int getRegionX1() {
        return regionX1;
    }

    public void setRegionX1(int regionX1) {
        this.regionX1 = regionX1;
    }

    public int getRegionX2() {
        return regionX2;
    }

    public void setRegionX2(int regionX2) {
        this.regionX2 = regionX2;
    }

    public Double getStepX() {
        return stepX;
    }

    public void setStepX(Double stepX) {
        this.stepX = stepX;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
