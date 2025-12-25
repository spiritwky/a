package util;

import java.sql.*;

public class JdbcUtil {
    private static final String[] DRIVERS = {"com.mysql.cj.jdbc.Driver", "com.mysql.jdbc.Driver"};
    private static final String url =
            "jdbc:mysql://localhost:3306/shoppingsystem?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";//连接字符串
    private static final String user = "root";
    private static final String password = "123456";

    private static Connection connection;

    static {
        boolean loaded = false;
        for (String driver : DRIVERS) {
            try {
                Class.forName(driver);
                loaded = true;
                break;
            } catch (ClassNotFoundException ignored) {
                // 继续尝试下一个驱动
            }
        }
        if (!loaded) {
            System.err.println("数据库驱动加载失败: 未找到可用的 MySQL 驱动类，请确认依赖是否存在");
        }
    }

    /**
     * 建立连接
     * @return 返回获得的连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);//获得连接
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
