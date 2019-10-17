package com.colin.anbet.entity;


/*

            "activityName": "手机优惠活动2",
            "androidImageName": "",
            "androidImageNameDetail": "",

            "h5imageName": "20191001163719h5.jpg",
            "h5imageNameDetail": "20191001163719h5Detail.jpg",
            "id": "oHNLZPtdqqmPI9Yp7bIjmzP4XcMgVQf8",
            "imageName": "20191001163719pc.jpg",
            "imageNameDetail": "20191001163719pcDetail.jpg",
            "iosImageName": "20191001163719ios.jpg",
            "iosImageNameDetail": "20191001163719iosDetail.jpg",
            "isEnable": 1,
            "isOnlineGet": "",
            "language": null,
            "order": "ASC",
            "orderFields": "",
            "page": null,
            "sort": 6,
            "token": "",
            "typeParent": "2",
            "typeParentName": "棋牌优惠"
 */
public class ActionItem  {
    private String activityName;
    private String androidImageName;
    private String androidImageNameDetail;
    private String id;
    private String imageName;
    private String imageNameDetail;
    private String isOnlineGet;


    public String getActivityName() {
        return activityName;
    }

    public String getAndroidImageName() {
        return androidImageName;
    }

    public String getAndroidImageNameDetail() {
        return androidImageNameDetail;
    }

    public String getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageNameDetail() {
        return imageNameDetail;
    }

    public String getIsOnlineGet() {
        return isOnlineGet;
    }
}