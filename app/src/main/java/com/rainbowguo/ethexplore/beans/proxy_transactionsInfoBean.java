package com.rainbowguo.ethexplore.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class proxy_transactionsInfoBean implements Serializable {

    @SerializedName("jsonrpc")
    private String jsonrpc;
    @SerializedName("id")
    private Integer id;
    @SerializedName("result")
    private ResultDTO result;

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

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public static class ResultDTO {
        @SerializedName("blockHash")
        private String blockHash;
        @SerializedName("blockNumber")
        private String blockNumber;
        @SerializedName("from")
        private String from;
        @SerializedName("gas")
        private String gas;
        @SerializedName("gasPrice")
        private String gasPrice;
        @SerializedName("maxFeePerGas")
        private String maxFeePerGas;
        @SerializedName("maxPriorityFeePerGas")
        private String maxPriorityFeePerGas;
        @SerializedName("hash")
        private String hash;
        @SerializedName("input")
        private String input;
        @SerializedName("nonce")
        private String nonce;
        @SerializedName("to")
        private String to;
        @SerializedName("transactionIndex")
        private String transactionIndex;
        @SerializedName("value")
        private String value;
        @SerializedName("type")
        private String type;
        @SerializedName("accessList")
        private List<?> accessList;
        @SerializedName("chainId")
        private String chainId;
        @SerializedName("v")
        private String v;
        @SerializedName("r")
        private String r;
        @SerializedName("s")
        private String s;

        public String getBlockHash() {
            return blockHash;
        }

        public void setBlockHash(String blockHash) {
            this.blockHash = blockHash;
        }

        public String getBlockNumber() {
            return blockNumber;
        }

        public void setBlockNumber(String blockNumber) {
            this.blockNumber = blockNumber;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
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

        public String getMaxFeePerGas() {
            return maxFeePerGas;
        }

        public void setMaxFeePerGas(String maxFeePerGas) {
            this.maxFeePerGas = maxFeePerGas;
        }

        public String getMaxPriorityFeePerGas() {
            return maxPriorityFeePerGas;
        }

        public void setMaxPriorityFeePerGas(String maxPriorityFeePerGas) {
            this.maxPriorityFeePerGas = maxPriorityFeePerGas;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getNonce() {
            return nonce;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getTransactionIndex() {
            return transactionIndex;
        }

        public void setTransactionIndex(String transactionIndex) {
            this.transactionIndex = transactionIndex;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<?> getAccessList() {
            return accessList;
        }

        public void setAccessList(List<?> accessList) {
            this.accessList = accessList;
        }

        public String getChainId() {
            return chainId;
        }

        public void setChainId(String chainId) {
            this.chainId = chainId;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public String getR() {
            return r;
        }

        public void setR(String r) {
            this.r = r;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }
    }
}
