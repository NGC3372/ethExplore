package com.rainbowguo.ethexplore.beans;

import com.google.gson.annotations.SerializedName;

public class EthPriceBean {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private ResultDTO result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public static class ResultDTO {
        @SerializedName("ethbtc")
        private String ethbtc;
        @SerializedName("ethbtc_timestamp")
        private String ethbtcTimestamp;
        @SerializedName("ethusd")
        private String ethusd;
        @SerializedName("ethusd_timestamp")
        private String ethusdTimestamp;

        public String getEthbtc() {
            return ethbtc;
        }

        public void setEthbtc(String ethbtc) {
            this.ethbtc = ethbtc;
        }

        public String getEthbtcTimestamp() {
            return ethbtcTimestamp;
        }

        public void setEthbtcTimestamp(String ethbtcTimestamp) {
            this.ethbtcTimestamp = ethbtcTimestamp;
        }

        public String getEthusd() {
            return ethusd;
        }

        public void setEthusd(String ethusd) {
            this.ethusd = ethusd;
        }

        public String getEthusdTimestamp() {
            return ethusdTimestamp;
        }

        public void setEthusdTimestamp(String ethusdTimestamp) {
            this.ethusdTimestamp = ethusdTimestamp;
        }
    }
}
