package com.colin.anbet.entity;

/**
 * @ProjectName: Colin
 * @Package: com.chai.colin.entity
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/16 10:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/16 10:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserBetItem {
    private String betAmount;
    private String betTime;
    private String creationTimeStr;
    private String profit;
    private String orderNo;
    private String revenue;
    private String validBetAmount;
    private String gameCode;
    private String gameTypeName;

    public String getBetAmount() {
        return betAmount;
    }

    public String getBetTime() {
        return betTime;
    }

    public String getCreationTimeStr() {
        return creationTimeStr;
    }

    public String getProfit() {
        return profit;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getRevenue() {
        return revenue;
    }

    public String getValidBetAmount() {
        return validBetAmount;
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getGameTypeName() {
        return gameTypeName;
    }

    @Override
    public String toString() {
        return "UserBetItem{" +
                "betAmount='" + betAmount + '\'' +
                ", betTime='" + betTime + '\'' +
                ", creationTimeStr='" + creationTimeStr + '\'' +
                ", profit='" + profit + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", revenue='" + revenue + '\'' +
                ", validBetAmount='" + validBetAmount + '\'' +
                ", gameCode='" + gameCode + '\'' +
                ", gameTypeName='" + gameTypeName + '\'' +
                '}';
    }
}
