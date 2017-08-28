package com.facebook.shuiai.project.enitity;


public class ResultDto<T, K> {
    private String resultCode;
    // 返回的结果对象
    private T properties;
    private String message;
    private K listData;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public T getProperties() {
        return properties;
    }

    public void setProperties(T properties) {
        this.properties = properties;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public K getListData() {
        return listData;
    }

    public void setListData(K listData) {
        this.listData = listData;
    }
}
