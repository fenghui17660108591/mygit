package com.jxd.emp.service;

import com.jxd.emp.model.WorkReporting;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public interface IManagerService {

    /**
     * 得到当前经理的部门
     */
    String getDeptnoByEname(String ename);


    List<List<WorkReporting>> getWorkReportingByDeptno(Integer deptno);


    List<Map<String, Object>> getInfoByDateAndPname(Map<String, String> map);

    Boolean adopt(Map<String, String> map);

    Boolean adopts(List<Map<String, String>> map);


    Boolean fail(Map<String, String> map);

    Boolean fails(List<Map<String, String>> map);

    /**
     * 获取部门所有报工信息
     * @param queryMap
     * @return
     */
    Map<String, Object> getAllInfoByDeptno(Map<String,String> queryMap);

    Map<String, Object> getAllProcessByDeptno(Map<String,String> queryMap);


    List<Map<String, Object>> getAllProcessCount(String ename);


    List<List<Map<String, Object>>> getMonthlyOver(Map<String, String> map);
}
