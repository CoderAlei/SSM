package com.alei.crud.test;

import com.alei.crud.bean.Department;
import com.alei.crud.bean.Employee;
import com.alei.crud.dao.DepartmentMapper;
import com.alei.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

//测试dao层的工作
//Spring的项目就可以使用spring的单元测试，可以自动注入我们需要的组件
//导入SpringTest模块
//指定contConfi指定spring配置文件位置
//直接autowired要使用的组件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;
//    测试departmentMapper
    @Test
    public  void testCRUD(){
/*
        //1、springIOC容器创建
        ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.从容器中获取mapper
        ioc.getBean(DepartmentMapper.class);
*/
        System.out.println(departmentMapper);

//        1、插入几个部门
        Department department = new Department();
        departmentMapper.insertSelective(new Department(null,"开发部"));
        departmentMapper.insertSelective(new Department(null,"测试部"));
//      2、生成员工数据
        employeeMapper.insert(new Employee(null,"jerry","m","231@qq.com",1));
//        批量插入多个员工，批量，使用可以执行批量操作的sqlSession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i = 0;i<1000;i++){
            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@atalei.com",1));
        }
    }
}
