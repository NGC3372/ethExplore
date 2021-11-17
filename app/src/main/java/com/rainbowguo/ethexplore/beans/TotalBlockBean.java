package com.rainbowguo.ethexplore.beans;

import com.google.gson.annotations.SerializedName;

public class TotalBlockBean {
    @SerializedName("jsonrpc")
    private String jsonrpc;
    @SerializedName("id")
    private Integer id;
    @SerializedName("result")
    private String result;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
