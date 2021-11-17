package com.rainbowguo.ethexplore.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class blockInfoBean {

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
        @SerializedName("baseFeePerGas")
        private String baseFeePerGas;
        @SerializedName("difficulty")
        private String difficulty;
        @SerializedName("extraData")
        private String extraData;
        @SerializedName("gasLimit")
        private String gasLimit;
        @SerializedName("gasUsed")
        private String gasUsed;
        @SerializedName("hash")
        private String hash;
        @SerializedName("logsBloom")
        private String logsBloom;
        @SerializedName("miner")
        private String miner;
        @SerializedName("mixHash")
        private String mixHash;
        @SerializedName("nonce")
        private String nonce;
        @SerializedName("number")
        private String number;
        @SerializedName("parentHash")
        private String parentHash;
        @SerializedName("receiptsRoot")
        private String receiptsRoot;
        @SerializedName("sha3Uncles")
        private String sha3Uncles;
        @SerializedName("size")
        private String size;
        @SerializedName("stateRoot")
        private String stateRoot;
        @SerializedName("timestamp")
        private String timestamp;
        @SerializedName("totalDifficulty")
        private String totalDifficulty;
        @SerializedName("transactions")
        private List<String> transactions;
        @SerializedName("transactionsRoot")
        private String transactionsRoot;
        @SerializedName("uncles")
        private List<?> uncles;

        public String getBaseFeePerGas() {
            return baseFeePerGas;
        }

        public void setBaseFeePerGas(String baseFeePerGas) {
            this.baseFeePerGas = baseFeePerGas;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public String getExtraData() {
            return extraData;
        }

        public void setExtraData(String extraData) {
            this.extraData = extraData;
        }

        public String getGasLimit() {
            return gasLimit;
        }

        public void setGasLimit(String gasLimit) {
            this.gasLimit = gasLimit;
        }

        public String getGasUsed() {
            return gasUsed;
        }

        public void setGasUsed(String gasUsed) {
            this.gasUsed = gasUsed;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getLogsBloom() {
            return logsBloom;
        }

        public void setLogsBloom(String logsBloom) {
            this.logsBloom = logsBloom;
        }

        public String getMiner() {
            return miner;
        }

        public void setMiner(String miner) {
            this.miner = miner;
        }

        public String getMixHash() {
            return mixHash;
        }

        public void setMixHash(String mixHash) {
            this.mixHash = mixHash;
        }

        public String getNonce() {
            return nonce;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getParentHash() {
            return parentHash;
        }

        public void setParentHash(String parentHash) {
            this.parentHash = parentHash;
        }

        public String getReceiptsRoot() {
            return receiptsRoot;
        }

        public void setReceiptsRoot(String receiptsRoot) {
            this.receiptsRoot = receiptsRoot;
        }

        public String getSha3Uncles() {
            return sha3Uncles;
        }

        public void setSha3Uncles(String sha3Uncles) {
            this.sha3Uncles = sha3Uncles;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getStateRoot() {
            return stateRoot;
        }

        public void setStateRoot(String stateRoot) {
            this.stateRoot = stateRoot;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getTotalDifficulty() {
            return totalDifficulty;
        }

        public void setTotalDifficulty(String totalDifficulty) {
            this.totalDifficulty = totalDifficulty;
        }

        public List<String> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<String> transactions) {
            this.transactions = transactions;
        }

        public String getTransactionsRoot() {
            return transactionsRoot;
        }

        public void setTransactionsRoot(String transactionsRoot) {
            this.transactionsRoot = transactionsRoot;
        }

        public List<?> getUncles() {
            return uncles;
        }

        public void setUncles(List<?> uncles) {
            this.uncles = uncles;
        }
    }
}
