package com.colin.anbet.net;

/**
 * @ProjectName: Colin
 * @Package: com.chai.colin.entity
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/9 10:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/9 10:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginBean {
    private String memberName;
    private String memberId;
    private String vipLevelName;
    private String availableFration;
    private String msg;
    private int status;
    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getVipLevelName() {
        return vipLevelName;
    }

    public String getAvailableFration() {
        return availableFration;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "memberName='" + memberName + '\'' +
                ", memberId='" + memberId + '\'' +
                ", vipLevelName='" + vipLevelName + '\'' +
                ", availableFration='" + availableFration + '\'' +
                ", msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
