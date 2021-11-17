package com.rainbowguo.ethexplore.https;

import com.rainbowguo.ethexplore.beans.*;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EtherScanServer {
    private static final String TAG = "TAG";
    private static EtherScanServer instance;
    private final homeServer_interface homeServer;
    private final addressServer_interface addressServer;
    private EtherScanServer(){
        Retrofit retrofit = new  Retrofit.Builder().baseUrl("https://api.etherscan.io")
                .addConverterFactory(GsonConverterFactory.create()).build();
        homeServer = retrofit.create(homeServer_interface.class);
        addressServer = retrofit.create(addressServer_interface.class);
    }

    public void getEthPrice(Callback<EthPriceBean> callback){
        homeServer.getEthPrice().enqueue(callback);
    }

    public void getTotalNodes(Callback<TotalNodesBean> callback){
        homeServer.getTotalNodesNumber().enqueue(callback);
    }

    public void getTotalEthNumber(Callback<TotalEthBean> callback){
        homeServer.getTotalEtherNumber().enqueue(callback);
    }
    public void getTotalBlockNumber(Callback<TotalBlockBean> callback){
        homeServer.getTotalBlockNumber().enqueue(callback);
    }
    public void getChainSize(String date,Callback<chainSizeBean> callback){
        homeServer.getChainSize(date,date).enqueue(callback);
    }
    public void getContractByteCode(String address, Callback<ContractByteCodeBean> callback){
        addressServer.getByteCodeByAddress(address).enqueue(callback);
    }

    public void getBalance(String address, Callback<balanceBean> callback){
        addressServer.getBalance(address).enqueue(callback);
    }

    public void getTransactions(String address,String page, Callback<transactionsBean> callback){
        addressServer.getTransactions(address,page).enqueue(callback);
    }

    public void getInternalTransactions(String address,String page, Callback<internalTransactionsBean> callback){
        addressServer.getInternalTransactions(address,page).enqueue(callback);
    }

    public void getContractCode(String address, Callback<ContractCodeBean> callback){
        addressServer.getContractCode(address).enqueue(callback);
    }
    public void getProxyTransactionsInfo(String txHash,Callback<proxy_transactionsInfoBean> callback){
        addressServer.get_ProxyTransactionsInfoBean(txHash).enqueue(callback);
    }

    public void getBlockInfo(String blockNumber, Callback<blockInfoBean> callback){
        addressServer.get_ProxyBlockInfo(blockNumber).enqueue(callback);
    }



    public static EtherScanServer getInstance() {
        if(instance == null)
            instance = new EtherScanServer();
        return instance;
    }

}
