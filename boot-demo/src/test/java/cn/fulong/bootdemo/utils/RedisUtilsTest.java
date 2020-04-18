//package cn.fulong.bootdemo.utils;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.annotation.Resource;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class RedisUtilsTest {
//
//    @Resource
//    private RedisUtils redisUtils;
//
//
//
//    @Test
//    public void get() {
//        System.out.println(redisUtils.get("test_key2"));
//    }
//
//    @Test
//    public void delete() {
//        redisUtils.del("test_key");
//    }
//
//    @Test
//    public void getAndSet() {
//        redisUtils.sSet("test_key","test_value2");
//    }
//
//    @Test
//    public void set() {
//        redisUtils.set("test_key1","test_value1");
//        redisUtils.set("test_key2","test_value2");
//        redisUtils.set("test_key3","test_value3");
//        redisUtils.set("test_key4","test_value4");
//        redisUtils.set("test_key5","test_value5");
//    }
//}