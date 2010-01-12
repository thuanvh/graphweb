package graphweb;

import java.awt.*;
import java.awt.image.*;
import java.awt.Toolkit;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.sourceforge.jeval.Evaluator;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 22 déc. 2009
 * Time: 20:19:48
 * To change this template use File | Settings | File Templates.
 */
public class GraphManager extends ServletService
{
    public boolean verify(Graph graph){
        return false;
    }
    public String generateGraph(Graph graph){
return "";        
    }
    public int getYValue(int x){
        return 0;
    }
    public String generateEmbedCode(Graph graph){
         return "";
    }
    public String getSerialCode(Graph graph){
        return "";
    }
    public GraphManager(){
        init();
    }
    public String init(){
        formuleExp="x^2";
        width=200;
        height=200;
        minX=-1;
        maxX=1;

        return "init";
    }
    @Override
    public String execute() throws Exception {
        //getImage();
        return "success";
    }
    String formuleExp;
    int width;
    int height;
    int minX;
    int maxX;

    public String getFormuleExp() {
        return formuleExp;
    }

    public void setFormuleExp(String formuleExp) {
        this.formuleExp = formuleExp;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    private String graphParas;
    public String getGraphParas(){
        try{
        String exp= URLEncoder.encode(formuleExp,"utf8");
            graphParas="/displaygraph?e="+exp+"&xl="+minX+"&xu="+maxX+"&w="+width+"&h="+height;
        return graphParas;
        }catch(Exception e){
            return "";
        }
    }
    
}
