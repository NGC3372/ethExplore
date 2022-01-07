package com.rainbowguo.ethexplore.https

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpUtils {

    private  val client: Retrofit = Retrofit.Builder().baseUrl("https://api.etherscan.io").addConverterFactory(
        GsonConverterFactory.create()).build()

    val SearchService: interface_searchService = client.create(interface_searchService::class.java)

    val HomeService :interface_homeService = client.create(interface_homeService::class.java)

}