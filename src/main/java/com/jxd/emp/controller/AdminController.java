package com.jxd.emp.controller;

import com.jxd.emp.model.Admin;
import com.jxd.emp.model.Dept;
import com.jxd.emp.model.Emp;
import com.jxd.emp.model.Project;
import com.jxd.emp.service.IAdminService;
import com.jxd.emp.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author fenghui
 * @Date 2023/2/3
 * @Version 1.0
 */
@RestController
public class AdminController {


    @Autowired
    private IAdminService adminService;

    @RequestMapping("/adminLogin")
    public String adminLogin(@RequestBody Admin admin) {
        String msg;
        Admin adminFound = adminService.login(admin);
        if (adminFound == null){
            msg = "error";
        }else {
            msg = "success";
        }
        return msg;
    }

    /**
     *
     * @param map 四个参数  limit  page  ename  deptno
     * @return  返回数据中包括 data:所有列表数据， count总条数
     */
    @RequestMapping("/emplist")
    public Map<String,Object> emplist(@RequestBody Map<String,String> map){
        return adminService.emplist(map);
    }

    @RequestMapping("/deptlist")
    public List<Dept> deptlist(){
        return adminService.deptlist();
    }


    @RequestMapping("/delbatch")
    public String delEmp(@RequestBody List<Integer> empnos){
        if(adminService.delEmp(empnos)){
            return "success";
        }else{
            return "error";
        }
    }


    @RequestMapping("/updateEmp")
    public String updateEmp(@RequestBody Emp emp){
        if(adminService.updateEmp(emp)){
            return "success";
        }else{
            return "error";
        }
    }


    @RequestMapping("/addEmp")
    public String addEmp(@RequestBody Emp emp){
        if(adminService.addEmp(emp)){
            return "success";
        }else{
            return "error";
        }
    }


    @RequestMapping("/resetpassword")
    public String resetpassword(@RequestBody List<Integer> empnos){
        if(adminService.resetpassword(empnos)){
            return "success";
        }else{
            return "error";
        }
    }


    @RequestMapping("/deptlistinfo")
    public List<Map<String,Object>> deptlistinfo(@RequestBody Map<String,String> map){
        return adminService.deptlistinfo(map);
    }


    @RequestMapping("/addDept")
    public String addDept(@RequestBody Map<String,String> map){
        if(adminService.addDept(map)){
            return "success";
        }else{
            return "error";
        }
    }


    @RequestMapping("/updatedept")
    public String updatedept(@RequestBody Dept dept){
        if(adminService.updateDept(dept)){
            return "success";
        }else{
            return "error";
        }
    }

    @RequestMapping("/deldept")
    public String delDept(@RequestBody List<Integer> deptnos){
        if(adminService.delDept(deptnos)){
            return "success";
        }else{
            return "error";
        }
    }


    @RequestMapping("/projectlist")
    public Map<String,Object> projectlist(@RequestBody Map<String,String> map){
        return adminService.projectlist(map);
    }



    @RequestMapping("/delproject")
    public String delproject(@RequestBody List<Integer> projets){
        if(adminService.delproject(projets)){
            return "success";
        }else{
            return "error";
        }
    }

    @RequestMapping("/addProject")
    public String addProject(@RequestBody Map<String,String> map){
        if(adminService.addProject(map)){
            return "success";
        }else{
            return "error";
        }
    }

    @RequestMapping("/updateProject")
    public String updateProject(@RequestBody Project project){
        if(adminService.updateProject(project)){
            return "success";
        }else{
            return "error";
        }
    }
}
