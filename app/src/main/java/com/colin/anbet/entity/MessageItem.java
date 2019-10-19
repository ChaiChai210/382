package com.colin.anbet.entity;

/**
 * @ProjectName: Colin
 * @Package: com.chai.colin.entity
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/12 19:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/12 19:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

/**
 * "msgContent": "我是公告！！！我是公告！！！我是公告！！！我是公告！！！我是公告！！！",
 * "msgTitle": "我是公告！！！",
 * "msgType": "28",
 * "msgTypeName": "手机端弹窗公告",
 */
public class MessageItem {
    private String msgContent;
    private String msgTitle;
    private String msgType;
    private String msgTypeName;
    private boolean isRead;

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isRead() {
        return isRead;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getMsgTypeName() {
        return msgTypeName;
    }
}
