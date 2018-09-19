package com.itheima.maven;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class MybatisHello {

    public static void main(String[] args) {
        try {
            testUpdateById();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void testSelect() throws IOException {

        // 加载配置文件文件,本API是从Classpath加载文件的
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder =
                new SqlSessionFactoryBuilder();
        // 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory
                = sqlSessionFactoryBuilder.build(inputStream);
        // 获取SqlSession, 该对象有CURD的能力
        SqlSession session = sqlSessionFactory.openSession();
        // namespace + id,类似于包名+类名
        // User user = session.selectOne("com.itheima.pojo.User.selectById");
        // 参数1 : 指定要查询的SQL语句的ID
        // 参数2 : 指定参数
        User user = session.selectOne("com.itheima.maven.User.selectById", 10);
        System.err.println(user);
        // 释放资源
        session.close();
    }
    public static void testUpdateById() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        User user=new User(null,"lisi","1",new Date(),"shanxi");
        session.insert("com.itheima.maven.User.insertOne", user);
        System.out.println(user.getId());
        session.commit();
        // 释放资源
        session.close();
    }

}
