package com.jxd.emp.controller;

import com.jxd.emp.model.Dept;
import com.jxd.emp.model.Emp;
import com.jxd.emp.model.Project;
import com.jxd.emp.model.WorkReporting;
import com.jxd.emp.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName EmpController
 * @Description TODO
 * @Author fenghui
 * @Date 2023/1/16
 * @Version 1.0
 */
@RestController
public class EmpController {
    @Autowired
    private IEmpService empService;

    //改密码
    @RequestMapping("/setPassword")
    public String setPassword(@RequestBody Map<String,String> map) {
        String msg;
        if (empService.setPassword(map)){
            msg = "success";
        }else {
            msg = "error";
        }
        return msg;
    }


    //登录
    @RequestMapping("/login")
    public String login(@RequestBody Emp emp) {
        String msg;
        Emp empFound = empService.login(emp);
        if (empFound == null){
            msg = "error";
        }else {
            if ("经理".equals(empFound.getJob())){
                msg = "managerSuccess";
            }else {
                msg = "empSuccess";
            }
        }
        return msg;
    }


    //新增报工信息
    @RequestMapping("/filling")
    public String filling(@RequestBody Map<String, Object> map){
        String msg = "error";
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
        String now = df.format(System.currentTimeMillis());
        //修改工作量
        String workload = "1";
        if ("请假".equals(map.get("attendance"))){
            workload = "0";
        }

        WorkReporting workReporting = new WorkReporting(
                null,
                (String)map.get("date"),
                now,
                (String) map.get("attendance"),
                workload,
                Double.valueOf( map.get("overtime").toString()),
                (String)map.get("remarks"),
                (Integer) map.get("pno"),
                Integer.parseInt((String)map.get("state")),
                empService.getEmpIdByName((String) map.get("ename")),
                null,
                null,
                null
        );
        if (empService.addWorkReporting(workReporting)){
            msg = "success";
        }
        return msg;
    }

    //查询当前员工的漏报信息
    @RequestMapping("/getMissInfo")
    public List<WorkReporting> getMissInfo( String ename){
        return empService.getMissInfo(ename);
    }

    //查询当前员工的被退回信息
    @RequestMapping("/getReturnInfo")
    public List<WorkReporting> getReturnInfo( String ename){
        List<WorkReporting> a = empService.getReturnInfo(ename);
        return empService.getReturnInfo(ename);
    }

    //根据日期查询当前员工的被退回信息
    @RequestMapping("/getMissInfoByDate")
    public Map<String,Object> getMissInfoByDate(@RequestBody Map<String, String> map){
       return  empService.selectMissInfoByDate(empService.getEmpIdByName(map.get("ename")), map.get("date"));
    }

    /**
     *
     * @param queryMap 六个参数  limit  page  date  project ename year
     * @return  返回数据中包括 data:所有列表数据， count总条数
     */
    @RequestMapping("/getWorkReportingList")
    public Map<String,Object> getWorkReportingList(@RequestBody Map<String,String> queryMap){
        return empService.getWorkReportingList(queryMap);
    }

    //选择日期范围
    @RequestMapping("/getDateRange")
    public  List<Map<String,Object>>  getDateRange(String ename){
        return empService.getDateRange(ename);
    }

    //查询到最近三天的报工信息
    @RequestMapping("/getLastTimeProject")
    public  List<Map<String,Object>>  getLastTimeProject(@RequestBody Map<String,String> map){
        return empService.getLastTimeProject(map);
    }

    //获得项目列表
    @RequestMapping("/getProjectList")
    public List<Project> getProjectList(@RequestBody Map<String,String> queryMap){
        return empService.getProjectList(queryMap);
    }

    //获取项目名字
    @RequestMapping("/getPnameById")
    public String getPnameById(Integer pno){
        return empService.getPnameById(pno);
    }
}
