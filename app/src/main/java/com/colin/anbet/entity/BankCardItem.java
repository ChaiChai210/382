package com.colin.anbet.entity;

/**
 * @ProjectName: Colin
 * @Package: com.chai.colin.entity
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/12 19:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/12 19:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  "backGroupIcon": "",
 *             "bankBranch": "深圳市福田区车公庙支行",
 *             "bankCardId": "lyPjer2iom3O93DBsWpIRAUBBhtHuDiG",
 *             "bankCode": "ZGYH",
 *             "bankIcon": "",
 *             "bankName": "中国银行",
 *             "bankNo": "6214836523159057",
 *             "creationBy": "wangwu",
 *             "creationTime": "2019-09-10 12:32:09",
 *             "dataSourceKey": "",
 *             "isDefault": "0",
 *             "lastUpdatedTime": "2019-09-28 14:35:12",
 *             "levelName": "",
 *             "memberId": "bK5n8TPEyP7qU1t8eECJbX9PlBEvwtwL",
 *             "memberLevelName": "",
 *             "memberName": "wangwu",
 *             "page": null,
 *             "remark": "",
 *             "token": "",
 *             "userBankName": ""
 */
public class BankCardItem implements Parcelable {
        private String bankBranch;
        private String bankCardId;
        private String bankCode;
        private String bankIcon;
        private String bankName;
        private String bankNo;
        private String isDefault;
        private String memberId;
        private String memberLevelName;
        private String levelName;
        private String memberName;
        private String userBankName;

    protected BankCardItem(Parcel in) {
        bankBranch = in.readString();
        bankCardId = in.readString();
        bankCode = in.readString();
        bankIcon = in.readString();
        bankName = in.readString();
        bankNo = in.readString();
        isDefault = in.readString();
        memberId = in.readString();
        memberLevelName = in.readString();
        levelName = in.readString();
        memberName = in.readString();
        userBankName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bankBranch);
        dest.writeString(bankCardId);
        dest.writeString(bankCode);
        dest.writeString(bankIcon);
        dest.writeString(bankName);
        dest.writeString(bankNo);
        dest.writeString(isDefault);
        dest.writeString(memberId);
        dest.writeString(memberLevelName);
        dest.writeString(levelName);
        dest.writeString(memberName);
        dest.writeString(userBankName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BankCardItem> CREATOR = new Creator<BankCardItem>() {
        @Override
        public BankCardItem createFromParcel(Parcel in) {
            return new BankCardItem(in);
        }

        @Override
        public BankCardItem[] newArray(int size) {
            return new BankCardItem[size];
        }
    };

    public String getBankBranch() {
        return bankBranch;
    }

    public String getBankCardId() {
        return bankCardId;
    }

//    public String getBankCode() {
//        return bankCode;
//    }

    public String getBankIcon() {
        return bankIcon;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberLevelName() {
        return memberLevelName;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getUserBankName() {
        return userBankName;
    }
}
