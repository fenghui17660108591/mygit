package com.jxd.emp.model;

/**
 * @ClassName Dept
 * @Description TODO
 * @Author fenghui
 * @Date 2023/1/16
 * @Version 1.0
 */
public class Dept {
    private Integer deptno;
    private String dname;

    public Dept(Integer deptno, String dname) {
        this.deptno = deptno;
        this.dname = dname;
    }

    public Dept() {
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                '}';
    }
}
