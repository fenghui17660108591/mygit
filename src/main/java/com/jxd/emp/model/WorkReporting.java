package com.jxd.emp.model;

/**
 * @ClassName WorkReporting
 * @Description TODO
 * @Author fenghui
 * @Date 2023/1/16
 * @Version 1.0
 */
public class WorkReporting {
    private Integer wno;
    private String date;
    private String time;
    private String attendance;
    private String workload;
    private Double overtime;
    private String remarks;
    private Integer pno;
    private Integer state;
    private Integer empno;
    private String reviewer;
    private String audittime;
    private  String reason;

    public WorkReporting() {
    }

    public WorkReporting(Integer wno, String date, String time, String attendance, String workload, Double overtime, String remarks, Integer pno, Integer state, Integer empno, String reviewer, String audittime, String reason) {
        this.wno = wno;
        this.date = date;
        this.time = time;
        this.attendance = attendance;
        this.workload = workload;
        this.overtime = overtime;
        this.remarks = remarks;
        this.pno = pno;
        this.state = state;
        this.empno = empno;
        this.reviewer = reviewer;
        this.audittime = audittime;
        this.reason = reason;
    }

    public Integer getWno() {
        return wno;
    }

    public void setWno(Integer wno) {
        this.wno = wno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public Double getOvertime() {
        return overtime;
    }

    public void setOvertime(Double overtime) {
        this.overtime = overtime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getPno() {
        return pno;
    }

    public void setPno(Integer pno) {
        this.pno = pno;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getAudittime() {
        return audittime;
    }

    public void setAudittime(String audittime) {
        this.audittime = audittime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "WorkReporting{" +
                "wno=" + wno +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", attendance='" + attendance + '\'' +
                ", workload='" + workload + '\'' +
                ", overtime=" + overtime +
                ", remarks='" + remarks + '\'' +
                ", pno=" + pno +
                ", state=" + state +
                ", empno=" + empno +
                ", reviewer='" + reviewer + '\'' +
                ", audittime='" + audittime + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
