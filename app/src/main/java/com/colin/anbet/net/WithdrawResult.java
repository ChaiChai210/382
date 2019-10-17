package com.colin.anbet.net;

/**
 * @ProjectName: Colin
 * @Package: com.chai.colin.net
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/17 10:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/17 10:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WithdrawResult {
    private String msg;
    private String status;
    private WithdrawRecorde withdrawalsRecord;

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }
}
