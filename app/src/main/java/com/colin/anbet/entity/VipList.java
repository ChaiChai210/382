package com.colin.anbet.entity;

/**
 * @ProjectName: Colin
 * @Package: com.chai.colin.entity
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/15 17:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/15 17:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class VipList {

    private String depositAccelerationChannel;
    private String exclusiveCustomerManager;
    private String gradeName;
    private String monthGiftMoney;
    private String promotionGift;  /// 晋级礼金
    private String signInMoney;
    /// 有效投注
    private String totalEffectiveBet;
    private String totalPromotionGift;
    private String vipType;
    private String washingRatio;
    private String weekBonus;  /// 洗码比例

    public String getDepositAccelerationChannel() {
        return depositAccelerationChannel;
    }

    public String getExclusiveCustomerManager() {
        return exclusiveCustomerManager;
    }

    public String getGradeName() {
        return gradeName;
    }

    public String getMonthGiftMoney() {
        return monthGiftMoney;
    }

    public String getPromotionGift() {
        return promotionGift;
    }

    public String getSignInMoney() {
        return signInMoney;
    }

    public String getTotalEffectiveBet() {
        return totalEffectiveBet;
    }

    public String getTotalPromotionGift() {
        return totalPromotionGift;
    }

    public String getVipType() {
        return vipType;
    }

    public String getWashingRatio() {
        return washingRatio;
    }

    public String getWeekBonus() {
        return weekBonus;
    }
}
