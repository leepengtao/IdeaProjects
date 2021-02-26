package cn.bdqn.easybuy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
     * 数据访问层的基类 所有实现类都要继承这个类
     *
     * @author Lin
     *
     */
    public class BaseDao {


        private String driverName = "com.mysql.jdbc.Driver";
        private String url = "jdbc:mysql://localhost:3306/easybuy1"; // 连接地址
        private String user = "root"; // 登录名
        private String password = "root"; // 登录密码

        protected Connection conn;
        protected PreparedStatement pstmt = null;
        protected ResultSet rs = null;

        // 通用的获得连接的方法
        public Connection getConnection() throws ClassNotFoundException, SQLException {
            Connection conn = null;

            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);

            return conn;
        }

        // 通用的关闭所有
        public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //通用的增删改
        public int executeUpdate(String sql,Object... args) {
            int ret = 0;
            try {
                conn = getConnection();
                pstmt = conn.prepareStatement(sql);
                //加入?的占位
                if(args!=null) {
                    for (int i = 0; i < args.length; i++) {
                        pstmt.setObject(i+1, args[i]);
                    }
                }
                ret = pstmt.executeUpdate();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeAll(conn, pstmt, rs);
            }
            return ret;
        }

    // 通用的查询
    public ResultSet executeQuery(String sql, Object... args) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        conn = getConnection();
        pstmt = conn.prepareStatement(sql);
        // 加入?占位符
        if(args!=null){
            for (int i = 0; i <args.length; i++) {
                pstmt.setObject(i+1,args[i]);
            }
        }
        rs = pstmt.executeQuery();
        return rs;
    }
}
