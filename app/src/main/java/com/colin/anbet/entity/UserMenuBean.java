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

public class UserMenuBean {

    private int position;
    private boolean isSelected;

    public int getPosition() {
        return position;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public UserMenuBean(int position, boolean isSelected) {

        this.position = position;
        this.isSelected = isSelected;
    }
}
