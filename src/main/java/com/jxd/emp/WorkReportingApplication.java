package com.jxd.emp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName EmpApplication
 * @Description TODO
 * @Author lixiaoru
 * @Date 2020/11/18
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.jxd.emp.dao")
@EnableScheduling//定时任务注解
public class WorkReportingApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkReportingApplication.class);
    }
}
