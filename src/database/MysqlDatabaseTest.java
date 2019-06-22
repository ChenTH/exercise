package database;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.xml.transform.Result;
import java.sql.*;

public class MysqlDatabaseTest {


    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        DataBaseParam databasePracticeParam = new DataBaseParam();
        databasePracticeParam.setUrl("jdbc:mysql://localhost:3306/databasepractice?useUnicode=true&characterEncoding=UTF8");
        databasePracticeParam.setUser("root");
        databasePracticeParam.setPwd("123456");
        DataBaseParam vcenterParam = new DataBaseParam();
        vcenterParam.setUrl("jdbc:mysql://localhost:3306/databasepractice?useUnicode=true&characterEncoding=UTF8");
        vcenterParam.setUser("root");
        vcenterParam.setPwd("123456");

//        baseTest(vcenterParam.getUrl(), vcenterParam.getUser(), vcenterParam.getPwd());
//        repeatableTransactionInsertTest(databasePracticeParam.getUrl(), databasePracticeParam.getUser(), databasePracticeParam.getPwd());
//        repeatableTransactionUpdateTest(databasePracticeParam.getUrl(), databasePracticeParam.getUser(), databasePracticeParam.getPwd());
//        repeatableTransactionDeleteTest(databasePracticeParam.getUrl(), databasePracticeParam.getUser(), databasePracticeParam.getPwd());
//        readUncommittedTransactionSelectTest(databasePracticeParam.getUrl(), databasePracticeParam.getUser(), databasePracticeParam.getPwd());
        readCommittedTransactionSelectTest(databasePracticeParam.getUrl(), databasePracticeParam.getUser(), databasePracticeParam.getPwd());

    }

    //测试基本的改查操作
    private static void baseTest(String url, String user, String pwd) {
        //Statement查询测试
        System.out.println("Statement查询测试");
        try (Connection connection = DriverManager.getConnection(url, user, pwd);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select * from vcenter_user");) {
            while (rs.next()) {
                System.out.println(rs.getString("user_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //statement更新测试
        System.out.println("statement更新测试");
        try (Connection connection = DriverManager.getConnection(url, user, pwd);
             Statement stmt = connection.createStatement();) {
            int rs = stmt.executeUpdate("update vcenter_user set nick_name='yahaha' where binary user_name = 'testUser'");
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //PrepareStatement查询测试
        System.out.println("PrepareStatement查询测试");
        try (Connection connection = DriverManager.getConnection(url, user, pwd);
             PreparedStatement preparedStatement = connection.prepareStatement("select * from vcenter_user where user_name =? and nick_name = ?");) {
            preparedStatement.setString(1, "testUser");
            preparedStatement.setString(2, "yahaha");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("user_name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //PrepareStatement更新测试
        System.out.println("PrepareStatement更新测试");
        try (Connection connection = DriverManager.getConnection(url, user, pwd);
             PreparedStatement preparedStatement = connection.prepareStatement("update vcenter_user set nick_name='yahaha' where binary user_name = ?");) {
            preparedStatement.setString(1, "testUser");
            int n = preparedStatement.executeUpdate();
            System.out.println(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void repeatableTransactionUpdateTest(String url, String user, String pwd) {
        //statement更新测试
        System.out.println("事务测试");
        try (
                Connection conn1 = DriverManager.getConnection(url, user, pwd);
                Connection conn2 = DriverManager.getConnection(url, user, pwd);
        ) {
            conn1.setAutoCommit(false);
            conn2.setAutoCommit(false);
            conn1.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            conn2.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            try (Statement statement1 = conn1.createStatement();
                 Statement statement2 = conn2.createStatement();) {
                String statement1Select = "select age from user_info where id =1";
                String statement1Update = "update user_info set age='50' where binary id = '1'";
                String statement2Select = "select age from user_info where id =1";

                try (ResultSet rs = statement1.executeQuery(statement1Select)) {
                    while (rs.next()) {
                        System.out.println("事务1查询年龄为：" + rs.getInt("age"));
                    }
                }

                try (ResultSet rs = statement2.executeQuery(statement2Select)) {
                    while (rs.next()) {
                        System.out.println("事务2查询年龄为：" + rs.getInt("age"));
                    }
                }

                statement1.executeUpdate(statement1Update);
                System.out.println("事务1更新了年龄");

                try (ResultSet rs = statement1.executeQuery(statement1Select)) {
                    while (rs.next()) {
                        System.out.println("事务1查询年龄为：" + rs.getInt("age"));
                    }
                }

                try (ResultSet rs = statement2.executeQuery(statement2Select)) {
                    while (rs.next()) {
                        System.out.println("事务2查询年龄为：" + rs.getInt("age"));
                    }
                }
            }
//            int i = 1 / 0;
            conn1.commit();
            conn2.commit();
            System.out.println("事务已提交");
        } catch (Exception e) {
            System.out.println("事务未提交");
            e.printStackTrace();
        }
    }

    private static void repeatableTransactionInsertTest(String url, String user, String pwd) {
        //statement插入测试
        System.out.println("事务测试");
        try (
                Connection conn1 = DriverManager.getConnection(url, user, pwd);
                Connection conn2 = DriverManager.getConnection(url, user, pwd);
        ) {
            conn1.setAutoCommit(false);
            conn2.setAutoCommit(false);
            conn1.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            conn2.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            try (Statement statement1 = conn1.createStatement();
                 Statement statement2 = conn2.createStatement();) {
                String statement1Select = "select * from user_info where age >30";
                String statement1Update = "insert into user_info(name,age) value ('qwe',31)";
                String statement2Select = "select * from user_info where age >30";

                try (ResultSet rs = statement1.executeQuery(statement1Select)) {
                    while (rs.next()) {
                        System.out.println("事务1查询姓名为：" + rs.getString("name") + "年龄为：" + rs.getInt("age"));
                    }
                }

                try (ResultSet rs = statement2.executeQuery(statement2Select)) {
                    while (rs.next()) {
                        System.out.println("事务2查询姓名为：" + rs.getString("name") + "年龄为：" + rs.getInt("age"));
                    }
                }

                statement1.executeUpdate(statement1Update);
                System.out.println("事务1插入了新的数据");

                try (ResultSet rs = statement1.executeQuery(statement1Select)) {
                    while (rs.next()) {
                        System.out.println("事务1查询姓名为：" + rs.getString("name") + "年龄为：" + rs.getInt("age"));
                    }
                }
                conn1.commit();
                try (ResultSet rs = statement2.executeQuery(statement2Select)) {
                    while (rs.next()) {
                        System.out.println("事务2查询姓名为：" + rs.getString("name") + "年龄为：" + rs.getInt("age"));

                    }
                }
            }
//            int i = 1 / 0;

            conn2.commit();
            System.out.println("事务已提交");
        } catch (Exception e) {
            System.out.println("事务未提交");
            e.printStackTrace();
        }
    }

    private static void repeatableTransactionDeleteTest(String url, String user, String pwd) {
        //statement插入测试
        System.out.println("事务测试");
        try (
                Connection conn1 = DriverManager.getConnection(url, user, pwd);
                Connection conn2 = DriverManager.getConnection(url, user, pwd);
        ) {
            conn1.setAutoCommit(false);
            conn2.setAutoCommit(false);
            conn1.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            conn2.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            try (Statement statement1 = conn1.createStatement();
                 Statement statement2 = conn2.createStatement();) {
                String statement1Select = "select * from user_info where age >30";
                String statement1delete = "delete from user_info where age ='31'";
                String statement2Select = "select * from user_info where age >30";

                try (ResultSet rs = statement1.executeQuery(statement1Select)) {
                    while (rs.next()) {
                        System.out.println("事务1查询姓名为：" + rs.getString("name") + "年龄为：" + rs.getInt("age"));
                    }
                }

                try (ResultSet rs = statement2.executeQuery(statement2Select)) {
                    while (rs.next()) {
                        System.out.println("事务2查询姓名为：" + rs.getString("name") + "年龄为：" + rs.getInt("age"));
                    }
                }

                int count = statement1.executeUpdate(statement1delete);
                System.out.println("事务1删除了数据" + count + "条");

                try (ResultSet rs = statement1.executeQuery(statement1Select)) {
                    while (rs.next()) {
                        System.out.println("事务1查询姓名为：" + rs.getString("name") + "年龄为：" + rs.getInt("age"));
                    }
                }
                conn1.commit();
                try (ResultSet rs = statement2.executeQuery(statement2Select)) {
                    while (rs.next()) {
                        System.out.println("事务2查询姓名为：" + rs.getString("name") + "年龄为：" + rs.getInt("age"));

                    }
                }
            }
//            int i = 1 / 0;
            conn1.commit();
            conn2.commit();
            System.out.println("事务已提交");
        } catch (Exception e) {
            System.out.println("事务未提交");
            e.printStackTrace();
        }
    }

    private static void readUncommittedTransactionSelectTest(String url, String user, String pwd) {
        //statement更新测试
        System.out.println("事务测试");
        try (
                Connection conn1 = DriverManager.getConnection(url, user, pwd);
                Connection conn2 = DriverManager.getConnection(url, user, pwd);
        ) {
            conn1.setAutoCommit(false);
            conn2.setAutoCommit(false);
            conn1.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            conn2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            try (Statement statement1 = conn1.createStatement();
                 Statement statement2 = conn2.createStatement();) {
                String statement1Select = "select age from user_info where id =1";
                String statement1Update = "update user_info set age='50' where binary id = '1'";
                String statement2Select = "select age from user_info where id =1";

                try (ResultSet rs = statement1.executeQuery(statement1Select)) {
                    while (rs.next()) {
                        System.out.println("事务1查询年龄为：" + rs.getInt("age"));
                    }
                }

                try (ResultSet rs = statement2.executeQuery(statement2Select)) {
                    while (rs.next()) {
                        System.out.println("事务2查询年龄为：" + rs.getInt("age"));
                    }
                }

                statement1.executeUpdate(statement1Update);
                System.out.println("事务1更新了年龄");

                try (ResultSet rs = statement1.executeQuery(statement1Select)) {
                    while (rs.next()) {
                        System.out.println("事务1查询年龄为：" + rs.getInt("age"));
                    }
                }

                try (ResultSet rs = statement2.executeQuery(statement2Select)) {
                    while (rs.next()) {
                        System.out.println("事务2查询年龄为：" + rs.getInt("age"));
                    }
                }
            }
//            int i = 1 / 0;
            conn1.commit();
            conn2.commit();
            System.out.println("事务已提交");
        } catch (Exception e) {
            System.out.println("事务未提交");
            e.printStackTrace();
        }
    }

    private static void readCommittedTransactionSelectTest(String url, String user, String pwd) {
        //statement更新测试
        System.out.println("事务测试");
        try (
                Connection conn1 = DriverManager.getConnection(url, user, pwd);
                Connection conn2 = DriverManager.getConnection(url, user, pwd);
        ) {
            conn1.setAutoCommit(false);
            conn2.setAutoCommit(false);
            conn1.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            conn2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            try (Statement statement1 = conn1.createStatement();
                 Statement statement2 = conn2.createStatement();) {
                String statement1Select = "select age from user_info where id =1";
                String statement1Update = "update user_info set age='50' where binary id = '1'";
                String statement2Select = "select age from user_info where id =1";

                try (ResultSet rs = statement1.executeQuery(statement1Select)) {
                    while (rs.next()) {
                        System.out.println("事务1查询年龄为：" + rs.getInt("age"));
                    }
                }

                try (ResultSet rs = statement2.executeQuery(statement2Select)) {
                    while (rs.next()) {
                        System.out.println("事务2查询年龄为：" + rs.getInt("age"));
                    }
                }

                statement1.executeUpdate(statement1Update);
                System.out.println("事务1更新了年龄");

                try (ResultSet rs = statement1.executeQuery(statement1Select)) {
                    while (rs.next()) {
                        System.out.println("事务1查询年龄为：" + rs.getInt("age"));
                    }
                }

                try (ResultSet rs = statement2.executeQuery(statement2Select)) {
                    while (rs.next()) {
                        System.out.println("事务2查询年龄为：" + rs.getInt("age"));
                    }
                }
                conn1.commit();
                System.out.println("事务1提交");
                try (ResultSet rs = statement2.executeQuery(statement2Select)) {
                    while (rs.next()) {
                        System.out.println("事务2查询年龄为：" + rs.getInt("age"));
                    }
                }
            }

//            int i = 1 / 0;
            conn2.commit();
            System.out.println("事务2提交");
        } catch (Exception e) {
            System.out.println("事务未提交");
            e.printStackTrace();
        }
    }
}
