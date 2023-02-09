package com.jxd.emp.config;

import com.jxd.emp.model.WorkReporting;
import com.jxd.emp.service.IEmpService;
import com.jxd.emp.service.impl.EmpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.ws.Service;
import java.security.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @ClassName Corn 配置定时任务类
 * @Description TODO
 * @Author fenghui
 * @Date 2023/1/28
 * @Version 1.0
 */
@Configuration
public class Corn {
    @Autowired
    private IEmpService empService;

    @Scheduled(cron = "0 31 09 ? * MON-FRI")
    public void Scheduled(){
        //周一到周五每天上午十点30向数据库插入每个员工的报工信息（未报状态）
        empService.addInfoEveryDay();
    }
}
