package database.mybatis;

import database.mybatis.dao.UserInfo;
import database.mybatis.mapper.UserInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MyBatisTest {

    private SqlSessionFactory factory;

    @Before
    public void setUp() throws IOException {
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("database/mybatis/config/mybatis-config.xml"));
    }

    @Test
    public void showDefaultCacheConfiguration() {
        System.out.println("本地缓存范围" + factory.getConfiguration().getLocalCacheScope());
        System.out.println("二级缓存范围" + factory.getConfiguration().isCacheEnabled());
    }

    @Test
    public void testLocalCache() {
        SqlSession sqlSession = factory.openSession(true);
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        UserInfo userInfo = userInfoMapper.getUserById(Long.parseLong("1"));
        System.out.println(userInfo.getName());
        UserInfo userInfo2 = userInfoMapper.getUserById(Long.parseLong("1"));
        System.out.println(userInfo2.getName());
    }
}
