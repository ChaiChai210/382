package com.colin.anbet.entity;


public class NetBankBean {

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

    public NetBankBean(int gamingTypeId, boolean isSelected) {
        this.gamingTypeId = gamingTypeId;
        this.isSelected = isSelected;
    }
}
