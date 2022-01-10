package com.rainbowguo.ethexplore.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainbowguo.ethexplore.Utils.TextUtils
import com.rainbowguo.ethexplore.Utils.mToast
import com.rainbowguo.ethexplore.https.HttpUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.ArrayList

class blockFragmentMode : ViewModel() {
    val blockNumber = MutableStateFlow("")
    val date = MutableStateFlow("")
    val miner = MutableStateFlow("")
    val requestState = MutableStateFlow(STATE(null))
    private val transactionsList: MutableList<String> = ArrayList()
    private val TAG = "blockFragmentMode"
    fun getTransactionsList(): List<String> {
        return transactionsList
    }

    fun requestBlockData(number: String) {
        viewModelScope.launch(CoroutineExceptionHandler{ _, e ->
            Log.i("TAG", "requestData: $e")
            viewModelScope.launch {
                requestState.emit(STATE(false))
            }
            mToast.showToastRequestFail()
        }) {
            val bean = HttpUtils.SearchService.get_ProxyBlockInfo(number)
            requestState.emit(STATE(true))
            val timeStamp = TextUtils.to10(bean.result.timestamp)
            date.value = TextUtils.timeStampFormat(timeStamp)
            miner.value = bean.result.miner
            transactionsList.addAll(bean.result.transactions)
        }

    }

    data class STATE(val state:Boolean?)
}