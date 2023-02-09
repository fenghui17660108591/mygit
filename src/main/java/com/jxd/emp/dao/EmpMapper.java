package com.jxd.emp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxd.emp.model.Dept;
import com.jxd.emp.model.Emp;
import com.jxd.emp.model.Project;
import com.jxd.emp.model.WorkReporting;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmpMapper extends BaseMapper<Emp> {



    boolean setPassword(@Param("map") Map<String,String> map);

    /**
     *  查询用户信息以及职位
     * @param ename 用户名
     * @param password 用户密码
     * @return
     */
    Emp getEmpByName(@Param("ename") String ename, @Param("password") String password);

    /**
     *  向数据库中插入未报工信息
     * @param listWork
     */
    void addInfoEveryDay(@Param("listWork") List<WorkReporting> listWork);

    /**
     * 查询所有员工的名字
     * @return
     */
    List<Emp> selectAllEmp();

    /**
     * 得到当前用户的id
     */
    Integer getEmpIdByName(@Param("ename") String ename);

    /**
     * 得到当前用户的漏报信息
     * @param empno
     * @return
     */
    List<WorkReporting> selectMissInfo(@Param("empno") Integer empno);

    /**
     * 得到当前用户的被退回信息
     * @param empno
     * @return
     */
    List<WorkReporting> selectReturnInfo(@Param("empno") Integer empno);

    /**
     * 员工新增报工信息
     * @param workReporting
     * @return
     */
    boolean insertWorkReporting(@Param("workReporting") WorkReporting workReporting);

    /**
     * 根据日期查询员工漏报信息
     * @param empno
     * @param date
     * @return
     */
    Map<String,Object> selectMissInfoByDate(@Param("empno") Integer empno,@Param("date") String date);

    /**
     * 获取报工信息
     * @param page
     * @param date
     * @param pname
     * @param ename
     * @param year
     * @return
     */
    IPage<Map<String,String>> getWorkReportingList(IPage<Map<String,String>> page,
                                                   @Param("date") String date,
                                                   @Param("pname") String pname,
                                                   @Param("ename") String ename,
                                                   @Param("year") String year);

    /**
     * 获取日期选择范围（当天以及漏报当天）
     * @return
     */
    List<Map<String,Object>> selectDateRange(Integer empno);

    /**
     * 获取最近三天的上报记录，用于渲染默认值
     * @param empno
     * @param timelist
     * @return
     */
    List<Map<String,Object>> getLastTimeProject(@Param("empno") Integer empno,@Param("timelist") List<String> timelist);

    /**
     * 获取项目的信息
     * @param pname
     * @param pno
     * @return
     */
    List<Project> getProjectList(@Param("pname") String pname, @Param("pno") Integer pno);

    /**
     * 根据pno获取项目名字
     * @param pno
     * @return
     */
    String getPnameById(@Param("pno") Integer pno);

    /**
     * 根据经理名字获取部门
     * @param ename
     * @return
     */
    String getDeptnoByEname(@Param("ename") String ename);

    /**
     * 根据部门获取报工信息
     * @param deptno
     * @return
     */
    List<WorkReporting> getWorkReportingByDeptno(@Param("deptno")Integer deptno,@Param("date") String date);


    /**
     * 根据日期和部门名字获取信息
     * @param
     * @param
     * @return
     */
    List<Map<String, Object>> getInfoByDateAndPname(@Param("map") Map<String, String> map);


    boolean adopt(@Param("map") Map<String, String> map, @Param("audittime") String audittime);

    boolean adopts(@Param("map") List<String> map, @Param("audittime") String audittime,@Param("reviewer") String reviewer);

    boolean fail(@Param("map") Map<String, String> map, @Param("audittime") String audittime);

    boolean fails(@Param("map") List<String> map, @Param("audittime") String audittime,@Param("reviewer") String reviewer,@Param("reason") String reason);

    String getDnameByEname(@Param("ename") String ename);

    /**
     * 获取部门所有的报工信息
     * @param page
     * @param date1
     * @param date2
     * @param dname
     * @param ename
     * @return
     */
    IPage<Map<String,String>> getAllInfoByDeptno(IPage<Map<String,String>> page,
                                                   @Param("date1") String date1,
                                                   @Param("date2") String date2,
                                                   @Param("dname") String dname,
                                                   @Param("ename") String ename);




    IPage<Map<String,String>> getAllProcessByDeptno(IPage<Map<String,String>> page,
                                                 @Param("date") String date,
                                                 @Param("dname") String dname,
                                                 @Param("ename") String ename);

    List<Map<String, Object>> getAllProcessCount(@Param("dname") String dname);

    List<String> getAllEnameByDeptno(@Param("deptno") String deptno);


    List<Map<String, Object>> getMonthlyOver(@Param("map") Map<String, String> map);




}
