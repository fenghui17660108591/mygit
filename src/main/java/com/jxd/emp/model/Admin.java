package com.jxd.emp.model;

/**
 * @ClassName Admin
 * @Description TODO
 * @Author fenghui
 * @Date 2023/2/3
 * @Version 1.0
 */
public class Admin {
    private String ename;
    private String password;

    public Admin() {
    }

    public Admin(String ename, String password) {
        this.ename = ename;
        this.password = password;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
