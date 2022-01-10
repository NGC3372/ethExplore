package com.rainbowguo.ethexplore.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainbowguo.ethexplore.Utils.TextUtils
import com.rainbowguo.ethexplore.beans.proxy_transactionsInfoBean
import com.rainbowguo.ethexplore.https.HttpUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class transactionInfoMode : ViewModel() {
    val fromAddress = MutableStateFlow("")
    val toAddress = MutableStateFlow("")
    val eth_value = MutableStateFlow("")
    val requestState =MutableStateFlow(STATE(null))

    val dataBean = MutableStateFlow(proxy_transactionsInfoBean())


    fun requestProsy_TransactionsInfo(TxHash: String) {
        viewModelScope.launch{
            val handler = CoroutineExceptionHandler { _, e ->
                Log.i("TAG", "requestData: $e")
                launch {
                    requestState.emit(STATE(false))
                }
            }
            launch(handler) {
                val bean = HttpUtils.SearchService.get_ProxyTransactionsInfoBean(TxHash)
                val from = bean.result.from
                val to = bean.result.to
                var value = TextUtils.to10(bean.result.value)
                value = if (value != "0") TextUtils.formatEther(value, null) else "0 Ether"
                fromAddress.emit(from)
                toAddress.emit(to)
                eth_value.emit(value)
                requestState.emit(STATE(true))
                dataBean.emit(bean)
            }

        }

    }

   data class STATE(val state :Boolean?)
}