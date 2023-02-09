package com.jxd.emp.service;

import com.jxd.emp.model.Admin;
import com.jxd.emp.model.Dept;
import com.jxd.emp.model.Emp;
import com.jxd.emp.model.Project;
import org.apache.ibatis.annotations.Param;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface IAdminService {

    /**
     * 登录
     * @param admin
     * @return
     */
    Admin login(Admin admin);


    Map<String, Object> emplist(Map<String,String> queryMap);

    List<Dept> deptlist();

    boolean delEmp(List<Integer> empnos);

    boolean updateEmp(Emp emp);

    boolean addEmp(Emp emp);

    boolean resetpassword(List<Integer> empnos);

    List<Map<String, Object>> deptlistinfo(Map<String, String> map);

    boolean addDept(Map<String, String> map);

    boolean updateDept(Dept dept);

    boolean delDept(List<Integer> deptnos);

    Map<String, Object> projectlist(Map<String,String> queryMap);

    boolean delproject(List<Integer> projects);

    boolean addProject(Map<String, String> map);

    boolean updateProject(Project project);
}
