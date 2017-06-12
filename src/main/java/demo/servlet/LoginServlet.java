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
 * 2017/6/9 14:35
 * JavaEE_1702.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");

        Connection connection = Db.getConnection();
        PreparedStatement statement = null; // 需赋初始值
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM db_javaee.user WHERE mobile=? AND password=?";
            if (connection != null) {
                statement = connection.prepareStatement(sql);
            }else {
                req.setAttribute("message", "出现了一点情况。。。");
                req.getRequestDispatcher("default.jsp").forward(req, resp);
                return; // 返回类型void 退出 doPost() 方法
            }
            statement.setString(1, mobile);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                req.getSession().setAttribute("nick", resultSet.getString("nick"));
//                req.getRequestDispatcher("index.jsp").forward(req, resp);
                resp.sendRedirect("index.jsp"); // 重定向可以保存session范围内数据
            }else {
                req.setAttribute("message", "用户名或密码错误");
                req.getRequestDispatcher("default.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Db.close(resultSet,statement,connection);
        }
    }
}