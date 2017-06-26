package demo.util;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by caoyuncong on
 * 2017/6/15 8:54
 * JavaEE_1702.
 */
@WebListener
public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request= (HttpServletRequest) sre.getServletRequest();
        System.out.println("requestDestroyed: "+request.getRequestURL());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request= (HttpServletRequest) sre.getServletRequest();
        System.out.println("requestInitialized: "+request.getRequestURL());
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
//        request.setAttribute()
        System.out.println("attributeAdded: " + srae.getName() + ", " + srae.getValue());

    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("attributeRemoved: " + srae.getName() + ", " + srae.getValue());

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("attributeReplaced: " + srae.getName() + ", " + srae.getValue());
    }
}
