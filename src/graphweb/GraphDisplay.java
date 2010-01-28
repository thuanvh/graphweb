package graphweb;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.PNGEncodeParam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 10 janv. 2010
 * Time: 21:37:50
 * To change this template use File | Settings | File Templates.
 */
public class GraphDisplay extends ServletService implements ImageObserver {
    public static int FONT_SIZE = 16;
    public static String FONT = "Arial";
    public static int LEGEND_PADDING = 15;
    public static int IMAGE_PADDING=10;
    @Override
    public String execute() throws Exception {
        loadParameters();
        getImage();
        return "success";
    }

    private String express = "x^2";
    private int xLower = -1;
    private int xUpper = 1;
    private int width = 400;
    private int height = 400;

    private String[] colors;
    private FormulaCalculator[] calculator;
    private Graph graph;
    private BufferedImage image;
    private Graphics2D graphics;

    private double maxY = Double.MIN_VALUE;
    private double minY = Double.MAX_VALUE;

    private GraphConfiguration configuration;

    public void loadParameters() {
        configuration = GraphConfigurationManager.getGraphConfiguration(context.getRealPath(GraphConfigurationManager.DATA_FILE));

        // Separate list of expression
        express = request.getParameter("e");
        String[] exps = express.split(GraphConfiguration.EXPRESSION_SEPARATOR);

        String color = request.getParameter("c");
        colors = color.split(GraphConfiguration.EXPRESSION_SEPARATOR);
        for (int i = 0; i < colors.length; i++) {
            System.out.println(colors[i]);
//            System.out.println(color);
        }
        xLower = Integer.parseInt(request.getParameter("xl"));
        xUpper = Integer.parseInt(request.getParameter("xu"));
        width = Integer.parseInt(request.getParameter("w"));
        height = Integer.parseInt(request.getParameter("h"));

        // Create a list of FormulaCalculator
        calculator = new FormulaCalculator[exps.length];
        graph = new Graph();
        graph.setPointList(new Vector());
        for (int i = 0; i < exps.length; i++) {
            Formula f = new Formula();
            f.setExpression(exps[i]);
            f.setMinX(xLower);
            f.setMaxX(xUpper);
            System.out.println(exps[i]);
            calculator[i] = new FormulaCalculator(f);
        }
    }

    private void calculateEachFormula(int index) {
        FormulaCalculator calculator = this.calculator[index];
        double deltaX = (xUpper - xLower) / (double) width;
//            int pix[] = new int[width * height];
//        int index = 0;
        //            image.setRGB(0,0,width+2,height+2,(new int[]{Color.WHITE.getRGB()}),1,1);

        /*double[] x = new double[width];
        double[] y = new double[height];*/
        Point.Double[] pointList = new Point.Double[width];

        int pixX = 0;
        int pixY = 0;
        for (pixX = 0; pixX < width; pixX++) {
            Point.Double point = new Point.Double();
            point.x = xLower + deltaX * pixX;
            /*Evaluator e = new Evaluator();
          String tempExpression = express.replace("x", x[pixX] + "");
          System.out.println(tempExpression);
          String a = e.evaluate(tempExpression);
          y[pixX] = Double.parseDouble(a);*/
            point.y = calculator.getYValue(point.x);
            maxY = (maxY > point.y) ? maxY : point.y;
            minY = (minY < point.y) ? minY : point.y;
            pointList[pixX] = point;
            System.out.println("[" + pointList[pixX].x + "," + pointList[pixX].y + "]");
        }
        graph.getPointList().insertElementAt(pointList, index);

    }

    private void drawEachGraph(int index) {

        //            System.out.print("\n======");
        //double deltaY=(maxY-minY)/(double)height;
        int yTransition = height;
        int pixXBefore = 0;
        int pixYBefore = 0;
        int pixX, pixY;
        Color color = Color.GRAY;
        if (colors.length > index) {
            color = Color.decode("#" + colors[index]);
//            System.out.println(colors[index]);
//            System.out.println(color);
        }
        graphics.setColor(color);
        Point.Double[] pointList = graph.getPointList().get(index);
        for (pixX = 0; pixX < width; pixX++) {
            pixY = (int) Math.round(yTransition - (height * (pointList[pixX].y - minY) / (maxY - minY)));
            //pixY = yTransition - pixY;
//                System.out.println(pixXBefore + "," + pixYBefore + "," + pixX + "," + pixY);

            //image.setRGB(pixX, pixY, Color.BLACK.getRGB());
            if (pixX > 0)
                graphics.drawLine(pixXBefore, pixYBefore, pixX, pixY);
//                System.out.print("[" + pixX + "," + pixY + "]");
            pixXBefore = pixX;
            pixYBefore = pixY;
        }
    }

    private void drawLegend() {
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Font font = new Font(FONT, Font.BOLD, FONT_SIZE);

        graphics.setFont(font);

        Color colorAxe = Color.decode("#" + configuration.getColorOfAxis());
        graphics.setColor(colorAxe);
        // draw axe x
        int pixY = (int) Math.round(height + (height * minY) / (maxY - minY));
        System.out.print(pixY + ",");
        graphics.drawLine(0, pixY, width, pixY);
        // draw axe y
        int pixX = (int) Math.round(width + (width * xLower) / (xUpper - xLower));
        graphics.drawLine(pixX, 0, pixX, height);

        // draw arrow x
        // x=// Read from a file
        try {
            File xImageFile = new File(context.getRealPath("/images/fleche.jpg"));
            Image ximage = ImageIO.read(xImageFile);

            int arrowx, arrowy;
            arrowx = width - ximage.getWidth(this);
            arrowy = pixY - ximage.getHeight(this) / 2;
            graphics.drawImage(ximage, arrowx, arrowy, this);
            graphics.drawString("x", width-20, pixY+20);

            File yImageFile = new File(context.getRealPath("/images/fleche2.jpg"));
            Image yimage = ImageIO.read(yImageFile);
            arrowx = pixX - yimage.getWidth(this) / 2;
            arrowy = 0;
            graphics.drawImage(yimage, arrowx, arrowy, this);
            graphics.drawString("y", pixX+10, FONT_SIZE);
            //BufferedImage bufferedImage= ;
        } catch (Exception e) {

        }

        // draw legends

        //graphics.drawString("abc",100,100);
        for (int i = 0; i < calculator.length; i++) {
            if (colors.length > i) {
                Color color = Color.decode("#" + colors[i]);
                graphics.setColor(color);
            }
            int stringX = 0;
            int stringY = LEGEND_PADDING * (i + 1);
            graphics.drawString(calculator[i].getFormula().getExpression(), stringX, stringY);
        }
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void getImage() {
        try {
            // initialise image
            image = new BufferedImage(width + 2, height + 2, BufferedImage.TYPE_INT_RGB);
            graphics = image.createGraphics();
            graphics.setColor(Color.BLACK);
            graphics.setPaintMode();

            for (int i = 0; i < width + 2; i++) {
                for (int j = 0; j < height + 2; j++) {
                    image.setRGB(i, j, Color.WHITE.getRGB());
                }
            }
            // draw each formula
            for (int i = 0; i < calculator.length; i++) {
                calculateEachFormula(i);
            }
            // normalise maxY, minY
            if (maxY < 0 && minY < 0) {
                maxY = 3;
            }
            if (maxY > 0 && minY > 0) {
                minY = 0;
            }
            graphics.setStroke(new BasicStroke(2));
            for (int i = 0; i < calculator.length; i++) {
                drawEachGraph(i);
            }
            graphics.setStroke(new BasicStroke(1));
            drawLegend();

            // output image to the stream of response
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            OutputStream outputStream = response.getOutputStream();

            //PNGImageEncoder
            JPEGImageEncoder jpg = JPEGCodec.createJPEGEncoder(bos);
            jpg.encode(image);
            byte[] byteFileArray = bos.toByteArray();
            outputStream.write(byteFileArray);

            /*PNGEncodeParam encParam = new PNGEncodeParam.Gray();
            ImageEncoder encoder2= ImageCodec.createImageEncoder ("PNG", outputStream, encParam);
            encoder2.encode(image);*/

            outputStream.flush();
            bos.close();
            outputStream.close();
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}

