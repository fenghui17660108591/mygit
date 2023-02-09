package com.jxd.emp.model;

/**
 * @ClassName Emp
 * @Description TODO
 * @Author fenghui
 * @Date 2023/1/16
 * @Version 1.0
 */
public class Emp {
    private Integer empno;
    private String ename;
    private String sex;
    private String tel;
    private Integer deptno;
    private String job;
    private String password;

    public Emp() {
    }

    public Emp(Integer empno, String ename, String sex, String tel, Integer deptno, String job, String password) {
        this.empno = empno;
        this.ename = ename;
        this.sex = sex;
        this.tel = tel;
        this.deptno = deptno;
        this.job = job;
        this.password = password;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", deptno=" + deptno +
                ", job='" + job + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
