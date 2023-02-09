package com.jxd.emp.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxd.emp.dao.AdminMapper;
import com.jxd.emp.dao.EmpMapper;
import com.jxd.emp.model.Admin;
import com.jxd.emp.model.Dept;
import com.jxd.emp.model.Emp;
import com.jxd.emp.model.Project;
import com.jxd.emp.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author fenghui
 * @Date 2023/2/3
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(Admin admin) {
        return adminMapper.getAdminByName(admin.getEname(), admin.getPassword());
    }

    @Override
    public Map<String, Object> emplist(Map<String, String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        String ename = queryMap.get("ename");
        String deptnos = queryMap.get("deptno");
        int deptno = 0;
        if(!"".equals(deptnos)){
            deptno = Integer.parseInt(deptnos);
        }

        Map<String, Object> map = new HashMap(4);
        if(page != null && limit != null){
            IPage<Emp> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<Emp> pageResult = adminMapper.emplist(pages,ename,deptno);
            map.put("count",pageResult.getTotal());
            map.put("data",pageResult.getRecords());
            map.put("code",0);
            return map;
        }else{
            map.put("data", (Object)null);
            map.put("code", "500");
            map.put("msg", "参数不符");
            map.put("count", (Object)null);
            return map;
        }
    }

    @Override
    public List<Dept> deptlist() {
        return adminMapper.deptlist();
    }

    @Override
    public boolean delEmp(List<Integer> empnos) {
        return adminMapper.delEmp(empnos);
    }

    @Override
    public boolean updateEmp(Emp emp) {
        return adminMapper.updateEmp(emp);
    }

    @Override
    public boolean addEmp(Emp emp) {
        return adminMapper.addEmp(emp);
    }

    @Override
    public boolean resetpassword(List<Integer> empnos) {
        return adminMapper.resetpassword(empnos);
    }

    @Override
    public List<Map<String, Object>> deptlistinfo(Map<String, String> map) {
        return adminMapper.deptlistinfo(map);
    }

    @Override
    public boolean addDept(Map<String, String> map) {
        return adminMapper.addDept(map);
    }

    @Override
    public boolean updateDept(Dept dept) {
        return adminMapper.updateDept(dept);
    }

    @Override
    public boolean delDept(List<Integer> deptnos) {
        return adminMapper.delDept(deptnos);
    }

    @Override
    public Map<String, Object> projectlist(Map<String, String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        String pname = queryMap.get("pname");
        String deptnos = queryMap.get("deptno");
        int deptno = 0;
        if(!"".equals(deptnos)){
            deptno = Integer.parseInt(deptnos);
        }

        Map<String, Object> map = new HashMap(4);
        if(page != null && limit != null){
            IPage<Emp> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<Emp> pageResult = adminMapper.projectlist(pages,pname,deptno);
            map.put("count",pageResult.getTotal());
            map.put("data",pageResult.getRecords());
            map.put("code",0);
            return map;
        }else{
            map.put("data", (Object)null);
            map.put("code", "500");
            map.put("msg", "参数不符");
            map.put("count", (Object)null);
            return map;
        }
    }

    @Override
    public boolean delproject(List<Integer> projects) {
        return adminMapper.delproject(projects);
    }

    @Override
    public boolean addProject(Map<String, String> map) {
        return adminMapper.addProject(map);
    }

    public boolean updateProject(Project project) {
        return adminMapper.updateProject(project);
    }
}
