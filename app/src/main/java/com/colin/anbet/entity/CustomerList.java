package com.colin.anbet.entity;

import java.util.List;

/**
 * "customerName": "微信客服",
 */
public class CustomerList {
    private String customerName;
    private String customerContent;
    private List<CustomerServiceList> customers;

    public List<CustomerServiceList> getCustomers() {
        return customers;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerContent() {
        return customerContent;
    }
}
