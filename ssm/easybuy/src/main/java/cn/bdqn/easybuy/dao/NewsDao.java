package cn.bdqn.easybuy.dao;

import cn.bdqn.easybuy.entity.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDao {

    // 查询最近5条新闻显示在首页
    @Select("SELECT \n" +
            "  `id`,\n" +
            "  `title`,\n" +
            "  `content`,\n" +
            "  STR_TO_DATE(createTime,'%Y-%m-%d') \n" +
            "FROM\n" +
            "  `easybuy1`.`easybuy_news` ORDER BY createTime ASC\n" +
            "LIMIT 5")
    List<News> findTopFive();


    // 查询新闻总数量
    @Select("select COUNT(1) from `easybuy1`.`easybuy_news`")
    int totalCount();

    // 查询最近10条新闻显示在管理页面
    @Select("SELECT \n" +
            "  `id`,\n" +
            "  `title`,\n" +
            "  `content`,\n" +
            "  STR_TO_DATE(createTime,'%Y-%m-%d') \n" +
            "FROM\n" +
            "  `easybuy1`.`easybuy_news` ORDER BY createTime DESC\n" +
            "LIMIT #{start},#{end}")
    List<News> findTopTen(@Param("start") int pageNo, @Param("end")int pageSize);

    // 按id删除
    @Delete("delete from `easybuy1`.`easybuy_news` where id = #{id}")
    int delById(@Param("id") int newsId);

    // 查找单个新闻
    @Select("select * from `easybuy1`.`easybuy_news` where id = #{id}")
    News findById(@Param("id") int newsId);

    // 修改
    @Update("update `easybuy1`.`easybuy_news` set title=#{news.title}, content=#{news.content},createTime=#{news.createTime} where id=#{news.id}")
    int ModById(@Param("news") News news);

    // 增
    @Insert("insert into `easybuy1`.`easybuy_news` (title,content,createTime) values(#{news.title},#{news.content},#{news.createTime})")
    int addNews(@Param("news") News news);

}
