package com.colin.anbet.entity;
/*
 "gameCode": "",
            "gameKind": "WZ",
            "gameType": "SSS",
            "h5ImageName": "13s.gif",
            "id": "1102",
            "imageName": "13s.gif",
            "iosImageName": "",
            "isEnable": 1,
            "isFlash": 0,
            "isH5": 0,
            "isHot": 1,
            "language": null,
            "liveCode": "wz",
            "liveName": "王者棋牌",
            "order": "ASC",
            "orderFields": "",
            "page": null,
            "sort": 1012,
 */
public class HotGameList {
    private String androidImageName;
    private String chineseName;
    private String englishName;
    private String firstKind;
    private String gameCode;
    private String gameKind;
    private String gameType;

    private int id;
    private String imageName;
    private int isEnable;
    private String liveName;
    private String liveCode;

    public String getLiveCode() {
        return liveCode;
    }

    public String getAndroidImageName() {
        return androidImageName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getFirstKind() {
        return firstKind;
    }

    public String getGameCode() {
        return gameCode;
    }

    public String getGameKind() {
        return gameKind;
    }

    public String getGameType() {
        return gameType;
    }

    public int getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public String getLiveName() {
        return liveName;
    }
}
