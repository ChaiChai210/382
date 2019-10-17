package com.colin.anbet.recharge;

/**
 *  "isHot": 1,
 *             "isOnline": 2,
 *             "isRecommend": 1,
 *             "language": null,
 *             "order": "ASC",
 *             "orderFields": "",
 *             "orderNum": 1,
 *             "page": null,
 *             "payTypeCode": "1",
 *             "payTypeIcon": "https://1-company1.oss-cn-shenzhen.aliyuncs.com/PAY//1542715088554.png",
 *             "payTypeId": "1",
 *             "payTypeName": "银行卡转账",
 */
public class LeftMenuBean
{
  private String isRecommend;
  private boolean isSelected;
  private String payTypeIcon;
  private int isHot;
  private int isOnline;
  private int payTypeId;
  private int payTypeCode;
  private String payTypeName;

  public String getIsRecommend() {
    return isRecommend;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public String getPayTypeIcon() {
    return payTypeIcon;
  }

  public int getIsHot() {
    return isHot;
  }

  public int getIsOnline() {
    return isOnline;
  }

  public int getPayTypeId() {
    return payTypeId;
  }

  public int getPayTypeCode() {
    return payTypeCode;
  }

  public String getPayTypeName() {
    return payTypeName;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }
}
