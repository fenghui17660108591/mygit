package com.jxd.emp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.emp.model.Dept;
import com.jxd.emp.model.Emp;
import com.jxd.emp.model.Project;
import com.jxd.emp.model.WorkReporting;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IEmpService extends IService<Emp> {

    boolean setPassword(Map<String, String> map);

    /**
     * 登录
     * @param emp
     * @return
     */
    Emp login(Emp emp);

    /**
     * 每工作日系统自动新增报工信息
     */
    void addInfoEveryDay();

    /**
     * 得到当前用户的id
     */
    Integer getEmpIdByName(String ename);

    /**
     * 获取当前员工的漏报信息
     * @param ename
     * @return
     */
    List<WorkReporting> getMissInfo(String ename);

    /**
     * 获取当前员工的被退回信息
     * @param ename
     * @return
     */
    List<WorkReporting> getReturnInfo(String ename);

    /**
     * 员工新增报工信息
     * @param workReporting
     * @return
     */
    boolean addWorkReporting(WorkReporting workReporting);

    /**
     * 获取根据日期查询漏报信息
     * @param empno
     * @param date
     * @return
     */
    Map<String, Object> selectMissInfoByDate(Integer empno,String date);

    /**
     * 获取报工信息
     * @param queryMap
     * @return
     */
    Map<String, Object> getWorkReportingList(Map<String,String> queryMap);

    /**
     * 获取日期选择范围（当天以及漏报当天）
     * @return
     */
    List<Map<String,Object>> getDateRange(String ename);


    List<Map<String,Object>> getLastTimeProject(Map<String,String> map);


    List<Project> getProjectList(Map<String,String> queryMap);

    String getPnameById(Integer pno);
}



