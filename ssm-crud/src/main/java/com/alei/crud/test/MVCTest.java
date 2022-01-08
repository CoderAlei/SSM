package com.alei.crud.test;


import com.alei.crud.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

import com.github.pagehelper.PageInfo;


//spring测试模块提供的测试请求功能，测试crud功能正确性
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration//将自身的对象注入mock
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:D:/aleistudy/springstudy/SSM/ssm-crud/src/main/resources/springMVC.xml"})
public class MVCTest {
    //传入Springmvc的ioc
    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;
    //虚拟MVC请求，获取处理结果
    @Before
    public void initMockMvc(){
         mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void testPage()throws Exception{
        //模拟拿到的返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "6")).andReturn();
        //请求成功后，请求域中会有pageInfo；可以取出pageInfo进行验证
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
        System.out.println("当前页码："+ pageInfo.getPageNum());
        //  拿到总页码
        System.out.println("总页码："+pageInfo.getPages());
        System.out.println("总记录数："+pageInfo.getTotal());
        System.out.println("在页码需要连续显示");
        int[] navigatepageNums = pageInfo.getNavigatepageNums();
        for(int i :navigatepageNums){
            System.out.println(" "+i);
        }
        //获取员工数据
        List<Employee> list = pageInfo.getList();
        for(Employee employee:list){
            System.out.println("ID:"+employee.getEmpId()+",name:"+employee.getEmpName());
        }
    }
}
