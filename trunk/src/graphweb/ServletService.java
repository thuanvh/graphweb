package graphweb;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 10 janv. 2010
 * Time: 20:13:35
 * To change this template use File | Settings | File Templates.
 */
public abstract class ServletService extends ActionSupport implements
        ServletRequestAware, ServletResponseAware, ServletContextAware, SessionAware {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ServletContext context;
    protected Map<String, Object> session;

    public void setSession(Map<String, Object> stringObjectMap) {
        session = stringObjectMap;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public ServletContext getServletContext() {
        return context;
    }

    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    public abstract String execute() throws Exception;

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
}
