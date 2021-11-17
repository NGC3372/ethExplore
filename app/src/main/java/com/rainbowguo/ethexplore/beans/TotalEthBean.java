package com.rainbowguo.ethexplore.beans;

import com.google.gson.annotations.SerializedName;

public class TotalEthBean {

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
        @SerializedName("EthSupply")
        private String ethSupply;
        @SerializedName("Eth2Staking")
        private String eth2Staking;
        @SerializedName("BurntFees")
        private String burntFees;

        public String getEthSupply() {
            return ethSupply;
        }

        public void setEthSupply(String ethSupply) {
            this.ethSupply = ethSupply;
        }

        public String getEth2Staking() {
            return eth2Staking;
        }

        public void setEth2Staking(String eth2Staking) {
            this.eth2Staking = eth2Staking;
        }

        public String getBurntFees() {
            return burntFees;
        }

        public void setBurntFees(String burntFees) {
            this.burntFees = burntFees;
        }
    }
}
