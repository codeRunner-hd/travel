package cn.itcast.travel.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 1. 声明静态数据源成员变量
 * 2. 创建连接池对象
 * 3. 定义公有的得到数据源的方法
 * 4. 定义得到连接对象的方法
 * 5. 定义关闭资源的方法
 *
 * @author Admin-han
 */
public class JDBCUtils {
    /**
     * 1、声明静态数据源成员变量
     */
    private static DataSource ds;

    // 2、创建连接池对象
    static {
        // 加载配置文件中的数据
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties prop = new Properties();
        try {
            prop.load(is);
            // 创建连接池，使用配置文件中的参数
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 3. 定义公有的得到数据源的方法
     *
     * @return
     */
    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * 4. 定义得到连接对象的方法
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 5.定义关闭资源的方法
     *
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 6.重载关闭方法
     *
     * @param conn
     * @param stmt
     */
    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }
}
