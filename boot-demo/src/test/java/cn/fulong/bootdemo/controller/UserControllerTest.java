//package cn.fulong.bootdemo.controller;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class UserControllerTest extends  BaseMockTest{
//
//
//
//    @Test
//    public void getHello() throws Exception {
//        mockMvc.perform(
//                get("/user")
//                        .param("userId", "2"))// 参数
//                .andExpect(status().isOk())// 判断接收到的状态是否是200（静态导入）
//                .andDo(print());// 打印请求和响应的详情
//
//    }
//
//}
