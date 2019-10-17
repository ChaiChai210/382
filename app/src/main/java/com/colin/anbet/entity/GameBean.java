package com.colin.anbet.entity;

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
public class GameBean {
    private String s;
    private GameData d;
    private String m;
    private String status;

    public String getS() {
        return s;
    }

    public GameData getD() {
        return d;
    }

    public String getM() {
        return m;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "GameBean{" +
                "s='" + s + '\'' +
                ", d=" + d +
                ", m='" + m + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
