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
import java.sql.SQLException;

/**
 * Created by caoyuncong on
 * 2017/6/12 11:45
 * JavaEE_1702.
 */
@WebServlet(urlPatterns = "/student")
public class StudentAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            add(req, resp);
            return;
        }

        req.setAttribute("message", "出了一点问题");
        req.getRequestDispatcher("default.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");

        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO db_javaee.user VALUE (NULL ,?,?,?)";
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            }else {
                return;
            }
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,gender);
            preparedStatement.setString(3, dob);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Db.close(null,preparedStatement,connection);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
