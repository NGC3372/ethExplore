package com.rainbowguo.ethexplore.https

import com.rainbowguo.ethexplore.beans.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface interface_homeService {
    @GET("api?module=stats&action=ethprice&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun getEthPrice(): EthPriceBean

    @GET("api?module=stats&action=nodecount&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun getTotalNodesNumber(): TotalNodesBean

    @GET("api?module=stats&action=ethsupply2&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun getTotalEtherNumber(): TotalEthBean

    @GET("api?module=proxy&action=eth_blockNumber&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun getTotalBlockNumber(): TotalBlockBean

    @GET("api?module=stats&action=chainsize&clienttype=geth&syncmode=default&sort=asc&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    suspend fun getChainSize(
        @Query("startdate") startDate: String,
        @Query("enddate") endDate: String
    ): chainSizeBean
}