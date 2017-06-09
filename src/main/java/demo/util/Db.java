package demo.util;

import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * Created by caoyuncong on
 * 2017/6/9 11:53
 * JavaEE_1702.
 */
public class Db {
    private static final String URL = "jdbc:mysql:///?user=root&password=system";

    public static Connection getConnection() {
        try {
            new Driver();
            return DriverManager.getConnection(URL); // 接口类型声明的方法返回接口的实作类
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
