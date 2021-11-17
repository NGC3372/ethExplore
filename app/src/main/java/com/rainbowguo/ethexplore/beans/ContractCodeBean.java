package com.rainbowguo.ethexplore.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContractCodeBean {
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
        @SerializedName("SourceCode")
        private String sourceCode;
        @SerializedName("ABI")
        private String abi;

        public String getSourceCode() {
            return sourceCode;
        }

        public void setSourceCode(String sourceCode) {
            this.sourceCode = sourceCode;
        }

        public String getAbi() {
            return abi;
        }

        public void setAbi(String abi) {
            this.abi = abi;
        }
    }
}
