package com.jxd.emp.model;

/**
 * @ClassName Project
 * @Description TODO
 * @Author fenghui
 * @Date 2023/1/16
 * @Version 1.0
 */
public class Project {
    private Integer pno;
    private String pname;
    private Integer deptno;

    public Project() {
    }

    public Project(Integer pno, String pname, Integer deptno) {
        this.pno = pno;
        this.pname = pname;
        this.deptno = deptno;
    }

    public Integer getPno() {
        return pno;
    }

    public void setPno(Integer pno) {
        this.pno = pno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Project{" +
                "pno=" + pno +
                ", pname='" + pname + '\'' +
                ", deptno=" + deptno +
                '}';
    }
}
