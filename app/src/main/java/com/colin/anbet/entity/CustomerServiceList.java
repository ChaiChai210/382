package com.colin.anbet.entity;

/**
 * @ProjectName: Anbet
 * @Package: com.colin.anbet.entity
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/18 14:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/18 14:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomerServiceList {
    private String customerName;
    private String customerContent;
    private String customerHead;
    private String parentId;

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerContent() {
        return customerContent;
    }

    public String getCustomerHead() {
        return customerHead;
    }

    public String getParentId() {
        return parentId;
    }
}
