package graphweb;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.sourceforge.jeval.Evaluator;

import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 10 janv. 2010
 * Time: 21:37:50
 * To change this template use File | Settings | File Templates.
 */
public class GraphDisplay extends ServletService {
    @Override
    public String execute() throws Exception {
        loadParameters();
        getImage();
        return "";
    }

    private String express = "x^2";
    private int xLower = -1;
    private int xUpper = 1;
    private int width = 400;
    private int height = 400;
    private FormulaCalculator calculator;

    public void loadParameters() {
        express = request.getParameter("e");
        xLower = Integer.parseInt(request.getParameter("xl"));
        xUpper = Integer.parseInt(request.getParameter("xu"));
        width = Integer.parseInt(request.getParameter("w"));
        height = Integer.parseInt(request.getParameter("h"));

        Formula f = new Formula();
        f.setExpression(express);
        f.setMinX(xLower);
        f.setMaxX(xUpper);
        System.out.println(express);
        calculator = new FormulaCalculator(f);
    }

    public void getImage() {
        try {
            /* express="pow(x,3)";
             xLower=-1;
             xUpper=1;
             width = 400;
             height = 400;*/
            double deltaX = (xUpper - xLower) / (double) width;
            int pix[] = new int[width * height];
            int index = 0;
            BufferedImage bi = new BufferedImage(width + 2, height + 2, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < width + 2; i++) {
                for (int j = 0; j < height + 2; j++) {
                    bi.setRGB(i, j, Color.WHITE.getRGB());
                }
            }
//            bi.setRGB(0,0,width+2,height+2,(new int[]{Color.WHITE.getRGB()}),1,1);
            double[] x = new double[width];
            double[] y = new double[height];
            double maxY = Double.MIN_VALUE;
            double minY = Double.MAX_VALUE;
            int pixX = 0;
            int pixY = 0;
            for (pixX = 0; pixX < width; pixX++) {
                x[pixX] = xLower + deltaX * pixX;
                /*Evaluator e = new Evaluator();
                String tempExpression = express.replace("x", x[pixX] + "");
                System.out.println(tempExpression);
                String a = e.evaluate(tempExpression);
                y[pixX] = Double.parseDouble(a);*/
                y[pixX] = calculator.getYValue(x[pixX]);
                maxY = (maxY > y[pixX]) ? maxY : y[pixX];
                minY = (minY < y[pixX]) ? minY : y[pixX];
                System.out.print("[" + x[pixX] + "," + y[pixX] + "]");
            }
            System.out.print("\n======");
            //double deltaY=(maxY-minY)/(double)height;
            int yTransition = height;
            for (pixX = 0; pixX < width; pixX++) {
                pixY = (int) (height * (y[pixX] - minY) / (maxY - minY));
                pixY = yTransition - pixY;
                System.out.println(pixX + "," + pixY);
                bi.setRGB(pixX, pixY, Color.BLACK.getRGB());
                System.out.print("[" + pixX + "," + pixY + "]");
            }

            // Send back image       file:///home/thuan/sandbox/jep-2.4.1-ext-1.1.1-gpl/src/org/nfunk/jepexamples/Evaluator.java

            ServletOutputStream sos = response.getOutputStream();

            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
            response.setContentType("image/jpg");
            encoder.encode(bi);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

