package cn.yz.easybuy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private String driverName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/easybuy1";// ���ӵ�ַ
	private String user = "root";
	private String password = "root";

	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	// ͨ�����ӷ���
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	// ͨ�ùرշ���
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//ͨ�õ���ɾ��
	public int executeUpdate(String sql,Object...args) {
		int ret=0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i+1, args[i]);
			}
			ret=pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return ret;
	}
}
