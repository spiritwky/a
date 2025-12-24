package util;

import java.sql.*;

public class JdbcUtil {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url =
            "jdbc:mysql://localhost:3306/shoppingsystem?characterEncoding=utf-8";//连接字符串
    private static final String user = "root";
    private static final String password = "123456";

    private static Connection connection;

    /**
     * 建立连接
     * @return 返回获得的连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);//通过反射机制加载驱动
            conn = DriverManager.getConnection(url, user, password);//获得连接
        } catch (ClassNotFoundException e) {
            System.err.println("数据库驱动加载失败: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.err.println("数据库连接失败: " + throwables.getMessage());
            throwables.printStackTrace();
        }

        return conn;
    }

    /**
     * 关闭连接
     */
    public static void closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
