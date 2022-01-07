package com.rainbowguo.ethexplore.https

import com.rainbowguo.ethexplore.beans.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query


interface interface_searchService {
    @GET("api?module=proxy&action=eth_getCode&tag=latest&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun getByteCodeByAddress(@Query("address") address:String) :ContractByteCodeBean

    @GET("api?module=account&action=balance&tag=latest&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun getBalance(@Query("address") address: String): balanceBean

    @GET("api?module=account&action=txlist&startblock=0&endblock=99999999&offset=10&sort=desc&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun getTransactions(
        @Query("address") address: String,
        @Query("page") page: String
    ): transactionsBean

    @GET("api?module=account&action=txlistinternal&startblock=0&endblock=99999999&offset=10&sort=desc&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun getInternalTransactions(
        @Query("address") address: String,
        @Query("page") page: String
    ): internalTransactionsBean

    @GET("api?module=contract&action=getsourcecode&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun getContractCode(@Query("address") address: String): ContractCodeBean

    @GET("api?module=proxy&action=eth_getTransactionByHash&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun get_ProxyTransactionsInfoBean(@Query("txhash") address: String): proxy_transactionsInfoBean

    @GET("api?module=proxy&action=eth_getBlockByNumber&boolean=false&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun get_ProxyBlockInfo(@Query("tag") blockNumber: String): blockInfoBean
}