package com.rainbowguo.ethexplore.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class chainSizeBean {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private List<ResultDTO> result;

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

    public List<ResultDTO> getResult() {
        return result;
    }

    public void setResult(List<ResultDTO> result) {
        this.result = result;
    }

    public static class ResultDTO {
        @SerializedName("blockNumber")
        private String blockNumber;
        @SerializedName("chainTimeStamp")
        private String chainTimeStamp;
        @SerializedName("chainSize")
        private String chainSize;
        @SerializedName("clientType")
        private String clientType;
        @SerializedName("syncMode")
        private String syncMode;

        public String getBlockNumber() {
            return blockNumber;
        }

        public void setBlockNumber(String blockNumber) {
            this.blockNumber = blockNumber;
        }

        public String getChainTimeStamp() {
            return chainTimeStamp;
        }

        public void setChainTimeStamp(String chainTimeStamp) {
            this.chainTimeStamp = chainTimeStamp;
        }

        public String getChainSize() {
            return chainSize;
        }

        public void setChainSize(String chainSize) {
            this.chainSize = chainSize;
        }

        public String getClientType() {
            return clientType;
        }

        public void setClientType(String clientType) {
            this.clientType = clientType;
        }

        public String getSyncMode() {
            return syncMode;
        }

        public void setSyncMode(String syncMode) {
            this.syncMode = syncMode;
        }
    }
}
