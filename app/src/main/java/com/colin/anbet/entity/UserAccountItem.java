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
/*
 "expenditure" : 0,
    "tradingStatus" : "14",
    "creationTimeStr" : "2019-10-14 10:43:43",
    "creationTime" : "2019-10-14 10:43:43",
    "balance" : 2400,
    "orderFields" : "",
    "sumExp" : 1784.001,
    "sumBal" : 12480.88,
    "id" : "XjLHw2SodfVXbJUQM8T4MrsEC9YUkmT7",
    "token" : "",
    "memberName" : "wangwu",
    "billNo" : "ZH0000000F",
    "tradingStatusName" : "平台资金切换",
    "page" : <null>,
    "creationBy" : "",
    "income" : 2000,
    "memberId" : "bK5n8TPEyP7qU1t8eECJbX9PlBEvwtwL",
    "order" : "ASC",
    "creationByName" : "",
    "orderEncry" : ""
 */
public class UserAccountItem {
    private String sumIn;
    private String expenditure;
    private String creationTimeStr;
    private String balance;
    private String sumExp;
    private String sumBal;
    private String billNo;
    private String tradingStatusName;
    private String income;

    public String getSumIn() {
        return sumIn;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public String getCreationTimeStr() {
        return creationTimeStr;
    }

    public String getBalance() {
        return balance;
    }

    public String getSumExp() {
        return sumExp;
    }

    public String getSumBal() {
        return sumBal;
    }

    public String getBillNo() {
        return billNo;
    }

    public String getTradingStatusName() {
        return tradingStatusName;
    }

    public String getIncome() {
        return income;
    }
}
