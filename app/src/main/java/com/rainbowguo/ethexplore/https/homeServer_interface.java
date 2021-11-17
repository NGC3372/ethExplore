package com.rainbowguo.ethexplore.https;

import com.rainbowguo.ethexplore.beans.EthPriceBean;
import com.rainbowguo.ethexplore.beans.TotalBlockBean;
import com.rainbowguo.ethexplore.beans.TotalEthBean;
import com.rainbowguo.ethexplore.beans.TotalNodesBean;
import com.rainbowguo.ethexplore.beans.chainSizeBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface homeServer_interface {
    @GET("api?module=stats&action=ethprice&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<EthPriceBean> getEthPrice();

    @GET("api?module=stats&action=nodecount&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<TotalNodesBean> getTotalNodesNumber();

    @GET("api?module=stats&action=ethsupply2&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<TotalEthBean> getTotalEtherNumber();

    @GET("api?module=proxy&action=eth_blockNumber&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<TotalBlockBean> getTotalBlockNumber();

    @GET("api?module=stats&action=chainsize&clienttype=geth&syncmode=default&sort=asc&apikey=CKH1YWIKNCZST23VC9DTK6WK5561S2HQB9")
    Call<chainSizeBean> getChainSize(@Query("startdate") String startDate,@Query("enddate") String endDate);

}
