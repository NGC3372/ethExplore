package com.rainbowguo.ethexplore.https;

import com.rainbowguo.ethexplore.beans.ContractByteCodeBean;
import com.rainbowguo.ethexplore.beans.ContractCodeBean;
import com.rainbowguo.ethexplore.beans.balanceBean;
import com.rainbowguo.ethexplore.beans.blockInfoBean;
import com.rainbowguo.ethexplore.beans.internalTransactionsBean;
import com.rainbowguo.ethexplore.beans.proxy_transactionsInfoBean;
import com.rainbowguo.ethexplore.beans.transactionsBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface addressServer_interface {

    @GET("api?module=proxy&action=eth_getCode&tag=latest&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<ContractByteCodeBean> getByteCodeByAddress(@Query("address") String address);

    @GET("api?module=account&action=balance&tag=latest&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<balanceBean> getBalance(@Query("address") String address);

    @GET("api?module=account&action=txlist&startblock=0&endblock=99999999&offset=10&sort=desc&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<transactionsBean> getTransactions(@Query("address") String address,@Query("page") String page);

    @GET("api?module=account&action=txlistinternal&startblock=0&endblock=99999999&offset=10&sort=desc&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<internalTransactionsBean> getInternalTransactions(@Query("address") String address,@Query("page") String page);

    @GET("api?module=contract&action=getsourcecode&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<ContractCodeBean> getContractCode(@Query("address") String address);

    @GET("api?module=proxy&action=eth_getTransactionByHash&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<proxy_transactionsInfoBean> get_ProxyTransactionsInfoBean(@Query("txhash") String address);

    @GET("api?module=proxy&action=eth_getBlockByNumber&boolean=false&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<blockInfoBean> get_ProxyBlockInfo(@Query("tag") String blockNumber);

}
