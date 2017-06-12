package demo.servlet;

import demo.util.Db;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by caoyuncong on
 * 2017/6/12 9:29
 * JavaEE_1702.
 */
@WebServlet(urlPatterns = "/user")
public class UserAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("register".equals(action)) { // action.equals NPE
            register(req, resp); // -- ?
            return;
        }
        if ("login".equals(action)) {
            login(req, resp);
            return;
        }
        if ("logout".equals(action)) {
            logout(req, resp);
            return;
        }

        req.setAttribute("message", "出现了一点问题。。。");
        req.getRequestDispatcher("default.jsp").forward(req, resp);
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nick = req.getParameter("nick").trim();
        String mobile = req.getParameter("mobile").trim();
        String password = req.getParameter("password");

        if (nick.length() == 0 || mobile.length() == 0 || password.length() == 0) {
            req.setAttribute("message", "昵称或手机号或密码不为0");
            req.getRequestDispatcher("signup.jsp").forward(req, resp);
        }

        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sqlNick = "SELECT * FROM db_javaee.user WHERE nick=?";
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sqlNick);
            } else {
                return;
            }
            preparedStatement.setString(1, nick);
            resultSet = preparedStatement.executeQuery();
            boolean isNickExist = resultSet.next();

            String sqlMobile = "SELECT * FROM db_javaee.user WHERE mobile=?";
            preparedStatement = connection.prepareStatement(sqlMobile);
            preparedStatement.setString(1, mobile);
            resultSet = preparedStatement.executeQuery();
            boolean isMobileExist = resultSet.next();

            if (isNickExist) {
                req.setAttribute("message", "昵称已经存在");
                req.getRequestDispatcher("signup.jsp");
            } else if (isMobileExist) {
                req.setAttribute("message", "手机号已经存在");
                req.getRequestDispatcher("signup.jsp");
            } else {
                String sql = "INSERT INTO db_javaee.user VALUE (NULL ,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, nick);
                preparedStatement.setString(2, mobile);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate(); // ---?
                resp.sendRedirect("default.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, preparedStatement, connection);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");

        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM db_javaee.user WHERE mobile=? AND password=?";
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            } else {
                req.setAttribute("message", "出现了一点情况。。");
                req.getRequestDispatcher("default.jsp").forward(req, resp);
                return;
            }
            preparedStatement.setString(1, mobile);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                req.getSession().setAttribute("nick", resultSet.getString("nick"));
                resp.sendRedirect("index.jsp");
            } else {
                req.setAttribute("message", "手机号或密码错误。。。");
                req.getRequestDispatcher("default.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, preparedStatement, connection);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("default.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
