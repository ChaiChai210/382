package com.colin.anbet.entity;

/*
        383左边菜单定义
         private String gamingType;
    private boolean isOpen;
    private boolean isSelected;
    private int sort;
 */

/*
   "code": "ag",
            "money": "0",
            "name": "AG"
 */

public class PlatformAccountBean {

    private String money;
    private String code;
    private String name;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getMoney() {
        return money;
    }
}
