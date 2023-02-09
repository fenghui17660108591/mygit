package com.jxd.emp.controller;

import com.jxd.emp.model.WorkReporting;
import com.jxd.emp.service.IEmpService;
import com.jxd.emp.service.IManagerService;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ManagerController
 * @Description TODO
 * @Author fenghui
 * @Date 2023/2/1
 * @Version 1.0
 */
@RestController
public class ManagerController {
    @Autowired
    private IManagerService managerService;


    //查询当前经理的部门
    @RequestMapping("/getDeptnoByEname")
    public String getDeptnoByEname(String ename){
        return managerService.getDeptnoByEname(ename);
    }

    //得到经理部门的员工报工信息，用于计算首页的报工统计
    @RequestMapping("/getWorkReportingByDeptno")
    public List<List<WorkReporting>> getWorkReportingByDeptno(Integer deptno){
        return managerService.getWorkReportingByDeptno(deptno);
    }

    //得到需要审核的部门报工信息
    @RequestMapping("/getInfoByDateAndPname")
    public List<Map<String, Object>> getInfoByDateAndPname(@RequestBody Map<String, String> map){
        return managerService.getInfoByDateAndPname(map);
    }

    //提交员工的报工
    @RequestMapping("/adopt")
    public String adopt(@RequestBody Map<String, String> map){
        String msg = "error";
        if (managerService.adopt(map)) {
            msg = "success";
        }
        return msg;
    }

    //批量提交员工的报工
    @RequestMapping("/adopts")
    public String adopts(@RequestBody List<Map<String, String>> map){
        String msg = "error";
        if (managerService.adopts(map)) {
            msg = "success";
        }
        return msg;
    }

    //退回员工的报工
    @RequestMapping("/fail")
    public String fail(@RequestBody Map<String, String> map){
        String msg = "error";
        if (managerService.fail(map)) {
            msg = "success";
        }
        return msg;
    }

    //批量退回员工的报工
    @RequestMapping("/fails")
    public String fails(@RequestBody List<Map<String, String>> map){
        String msg = "error";
        if (managerService.fails(map)) {
            msg = "success";
        }
        return msg;
    }

    /**
     *
     * @param queryMap 六个参数  limit  page  date  project ename year
     * @return  返回数据中包括 data:所有列表数据， count总条数
     */
    @RequestMapping("/getAllInfoByDeptno")
    public Map<String,Object> getAllInfoByDeptno(@RequestBody Map<String,String> queryMap){
        return managerService.getAllInfoByDeptno(queryMap);
    }

    //得到本部门全部的漏报信息
    @RequestMapping("/getAllProcessByDeptno")
    public Map<String,Object> getAllProcessByDeptno(@RequestBody Map<String,String> queryMap){
        return managerService.getAllProcessByDeptno(queryMap);
    }

    //统计累计漏报
    @RequestMapping("/getAllProcessCount")
    public List<Map<String, Object>> getAllProcessCount(String ename){
        return managerService.getAllProcessCount(ename);
    }

    //获取月度总览
    @RequestMapping("/getMonthlyOver")
    public List<List<Map<String, Object>>> getMonthlyOver(@RequestBody Map<String, String> map){
        return managerService.getMonthlyOver(map);
    }
}
