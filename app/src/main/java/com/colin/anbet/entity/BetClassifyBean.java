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

public class BetClassifyBean {

    private String name;
    private boolean isSelected;

    public BetClassifyBean(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
