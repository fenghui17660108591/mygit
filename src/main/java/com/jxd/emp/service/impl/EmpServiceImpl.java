package com.jxd.emp.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.emp.dao.EmpMapper;
import com.jxd.emp.model.Dept;
import com.jxd.emp.model.Emp;
import com.jxd.emp.model.Project;
import com.jxd.emp.model.WorkReporting;
import com.jxd.emp.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName EmpServiceImpl
 * @Description TODO
 * @Author fenghui
 * @Date 2023/1/16
 * @Version 1.0
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IEmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public boolean setPassword(Map<String, String> map) {
        return empMapper.setPassword(map);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getEmpByName(emp.getEname(), emp.getPassword());
    }

    /**
     * 向数据库中插入未报工信息
     * @param
     */
    @Override
    public void addInfoEveryDay() {
        //获取当前日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(System.currentTimeMillis());
        List<WorkReporting> listWork = new ArrayList<>();
        List<Emp> list = empMapper.selectAllEmp();
        //将每个员工的编号添加到报工信息，并生成报工信息集合
        for (Emp emp : list) {
            WorkReporting workReporting1 = new WorkReporting(
                    null, now, null, null, null, null, null, null, -1, emp.getEmpno(),null,null,null
            );
            listWork.add(workReporting1);
        }
        empMapper.addInfoEveryDay(listWork);
    }

    @Override
    public Integer getEmpIdByName(String ename) {
        return empMapper.getEmpIdByName(ename);
    }

    @Override
    public List<WorkReporting> getMissInfo(String ename) {
        return empMapper.selectMissInfo(empMapper.getEmpIdByName(ename));
    }

    @Override
    public List<WorkReporting> getReturnInfo(String ename) {
        return empMapper.selectReturnInfo(empMapper.getEmpIdByName(ename));
    }

    @Override
    public boolean addWorkReporting(WorkReporting workReporting) {
        return empMapper.insertWorkReporting(workReporting);
    }

    @Override
    public Map<String, Object> selectMissInfoByDate(Integer empno, String date) {
        return empMapper.selectMissInfoByDate(empno, date) ;
    }

    @Override
    public Map<String, Object> getWorkReportingList(Map<String, String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        String date = queryMap.get("date");
        String pname = queryMap.get("pname");
        String ename = queryMap.get("ename");
        String year = queryMap.get("year");

        Map<String, Object> map = new HashMap(5);
        if(page != null && limit != null){
            IPage<Map<String,String>> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<Map<String,String>> pageResult = empMapper.getWorkReportingList(pages,date,pname,ename, year);
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
    public List<Map<String,Object>> getDateRange(String ename) {
        return empMapper.selectDateRange(empMapper.getEmpIdByName(ename));
    }

    @Override
    public List<Map<String, Object>> getLastTimeProject(Map<String, String> map) {
        List<String> timelist = new ArrayList<>();//用于存放前三天的日期
        String ename = map.get("ename");
        String date = map.get("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        try {
            Date nowdate = format.parse(date);//转换成日期格式
            Calendar calendar = Calendar.getInstance(); // 使用Calendar日历类对日期进行加减
            calendar.setTime(nowdate);
            for (int i = 0; i < 3; i++) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);//获取前1天的日期
                Date dt1=calendar.getTime();//得到前1天日期
                String previousdate = format.format(dt1);  //日期转字符串
                timelist.add(previousdate);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return empMapper.getLastTimeProject(empMapper.getEmpIdByName(ename), timelist);
    }

    @Override
    public List<Project> getProjectList(Map<String, String> queryMap) {
        String pname = queryMap.get("pname");
        String pno =queryMap.get("pno");
        if (pno == ""){
            pno = "0";
        }

       return empMapper.getProjectList(pname,Integer.parseInt(pno));
    }

    @Override
    public String getPnameById(Integer pno) {
        return empMapper.getPnameById(pno);
    }
}
