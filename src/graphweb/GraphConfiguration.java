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
    private String colorOfGraph;
    private String colorOfAxis;
    private int width;
    private int height;
    private int minX;
    private int maxX;

    public String getColorOfAxis() {
        return colorOfAxis;
    }

    public void setColorOfAxis(String colorOfAxis) {
        this.colorOfAxis = colorOfAxis;
    }

    public String getColorOfGraph() {
        return colorOfGraph;
    }

    public void setColorOfGraph(String colorOfGraph) {
        this.colorOfGraph = colorOfGraph;
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

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
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
