package demo.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by caoyuncong on
 * 2017/6/15 11:21
 * JavaEE_1702.
 */
public class SessionListenerTest implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("sessionCreated: " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("sessionDestroyed: "+se.getSession().getId());
    }
}
