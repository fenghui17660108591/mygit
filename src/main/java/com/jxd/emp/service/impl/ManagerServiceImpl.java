package com.jxd.emp.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.emp.dao.EmpMapper;
import com.jxd.emp.model.Emp;
import com.jxd.emp.model.WorkReporting;
import com.jxd.emp.service.IEmpService;
import com.jxd.emp.service.IManagerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName ManagerServiceImpl
 * @Description TODO
 * @Author fenghui
 * @Date 2023/2/1
 * @Version 1.0
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IManagerService {

    @Autowired
    private EmpMapper empMapper;


    @Override
    public String getDeptnoByEname(String ename) {
        return empMapper.getDeptnoByEname(ename);
    }

    @Override
    public List<List<WorkReporting>> getWorkReportingByDeptno(Integer deptno) {
        List<List<WorkReporting>> list = new ArrayList<>();//
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

        Date date = new Date(System.currentTimeMillis());//得到今天日期
        String nowdate = format.format(date);//日期转字符串
        List<WorkReporting> list1 = empMapper.getWorkReportingByDeptno(deptno,nowdate);
        list.add(list1);
        Calendar calendar = Calendar.getInstance(); // 使用Calendar日历类对日期进行加减
        calendar.setTime(date);
        for (int i = 0; i < 2; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);//获取前1天的日期
            Date dt1=calendar.getTime();//得到前1天日期
            String previousdate = format.format(dt1);  //日期转字符串
            List<WorkReporting> list2 = empMapper.getWorkReportingByDeptno(deptno,previousdate);
            list.add(list2);
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> getInfoByDateAndPname(Map<String, String> map) {
        return empMapper.getInfoByDateAndPname(map);
    }

    @Override
    public Boolean adopt(Map<String, String> map) {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
        String now = df.format(System.currentTimeMillis());
         return  empMapper.adopt(map, now);
    }

    @Override
    public Boolean adopts(List<Map<String, String>> map) {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
        String now = df.format(System.currentTimeMillis());
        List<String> list = new ArrayList<>();
        String reviewer = "";
        for(Map<String, String> m : map) {
            list.add(m.get("wno"));
            reviewer = m.get("reviewer");
        }
        return  empMapper.adopts(list, now, reviewer);
    }

    @Override
    public Boolean fail(Map<String, String> map) {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
        String now = df.format(System.currentTimeMillis());
        return empMapper.fail(map, now);
    }

    @Override
    public Boolean fails(List<Map<String, String>> map) {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
        String now = df.format(System.currentTimeMillis());
        List<String> list = new ArrayList<>();
        String reason = "";
        String reviewer = "";
        for(Map<String, String> m : map) {
            list.add(m.get("wno"));
            reviewer = m.get("reviewer");
            reason = m.get("reason");
        }
        return empMapper.fails(list, now, reviewer,reason);
    }

    @Override
    public Map<String, Object> getAllInfoByDeptno(Map<String, String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        String date1 = queryMap.get("date1");
        String date2 = queryMap.get("date2");
        String dname = empMapper.getDnameByEname(queryMap.get("managerename")) ;
        String ename = queryMap.get("ename");

        Map<String, Object> map = new HashMap(5);
        if(page != null && limit != null){
            IPage<Map<String,String>> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<Map<String,String>> pageResult = empMapper.getAllInfoByDeptno(pages,date1, date2,dname,ename);
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
    public Map<String, Object> getAllProcessByDeptno(Map<String, String> queryMap) {
        String page = queryMap.get("page");
        String limit = queryMap.get("limit");
        String date = queryMap.get("date");
        String dname = empMapper.getDnameByEname(queryMap.get("managerename")) ;
        String ename = queryMap.get("ename");

        Map<String, Object> map = new HashMap(5);
        if(page != null && limit != null){
            IPage<Map<String,String>> pages = new Page(Integer.parseInt(page),Integer.parseInt(limit));
            IPage<Map<String,String>> pageResult = empMapper.getAllProcessByDeptno(pages,date, dname,ename);
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
    public List<Map<String, Object>> getAllProcessCount(String ename) {

        return empMapper.getAllProcessCount(empMapper.getDnameByEname(ename));
    }

    @Override
    public List<List<Map<String, Object>>> getMonthlyOver(Map<String, String> map) {
        List<Map<String, Object>> list = empMapper.getMonthlyOver(map);

        Map<String,List<Map<String,Object>>> a = new HashMap<>();
        list.forEach(
                e->{
                    String key = e.get("ename").toString();
                    if (a.get(key) == null){
                        List<Map<String, Object>> enameList = new ArrayList<>();
                        enameList.add(e);
                        a.put(key,enameList);
                    }else {
                        List<Map<String,Object>> o = a.get(key);
                        o.add(e);
                        a.put(key,o);
                    }
                }
        );

        List<Map<String, Object>> resultMap = new ArrayList<>();
            a.forEach((k,v)->{
                Map<String, Object> enameMap = new HashMap<>();
                enameMap.put("ename",k);
                // k : ename名称 v ：data数组
                //解析不同的data list
              v.forEach(v2->{
                  Date date = (Date) v2.get("date");
                  if (date != null){
                      //时间类型转string
                      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");
                      String format = simpleDateFormat.format(date);
                      Object overtime =  v2.getOrDefault("overtime","漏报");
                      enameMap.put(format,overtime);
                  }
              });
                resultMap.add(enameMap);
            });

        List<Map<String, Object>> resultMap1 = new ArrayList<>();
        a.forEach((k,v)->{
            Map<String, Object> enameMap = new HashMap<>();
            enameMap.put("ename",k);
            // k : ename名称 v ：data数组
            //解析不同的data list
            v.forEach(v2->{
                Date date = (Date) v2.get("date");
                if (date != null){
                    //时间类型转string
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");
                    String format = simpleDateFormat.format(date);
                    Object workload =  v2.getOrDefault("workload","漏报");
                    enameMap.put(format,workload);
                }
            });
            resultMap1.add(enameMap);
        });

        List<List<Map<String, Object>>> resultList = new ArrayList<>();
        resultList.add(resultMap);
        resultList.add(resultMap1);

        return resultList;
    }

}
