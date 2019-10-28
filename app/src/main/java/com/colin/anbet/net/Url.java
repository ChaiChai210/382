package com.colin.anbet.net;

import rxhttp.wrapper.annotation.DefaultDomain;

public class Url {
//    @Domain(name = "Update9158") //设置非默认域名，name 可不传，不传默认为变量的名称
//    public static String update = "http://update.9158.com";

    @DefaultDomain() //设置为默认域名
//    http://192.168.0.122:8081/zh-member-web/userInfoMember/addUserInfoMember.do
    //测试服务器
    public static String baseUrl = "http://www.zhmember.f9186.com:8080/zh-member-web/";
//    public static String baseUrl = "http://192.168.1.21:8081/zh-member-web/";
    public static String chessCardDir = baseUrl+"uploadImg/ChessCarddataFile/";
    public static String secodeClaseGameUrl = baseUrl+"uploadImg/MenuTwodataFile/";
    public static String promotionDie = baseUrl+"uploadImg/promotions/";
    public static String customerServiceDir = baseUrl+"uploadImg/customer/";
    public static final String GAME_SECOND = "phone/findGamingPlatfromList";
    public static final String GAME_CLASSIFY = "phone/findGamingList";
    public static final String HOT_GAME = "phone/getHotGames";
    public static final String kyGameList = "phone/getGame/KYQP/PHONE";
    //进入游戏
    public static final String GAME_URL = "phone/play";
    public static final String getAllGameBalance = "phone/getAllGameBalance";

    public static final String BANK_LIST = "phone/bankCard/listBankCard";
    public static final String ADD_BANK_CARD = "phone/addBankCard";
    public static final String SET_DEFAULT_CARD = "phone/bankCard/setDefault";
    //    登陆注册
    public static final String CHECK_UNIQUE = "phone/checkUnique";
    public static final String LOGIN_CHECK = "phone/common/loginCheck";

    public static final String creditConversion = "phone/creditConversion";
    public static final String getBetRecordDetail = "phone/getBetRecordDetail";

    public static final String Action = "phone/listPromotions";
    public static final String getGameBalance = "phone/getGameBalance";
    //    、、获取账户明细(账变记录)
    public static final String listAccountDetail = "phone/listAccountDetail";
    //    获取投注记录列表
    public static final String listBetRecord = "phone/listBetRecord";
    //客服
    public static final String listCustomer = "phone/listCustomer";
    //支付方式
    public static final String listPayType = "phone/listPayType";
    //获取充值记录列表
    public static final String listRechargeOrder = "phone/listRechargeOrder";
    //公告列表
    public static final String listSiteMessage = "phone/listSiteMessage";
    //    获取交易状态下拉列表
    public static final String listStatus = "phone/listStatus";
    public static final String listVipGrade = "phone/listVipGrade";
    public static final String listWithdrawalsRecord = "phone/listWithdrawalsRecord";
    //会员充值
    public static final String czGenerateOrder = "phone/czGenerateOrder";
    //    查询会员余额方法
    public static final String findMemberBalance = "phone/memberManager/findMemberBalance";
    public static final String LOGIN = "phone/memberManager/login";
    public static final String logout = "phone/memberManager/logout";
    public static final String register = "phone/memberManager/register";
    //会员提现
    public static final String txGenerateOrder = "phone/memberManager/txGenerateOrder";
    //修改会员的登录密码
    public static final String updateLoginPwd = "phone/memberManager/updateLoginPwd";
    public static final String updateMember = "phone/memberManager/updateMember";
    public static final String updateMemberSafeMoney = "phone/memberManager/updateMemberSafeMoney";
    public static final String updateMemberSafePwd = "phone/memberManager/updateMemberSafePwd";
    public static final String findMemberSafePwd = "phone/memberManager/findMemberSafePwd";
}
