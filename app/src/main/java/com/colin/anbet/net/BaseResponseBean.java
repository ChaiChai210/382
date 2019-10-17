package com.colin.anbet.net;

public class BaseResponseBean<T> {
    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private int status;
    private String msg;
    private T data;

}
