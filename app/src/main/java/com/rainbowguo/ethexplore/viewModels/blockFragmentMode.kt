package com.rainbowguo.ethexplore.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rainbowguo.ethexplore.Utils.TextUtils
import com.rainbowguo.ethexplore.https.EtherScanServer
import com.rainbowguo.ethexplore.beans.blockInfoBean
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class blockFragmentMode : ViewModel() {
    val blockNumber = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val miner = MutableLiveData<String>()
    val transactionsSize = MutableLiveData<Int>()
    val requestState = MutableLiveData<Boolean>()
    private val transactionsList: MutableList<String> = ArrayList()
    private val TAG = "blockFragmentMode"
    fun getTransactionsList(): List<String> {
        return transactionsList
    }

    fun requestBlockData(number: String) {
        viewModelScope.launch {

        }

        EtherScanServer.getInstance().getBlockInfo(TextUtils.to16(number), object : Callback<blockInfoBean?> {
            override fun onResponse(
                call: Call<blockInfoBean?>,
                response: Response<blockInfoBean?>
            ) {
                requestState.value = true
                val bean = response.body()
                if (bean == null || bean.result == null) requestState.setValue(false) else {
                    blockNumber.value = TextUtils.to10(bean.result.number)
                    val timeStamp = TextUtils.to10(bean.result.timestamp)
                    date.value = TextUtils.timeStampFormat(timeStamp)
                    miner.value = bean.result.miner
                    transactionsList.addAll(bean.result.transactions)
                    val listSize = transactionsList.size
                    transactionsSize.setValue(listSize)
                }
            }

            override fun onFailure(call: Call<blockInfoBean?>, t: Throwable) {
                requestState.value = false
            }
        })
    }
}