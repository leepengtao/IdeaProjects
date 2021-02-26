package cn.bdqn.easybuy.dao.Impl;

import cn.bdqn.easybuy.dao.BaseDao;
import cn.bdqn.easybuy.dao.NewsDao;
import cn.bdqn.easybuy.entity.News;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl extends BaseDao implements NewsDao {
    @Override
    public List<News> findTopFive() {
        List<News> list = new ArrayList<News>();
        String sql = "SELECT \n" +
                "  `id`,\n" +
                "  `title`,\n" +
                "  `content`,\n" +
                "  `createTime` \n" +
                "FROM\n" +
                "  `easybuy1`.`easybuy_news` ORDER BY createTime ASC\n" +
                "LIMIT 5";

        try {
            rs = executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Date createTime = rs.getDate("createTime");
                list.add(new News(id, title, content, createTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public int totalCount() {
        String sql = "SELECT COUNT(1) FROM `easybuy1`.`easybuy_news`";
        int count = 0;
        try {
            ResultSet ret = executeQuery(sql);
            while (ret.next()) {// 获得的是结果集
                count = ret.getInt(1);// 从结果集中获取第一个结果
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return count;
    }


    // 查询新闻总数量



    // 按页码查询新闻，后台管理使用
    @Override
    public List<News> findTopTen(int pageNo, int pageSize) {
        String sql = "SELECT id,title,content,createTime FROM easybuy_news ORDER BY createTime DESC LIMIT ?,?;";
        List<News> list = new ArrayList<News>();
        try {
            rs = executeQuery(sql,(pageNo-1)*pageSize,pageSize);
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Date createTime = rs.getDate("createTime");
                list.add(new News(id, title, content, createTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return list;
    }

    // 删
    @Override
    public int delById(int newsId) {
        String sql = "DELETE\n" +
                "FROM\n" +
                "  `easybuy1`.`easybuy_news`\n" +
                "WHERE `id` = ?";
        return executeUpdate(sql,newsId);
    }

    @Override
    public News findById(int newsId) {
        String sql = "SELECT `id`,`title`,`content`,`createTime` FROM `easybuy1`.`easybuy_news` WHERE id = ?";
        News news = null;
        try {
            rs = executeQuery(sql, newsId);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Date date = rs.getDate("createTime");
                News newstemp = new News(id,title,content,date);// ???
                news = newstemp;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn,pstmt,rs);
        }
          return news;
    }



    // 改
    @Override
    public int ModById(News news) {
        String sql = "UPDATE easybuy1.`easybuy_news` SET title = ?, content = ?, createTime = ? WHERE id = ?";
        Object[] args = {news.getTitle(), news.getContent(),news.getCreateTime(), news.getId()};
        return executeUpdate(sql,args);
    }

    // 增
    @Override
    public int addNews(News news) {
        String sql = "INSERT INTO `easybuy1`.`easybuy_news` ( `title`,`content`,`createTime`)VALUES( ?,?,? )";
        Object[] args = {news.getTitle(),news.getContent(),news.getCreateTime()};
        return executeUpdate(sql, args);
    }

}
