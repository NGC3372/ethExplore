package com.rainbowguo.ethexplore.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class transactionsBean {

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

    public static class ResultDTO implements Serializable {
        @SerializedName("blockNumber")
        private String blockNumber;
        @SerializedName("timeStamp")
        private String timeStamp;
        @SerializedName("hash")
        private String hash;
        @SerializedName("nonce")
        private String nonce;
        @SerializedName("blockHash")
        private String blockHash;
        @SerializedName("transactionIndex")
        private String transactionIndex;
        @SerializedName("from")
        private String from;
        @SerializedName("to")
        private String to;
        @SerializedName("value")
        private String value;
        @SerializedName("gas")
        private String gas;
        @SerializedName("gasPrice")
        private String gasPrice;
        @SerializedName("isError")
        private String isError;
        @SerializedName("txreceipt_status")
        private String txreceiptStatus;
        @SerializedName("input")
        private String input;
        @SerializedName("contractAddress")
        private String contractAddress;
        @SerializedName("cumulativeGasUsed")
        private String cumulativeGasUsed;
        @SerializedName("gasUsed")
        private String gasUsed;
        @SerializedName("confirmations")
        private String confirmations;

        public String getBlockNumber() {
            return blockNumber;
        }

        public void setBlockNumber(String blockNumber) {
            this.blockNumber = blockNumber;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getNonce() {
            return nonce;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }

        public String getBlockHash() {
            return blockHash;
        }

        public void setBlockHash(String blockHash) {
            this.blockHash = blockHash;
        }

        public String getTransactionIndex() {
            return transactionIndex;
        }

        public void setTransactionIndex(String transactionIndex) {
            this.transactionIndex = transactionIndex;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getGas() {
            return gas;
        }

        public void setGas(String gas) {
            this.gas = gas;
        }

        public String getGasPrice() {
            return gasPrice;
        }

        public void setGasPrice(String gasPrice) {
            this.gasPrice = gasPrice;
        }

        public String getIsError() {
            return isError;
        }

        public void setIsError(String isError) {
            this.isError = isError;
        }

        public String getTxreceiptStatus() {
            return txreceiptStatus;
        }

        public void setTxreceiptStatus(String txreceiptStatus) {
            this.txreceiptStatus = txreceiptStatus;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getContractAddress() {
            return contractAddress;
        }

        public void setContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
        }

        public String getCumulativeGasUsed() {
            return cumulativeGasUsed;
        }

        public void setCumulativeGasUsed(String cumulativeGasUsed) {
            this.cumulativeGasUsed = cumulativeGasUsed;
        }

        public String getGasUsed() {
            return gasUsed;
        }

        public void setGasUsed(String gasUsed) {
            this.gasUsed = gasUsed;
        }

        public String getConfirmations() {
            return confirmations;
        }

        public void setConfirmations(String confirmations) {
            this.confirmations = confirmations;
        }
    }
}
