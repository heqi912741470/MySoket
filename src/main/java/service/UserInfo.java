package service;

public class UserInfo {
    private String name;//用户名
    private String password;//密码
    private String loignTime;//登录时间
    private String addRess;//客户机端口名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoignTime() {
        return loignTime;
    }

    public void setLoignTime(String loignTime) {
        this.loignTime = loignTime;
    }

    public String getAddRess() {
        return addRess;
    }

    public void setAddRess(String addRess) {
        this.addRess = addRess;
    }
}
