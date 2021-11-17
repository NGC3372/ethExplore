package com.rainbowguo.ethexplore.beans;

import com.google.gson.annotations.SerializedName;

public class TotalNodesBean {

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
        @SerializedName("UTCDate")
        private String uTCDate;
        @SerializedName("TotalNodeCount")
        private String totalNodeCount;

        public String getUTCDate() {
            return uTCDate;
        }

        public void setUTCDate(String uTCDate) {
            this.uTCDate = uTCDate;
        }

        public String getTotalNodeCount() {
            return totalNodeCount;
        }

        public void setTotalNodeCount(String totalNodeCount) {
            this.totalNodeCount = totalNodeCount;
        }
    }
}
