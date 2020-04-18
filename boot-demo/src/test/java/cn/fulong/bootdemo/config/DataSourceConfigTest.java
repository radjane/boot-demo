//package cn.fulong.bootdemo.config;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class DataSourceConfigTest {
//
//
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Autowired
//    @Qualifier(value = "secondJdbcTemplate")
//    private JdbcTemplate jdbcTemplate;
//
//
//    @Test
//    public void firstDataSource() {
//        List list=jdbcTemplate.queryForList("select * from sys_user ")  ;
//        System.out.println(list);
//    }
//
//    @Test
//    public void secondDataSource() {
//        List list=jdbcTemplate.queryForList("select * from hero_info")  ;
//        System.out.println(list);
//    }
//}