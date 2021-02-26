package cn.yz.easybuy.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.yz.easybuy.dao.BaseDao;
import cn.yz.easybuy.dao.NewsDao;
import cn.yz.easybuy.entity.News;

public class NewsDaoImpl extends BaseDao implements NewsDao{
	/**
	 * 按照时间降序查询新闻
	 */
	@Override
	public List<News> findByNews(int pageNo, int pageSize) {
		List<News> list=new ArrayList<News>();
		String sql="SELECT id,title,content,createTime FROM easybuy_news ORDER BY createTime DESC LIMIT ?,?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String title=rs.getString("title");
				String content=rs.getNString("content");
				Date createTime=rs.getDate("createTime");
				list.add(new News(id, title, content, createTime));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}
	//找到所有新闻总数
	@Override
	public int findTotalNews() {
		String sql="SELECT COUNT(1) FROM easybuy_news";
		int totalNews=0;
		try {
			conn =getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalNews=rs.getInt(1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn,pstmt,rs);
		}
		return totalNews;	
	}
	//新增新闻
	@Override
	public int addNews(News news) {
		String sql="INSERT INTO easybuy_news (id,title,content,createTime) VALUES (0,?,?,?)";
		Object[] args= {news.getTitle(),news.getContent(),news.getTime()};
		return executeUpdate(sql, args);
	}
	//通过id找新闻的方法
	@Override
	public News findById(int id) {
		News news=null;
		String sql="SELECT id,title,content,createTime FROM easybuy_news Where id=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String title=rs.getString("title");
				String content=rs.getNString("content");
				Date createTime=rs.getDate("createTime");
				news=new News(id, title, content, createTime);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return news;
	}
	//修改新闻的方法
	@Override
	public int updateNews(News news) {
		String sql="UPDATE easybuy_news SET title =?,content =?,createTime =?WHERE id = ?";
		Object[] args= {news.getTitle(),news.getContent(),news.getTime(),news.getId()};
		return executeUpdate(sql, args);
	}
	//删除新闻的方法
	@Override
	public int delNews(int id) {
		String sql="DELETE FROM easybuy_news WHERE id =?";
		return executeUpdate(sql, id);		
	}

}
