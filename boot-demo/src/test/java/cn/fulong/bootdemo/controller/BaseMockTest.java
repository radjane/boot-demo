//package cn.fulong.bootdemo.controller;
//
//import org.junit.Before;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
///**
// * @Author:GHB
// * @Date:2019-07-25
// * 单元测试 controller 层 基类
// */
//public class BaseMockTest {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    public MockMvc mockMvc;
//
//    @Before
//    public void setUpMockMvc(){
////        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
//
//    }
//
//}
