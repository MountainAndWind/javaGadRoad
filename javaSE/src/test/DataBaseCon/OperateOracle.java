package test.DataBaseCon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @ClassName
 * @Description 连接第三方数据工具类
 * @Author fangshilei
 * @Date 2019/12/16 14:56
 * @Version 1.0
 **/
public class OperateOracle {

    // 定义连接所需的字符串
    // 192.168.0.X是本机地址(要改成自己的IP地址)，1521端口号，XE是精简版Oracle的默认数据库名
    private static String USERNAMR = "OTF6TCE1";
    private static String PASSWORD = "OT6Ttkch";
    private static String DRVIER = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@ifrsr017.gaia.toshiba.co.jp:1556:GFAE";

    // 创建一个数据库连接
    Connection connection = null;
    // 创建预编译语句对象，一般都是用这个而不用Statement
    PreparedStatement pstm = null;
    // 创建一个结果集对象
    ResultSet rs = null;

    /**
     * 向数据库中增加数据
     * 首先获取表内数据总数,总数+1为新增数据的id值
     * @param stuName:学生姓名
     * @param gender:学生性别,1表示男性,2表示女性
     * @param age:学生年龄
     * @param address:学生住址
     */
    public void AddData(String stuName, int gender, int age, String address) {
        connection = getConnection();
        // String sql =
        // "insert into student values('1','王小军','1','17','北京市和平里七区30号楼7门102')";
        String sql = "select count(*) from student where 1 = 1";
        String sqlStr = "insert into student values(?,?,?,?,?)";
        int count = 0;

        try {
            // 计算数据库student表中数据总数
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1) + 1;
                System.out.println(rs.getInt(1));
            }
            // 执行插入数据操作
            pstm = connection.prepareStatement(sqlStr);
            pstm.setInt(1, count);
            pstm.setString(2, stuName);
            pstm.setInt(3, gender);
            pstm.setInt(4, age);
            pstm.setString(5, address);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    /**
     * 向数据库中删除数据
     * @param stuName:根据姓名删除数据
     */
    public void DeleteData(String stuName) {
        connection = getConnection();
        String sqlStr = "delete from student where stu_name=?";
        System.out.println(stuName);
        try {
            // 执行删除数据操作
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, stuName);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    /**
     * 向数据库中修改数据
     * @param stuName:学生姓名,根据此值查询要修改的某行值
     * @param gender
     * @param age
     * @param address
     */
    public void UpdateData(String stuName, int gender, int age, String address) {
        connection = getConnection();
        String sql = "select id from student where 1 = 1 and stu_name = ?";
        String sqlStr = "update student set stu_name=?,gender=?,age=?,address=? where id=?";
        int count = 0;

        try {
            // 计算数据库student表中数据总数
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, stuName);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
                System.out.println(rs.getInt(1));
            }
            // 执行插入数据操作
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, stuName);
            pstm.setInt(2, gender);
            pstm.setInt(3, age);
            pstm.setString(4, address);
            pstm.setInt(5, count);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    /**
     * 向数据库中查询数据
     */
    public void SelectData() {
        connection = getConnection();
        String sql = "select * from student where 1 = 1";
        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("stu_name");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                String address = rs.getString("address");
                System.out.println(id + "\t" + name + "\t" + gender + "\t"
                        + age + "\t" + address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    /**
     * 使用ResultSetMetaData计算列数
     */
    public void SelectData2() {
        connection = getConnection();
        String sql = "select * from employees where 1 = 1";
        int count = 0;

        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count++;
            }

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols_len = rsmd.getColumnCount();

            System.out.println("count=" + count + "\tcols_len=" + cols_len);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    /**
     * 获取Connection对象
     *
     * @return
     */
    public Connection getConnection() {
        try {
            Class.forName(DRVIER);
            connection = DriverManager.getConnection(URL, USERNAMR, PASSWORD);
            System.out.println("成功连接数据库");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return connection;
    }

    /**
     * 释放资源
     */
    public void ReleaseResource() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}