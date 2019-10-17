package com.colin.anbet.entity;

/*
        383左边菜单定义
         private String gamingType;
    private boolean isOpen;
    private boolean isSelected;
    private int sort;
 */

/*
 "gamingTypeCode": "4",
            "gamingTypeId": "4",
            "gamingTypeName": "视讯",
            "isEnable": 1,
 */

public class CategoryBean {

    private int gamingTypeCode;
    private int gamingTypeId;
    private String gamingTypeName;
    private String isEnable;
    private boolean isSelected;

    public int getGamingTypeCode() {
        return gamingTypeCode;
    }

    public int getGamingTypeId() {
        return gamingTypeId;
    }

    public String getGamingTypeName() {
        return gamingTypeName;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public CategoryBean(int gamingTypeId, boolean isSelected) {
        this.gamingTypeId = gamingTypeId;
        this.isSelected = isSelected;
    }


}
