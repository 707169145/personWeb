package cn.yimi.dto;

public class UserInfo {
    private static final long serialVersionUID = 1L;

    // 用户id
    private String empid;
    // 密码
    private String password;
    // 状态
    private String stat;
    // 用户名
    private String empName;
    // 手机号码
    private String empPhone;
    // 电子邮箱
    private String empEmail;
    // qq
    private String empQQ;
    // 性别
    private String empSex;
    // 角色
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getEmpPhone() {
        return empPhone;
    }
    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }
    public String getEmpEmail() {
        return empEmail;
    }
    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }
    public String getEmpQQ() {
        return empQQ;
    }
    public void setEmpQQ(String empQQ) {
        this.empQQ = empQQ;
    }
    public String getEmpSex() {
        return empSex;
    }
    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }
    public String getEmpid() {
        return empid;
    }
    public void setEmpid(String empid) {
        this.empid = empid;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getStat() {
        return stat;
    }
    public void setStat(String stat) {
        this.stat = stat;
    }
}
