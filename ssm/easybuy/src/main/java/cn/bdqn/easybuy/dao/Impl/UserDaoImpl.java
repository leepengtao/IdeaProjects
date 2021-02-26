package cn.bdqn.easybuy.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.easybuy.entity.Product;
import cn.bdqn.easybuy.entity.User;
import cn.bdqn.easybuy.dao.BaseDao;
import cn.bdqn.easybuy.dao.UserDao;
import cn.bdqn.easybuy.util.PageBean;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User findByLoginName(String loginName) {
        User user = null;
        try {
            String sql = "SELECT `id`,`loginName`, `userName`,`password`,`sex`,"
                    + " `identityCode`,`email`,`mobile`,`type` " +
                    " FROM `easybuy_user` WHERE `loginName`= ?";
            rs = executeQuery(sql, loginName);
            if(rs.next()) {
                Integer id = rs.getInt("id");
                //String loginName = rs.getString("loginName");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                Integer sex = rs.getInt("sex");
                String identityCode = rs.getString("identityCode");
                String email = rs.getString("email");
                String mobile = rs.getString("mobile");
                Integer type = rs.getInt("type");
                user = new User(id, loginName, userName, password, sex,
                        identityCode, email, mobile, type);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return user;
    }

    @Override
    public int addUser(User user) {
        String sql = "INSERT INTO `easybuy_user` (\r\n" +
                "  `loginName`,\r\n" +
                "  `userName`,\r\n" +
                "  `password`,\r\n" +
                "  `sex`,\r\n" +
                "  `identityCode`,\r\n" +
                "  `email`,\r\n" +
                "  `mobile`,\r\n" +
                "  `type`) VALUES (?,?,?,?,?,?,?,?)";
        Object[] args = {user.getLoginName(),user.getUserName(),user.getPassword(),
                user.getSex(),user.getIdentityCode(),user.getEmail(),user.getMobile(),user.getType()};
        return executeUpdate(sql, args);
    }

    @Override
    public List<User> testFindAll() {
        return null;
    }

    @Override
    public List<User> findByList(int pageNo, int pageSize) {
        List<User> list = new ArrayList<User>();
        String sql = "SELECT\n" +
                "  `id`,\n" +
                "  `loginName`,\n" +
                "  `userName`,\n" +
                "  `password`,\n" +
                "  `sex`,\n" +
                "  `identityCode`,\n" +
                "  `email`,\n" +
                "  `mobile`,\n" +
                "  `type`\n" +
                "FROM\n" +
                "  `easybuy1`.`easybuy_user`\n" +
                "LIMIT ?,?";
        try {
            rs = executeQuery(sql,(pageNo-1)*pageSize,pageSize);
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginName = rs.getString("loginName"); // 登录名
                String userName = rs.getString("userName");// 昵称
                String password = rs.getString("password");// 登录密码 加密
                Integer sex = rs.getInt("sex"); // 性别 1.男 0 女
                String identityCode= rs.getString("identityCode");
                String email= rs.getString("email");
                 String mobile= rs.getString("mobile");
                 Integer type = rs.getInt("type");
                list.add(new User(id, loginName, userName, password, sex, identityCode, email, mobile, type));
            }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeAll(conn, pstmt, rs);
            }
        return list;
    }

    @Override
    public int findTotalCount() {
        int count = 0;
        String sql = "SELECT COUNT(1) FROM easybuy1.easybuy_user";
        try {
            rs = executeQuery(sql);
            if(rs.next()) {
                count = rs.getInt(1);	// 第一列
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return count;
    }

    // 删除单个用户
    @Override
    public int delUserById(int id) {
        String sql = "DELETE\n" +
                "FROM\n" +
                "  `easybuy1`.`easybuy_user`\n" +
                "WHERE `id` = ?";
        return executeUpdate(sql, id);
    }


    // 修改一个用户的信息
    @Override
    public int modUser(User user) {
        Integer id = user.getId();
         String loginName = user.getLoginName(); // 登录名
         String userName = user.getUserName(); // 昵称
         String password = user.getPassword(); // 登录密码 加密
         Integer sex = user.getSex(); // 性别 1.男 0 女
         String identityCode = user.getIdentityCode();
         String email = user.getEmail();
         String mobile = user.getMobile();
         Integer type = user.getType();
        Object[] args = {loginName,userName,password,sex,identityCode,email,mobile,type,id};
         String sql = "UPDATE\n" +
                 "  `easybuy1`.`easybuy_user`\n" +
                 "SET\n" +
                 "  `loginName` = ?,\n" +
                 "  `userName` = ?,\n" +
                 "  `password` = ?',\n" +
                 "  `sex` = ?,\n" +
                 "  `identityCode` = ?,\n" +
                 "  `email` = ?,\n" +
                 "  `mobile` = ?,\n" +
                 "  `type` = ?\n" +
                 "WHERE `id` = ?;";
        return executeUpdate(sql,args);
    }

    // 查找模糊的总数
    @Override
    public int searchTotalCount(String search) {
        search = "%"+search+"%";
        int count = 0;
        String sql = "SELECT COUNT(1) FROM easybuy1.easybuy_user WHERE userName LIKE ?" ;
        try {
            rs = executeQuery(sql,search);
            if(rs.next()) {
                count = rs.getInt(1);	// 第一列
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return count;
    }

    // 模糊查找
    @Override
    public List<User> searchByList(String search, int pageNo, int pageSize) {
        search = "%"+search+"%";
        List<User> list = new ArrayList<User>();
        String sql = "SELECT \n" +
                "  `id`,\n" +
                "  `loginName`,\n" +
                "  `userName`,\n" +
                "  `password`,\n" +
                "  `sex`,\n" +
                "  `identityCode`,\n" +
                "  `email`,\n" +
                "  `mobile`,\n" +
                "  `type` \n" +
                "FROM\n" +
                "  `easybuy1`.`easybuy_user` \n" +
                "WHERE userName LIKE ?\n" +
                "LIMIT ?, ?";
        try {
            rs = executeQuery(sql, search,(pageNo - 1) * pageSize, pageSize);
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginName = rs.getString("loginName"); // 登录名
                String userName = rs.getString("userName");// 昵称
                String password = rs.getString("password");// 登录密码 加密
                Integer sex = rs.getInt("sex"); // 性别 1.男 0 女
                String identityCode = rs.getString("identityCode");
                String email = rs.getString("email");
                String mobile = rs.getString("mobile");
                Integer type = rs.getInt("type");
                list.add(new User(id, loginName, userName, password, sex, identityCode, email, mobile, type));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return list;
        }

}
