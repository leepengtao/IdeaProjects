package cn.bdqn.easybuy.dao;

import java.sql.*;

public class BaseDao2 {
    private String driverName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/easybuy1";
    private String user = "root";
    private String password = "pasword";

    protected Connection conn;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;

    // 通用的获取数据库的连接
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        return conn = DriverManager.getConnection(url, user, password);
    }

    // 通用的增删改
    public int executeUpdate(String sql, Object... args) {
        int ret = 0;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i+1,args[i]);
                }
            }
            ret = pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    // 通用的关闭所有
    public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {

        try {
            if (rs!=null)
                rs.close();
            if (pstmt!=null)
                pstmt.close();
            if (conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 通用的查询
    public ResultSet executeQuery(String sql, Object... args) {
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i+1,args[i]);
                }
            }
            rs = pstmt.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}
