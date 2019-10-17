package com.colin.anbet.entity;

/**
 * @ProjectName: Colin
 * @Package: com.chai.colin.entity
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/16 11:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/16 11:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserBetWholeBean {
    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    private String status;
    private String msg;
    private UserBetBean data;

    public UserBetBean getData() {
        return data;
    }
}
