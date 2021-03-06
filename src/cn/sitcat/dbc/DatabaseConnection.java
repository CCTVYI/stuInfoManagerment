/**
 * 数据库连接类
 * @author hiseico
 * @version 0.1 Bate
 */

package cn.sitcat.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 本类专门负责数据库的连接与关闭操作,在实例化本类对象时就意味着要进行数据库的开发
 * 所以在本类的构造方法里要进行数据库驱动加载与数据库连接取得
 */
public class DatabaseConnection {
    private static final String DBRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DBURL = "jdbc:oracle:thin:@172.20.10.4:1521:oracle";
    private static final String DBUSER = "scott";
    private static final String PASSWORD = "tiger";
    private Connection conn = null;

    /**
     * 在构造方法里面为conn对象进行实例化，可以直接取得数据库的连接对象
     * 由于所有的操作都是基于数据库完成的，如果数据库取得不到连接，那么也意味着所有的操作都可以停止了
     */
    public DatabaseConnection() {
        try {
            Class.forName(DBRIVER);
            this.conn = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
        } catch (Exception e) {//虽然此处有异常，但是抛出的意义不大
            e.printStackTrace();
        }
    }

    /**
     * 取得一个数据库的连接对象
     *
     * @return Connection实例化对象
     */
    public Connection  getConnection() {
        return this.conn;
    }

    /**
     * 负责数据库的关闭
     */
    public void close() {
        if (this.conn != null) {//表示现在存在有连接对象
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

/*测试数据库连接*/
    public static void main(String[] args) {
        DatabaseConnection conn = new DatabaseConnection();
        System.out.println(conn.getConnection());
    }
}
