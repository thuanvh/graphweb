package graphweb;

import java.awt.*;
import java.awt.image.*;
import java.awt.Toolkit;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 22 déc. 2009
 * Time: 20:19:48
 * To change this template use File | Settings | File Templates.
 */
public class GraphManager implements
        ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public String execute() throws Exception {
        getImage();
        return "SUCCESS";
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getServletRequest() {
        return request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getServletResponse() {
        return response;
    }

    public void getImage() {
        try{
        int w = 100;
        int h = 100;
        int pix[] = new int[w * h];
        int index = 0;
        BufferedImage bi=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < h; y++) {
            int red = (y * 255) / (h - 1);
            for (int x = 0; x < w; x++) {
                int blue = (x * 255) / (w - 1);
                pix[index] = (255 << 24) | (red << 16) | blue;
                bi.setRGB(x,y,pix[index]);
                index++;
            }
        }
//        Image img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(w, h, pix, 0, w));

        /* Image src = getImage("doc:///demo/images/duke/T1.gif");
                ImageFilter colorfilter = new RedBlueSwapFilter();
                Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(src.getSource(),
                                        colorfilter));
        */
//        ImageProducer imageProducer = new MemoryImageSource(w, h, pix, 0, w);
        // Send back image
        ServletOutputStream sos = response.getOutputStream();

        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
            response.setContentType("image/jpg");
        encoder.encode(bi);
//            BufferedImage bi=new BufferedImage()
//        String type = imageProducer.createImage(response.getOutputStream());
//        response.setContentType(type);
        }catch(IOException e){
            
        }
    }
}
