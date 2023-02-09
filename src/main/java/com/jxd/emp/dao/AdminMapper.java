package com.jxd.emp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxd.emp.model.Admin;
import com.jxd.emp.model.Dept;
import com.jxd.emp.model.Emp;
import com.jxd.emp.model.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminMapper  extends BaseMapper<Admin> {

    /**
     *  查询用户信息以及职位
     * @param ename 用户名
     * @param password 用户密码
     * @return
     */
    Admin getAdminByName(@Param("ename") String ename, @Param("password") String password);
    IPage<Emp> emplist(IPage<Emp> page, @Param("ename") String ename, @Param("deptno") int deptno);

    List<Dept> deptlist();

    boolean delEmp(@Param("empnos") List<Integer> empnos);

    boolean updateEmp( @Param("emp") Emp emp);

    boolean addEmp( @Param("emp") Emp emp);


    boolean resetpassword(@Param("empnos") List<Integer> empnos);

    List<Map<String, Object>>  deptlistinfo(Map<String, String> map);

    boolean addDept(@Param("map")Map<String, String> map);

    boolean updateDept( @Param("dept") Dept dept);

    boolean delDept(@Param("deptnos") List<Integer> deptnos);

    IPage<Emp> projectlist(IPage<Emp> page, @Param("pname") String pname, @Param("deptno") int deptno);

    boolean delproject(@Param("projects") List<Integer> projects);

    boolean addProject(@Param("map")Map<String, String> map);

    boolean updateProject( @Param("project") Project project);
}
