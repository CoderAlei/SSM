package com.alei.crud.controller;


import com.alei.crud.bean.Department;
import com.alei.crud.bean.Msg;
import com.alei.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//处理部门有关的请求
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

//    返回所有的部门信息
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> list=departmentService.getDepts();
        System.out.println(list);
        return Msg.Success().add("depts",list);
    }
}
