package demo.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by caoyuncong on
 * 2017/6/14 19:29
 * JavaEE_1702.
 */
public class Error {
//    public static void showErrorMessage(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
//        req.setAttribute("message", "Error.");
//        req.getRequestDispatcher("default.jsp").forward(req, resp);
//    }

    public static void showErrorMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", "Error.");
        req.getRequestDispatcher("default.jsp").forward(req, resp);
    }
}
