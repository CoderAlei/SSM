package com.alei.crud.controller;


import com.alei.crud.bean.Employee;
import com.alei.crud.bean.Msg;
import com.alei.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//处理员工CRUD请求

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    /*
    查询员工数据（分页查询）
     */
//    @RequestMapping("/emps")
//    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model) {
////        这部是一个分页查询
//
////        引入pageHelper分页插件,
////        在查询之前只需要调用，传入页码，以及每页的大小
//        PageHelper.startPage(pn,5);
//        //startPage后面紧跟的这个查询就是分页查询
//        List<Employee> emps = employeeService.getAll();
//        for (Employee e:emps
//             ) {
//            System.out.println("::::::::::::::::::::::"+emps.toString());
//        }
////        使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就可以了,只需要将pageinfo交给前端页面
////        封装了详细的分页信息，包括有查询出来的数据，连续传入5页。
//        PageInfo pageInfo = new PageInfo(emps,5);
////        System.out.println(pageInfo.toString());
//        model.addAttribute("pageInfo",pageInfo);
//        return "list";
//    }

    public String getEmps(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn,
            Model model) {
        // 这不是一个分页查询；
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(emps, 5);
        model.addAttribute("pageInfo", page);
        System.out.println("getEmps");
        return "list";
    }

//    员工

    //检查用户名字是否可以用
//    保存员工
//   支持JSR 303校验
//    导入hibernate-validate

    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkUse(@RequestParam("empName") String empName){
//        先判断用户名是否是合法表达式
        String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})/";
        boolean matches = empName.matches(regx);
        if(matches==false){
            return Msg.fail().add("va_msg","用户名必须是6-16为字母");
        }

        System.out.println("checkUse");
        //数据库用户名重复校验
        boolean b=employeeService.checkUser(empName);

       if(b){
           return Msg.Success();
       }else {
           return Msg.fail();
       }

}

    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(
            @RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,5);
        //startPage后面紧跟的这个查询就是分页查询
        List<Employee> emps = employeeService.getAll();

//        使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就可以了,只需要将pageinfo交给前端页面
//        封装了详细的分页信息，包括有查询出来的数据，连续传入5页。
        PageInfo pageInfo = new PageInfo(emps,5);
//        System.out.println(pageInfo.toString());
        pageInfo.hashCode();
        System.out.println("getEmpsWithJson");
        return Msg.Success().add("pageInfo",pageInfo);
    }


    //员工保存
    @RequestMapping(value="/emp",method=RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee,BindingResult result){
        System.out.println("saveEmp");

        if(result.hasErrors()){
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }else{
            System.out.println("进来了");
            employeeService.saveEmp(employee);
            return Msg.Success();
        }


    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable Integer id){
        System.out.println("getEmp");
        Employee employee = employeeService.getEmp(id);
        return Msg.Success().add("emp",employee);
    }


//    如果直接发送ajax=put请求
//    请求体中有数据
//    但是Employee对象封装不上
//    update tbl_emp
//    原因tomcat的问题
//    请求体中的数据，封装成一个map，requestmap（“empName")就会从中获取这个取值
//    SpringMVC封装pojo对象的时候，会把每个属性值拿到。
    //AJAX不能直接发put
    //put请求，request.getParameter("empName")拿不到，Tomcat一看put不会封装请求体为map
    //配置上filter springmvc HttpPutFormContentFilter将请求体中的数据包包装成一个map，request被重新包装
    //request被重新包装，request.getPara被重写，从新封装成功。
    //员工 更新方法
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)//更新员工
    @ResponseBody
    public Msg saveEmp(Employee employee){
        System.out.println("saveEmp");
        employeeService.updateEmp(employee);
        return Msg.Success();
    }


    //单个批量二合一
//    批量 用横岗隔开
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmpById(@PathVariable("ids")String ids){
        if(ids.contains("-"))//批量删除
        {
            List<Integer> del_ids=new ArrayList<>();
            //多个删除
            String[] split = ids.split("-");
            //组装id集合
            for (String str:split){
                System.out.println(str);
                del_ids.add(Integer.parseInt(str));
            }
            employeeService.deleteBatch(del_ids);
        }
        else{
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        System.out.println("进来了");
        return Msg.Success();
    }


}
