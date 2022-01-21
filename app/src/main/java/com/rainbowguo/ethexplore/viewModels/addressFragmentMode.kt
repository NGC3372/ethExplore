package com.rainbowguo.ethexplore.viewModels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainbowguo.ethexplore.Utils.TextUtils
import com.rainbowguo.ethexplore.Utils.mToast
import com.rainbowguo.ethexplore.beans.transactionsBean
import com.rainbowguo.ethexplore.beans.internalTransactionsBean
import com.rainbowguo.ethexplore.https.HttpUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.ArrayList
import java.util.HashMap

class addressFragmentMode(address: String) : ViewModel() {
    private val TAG = "addressFragmentMode"
    private val Address = address
    private lateinit var byteCode: String
    private lateinit var addressByteCode: String
    val addressType = MutableStateFlow("")
    val addressBalance = MutableStateFlow("")
    val requestState = MutableStateFlow(STATE(null))
    val transactionList = ArrayList<transactionsBean.ResultDTO>()
    val internalList = ArrayList<internalTransactionsBean.ResultDTO>()
    val contractData = HashMap<String,String>()
    val TransactionsDataState = MutableStateFlow(transactionsRequestState(""))
    val internalTransactionsDataState = MutableStateFlow(transactionsRequestState(""))
    val contractDataState = MutableStateFlow("")
    private var wrongHandler : CoroutineExceptionHandler = CoroutineExceptionHandler{ _, e ->
        Log.i("TAG", "requestData: $e")
        viewModelScope.launch {
            requestState.emit(STATE(false))
        }
        mToast.showToastRequestFail()
    }



    fun requestAddressBytecode() {
        viewModelScope.launch(wrongHandler) {
            val bean = HttpUtils.SearchService.getByteCodeByAddress(Address)
            byteCode = bean.result
            addressByteCode = byteCode
            if (addressByteCode == "0x") {
                addressType.value = "Address"
            } else {
                addressType.value = "Contract"
            }
        }
    }


    fun getContractData() {
        viewModelScope.launch (wrongHandler){
            val bean = HttpUtils.SearchService.getContractCode(Address)
            var abi = bean.result[0].abi
            var code = bean.result[0].sourceCode
            if (code.equals("")) code = "-"
            if(abi.equals("Contract source code not verified")) abi = "-"
            contractData["byteCode"] = byteCode
            contractData[abi] = abi
            contractData[code] = code
            contractDataState.emit("ok")
        }
    }

    fun getTransactionsData(page: String) {
        viewModelScope.launch(wrongHandler) {
            val bean = HttpUtils.SearchService.getTransactions(Address,page)
            val list = bean.result
            Log.i(TAG, "getTransactionsData: ${list.size}")
            if (list.size ==0)
                TransactionsDataState.emit(transactionsRequestState("null"))
            else{
                transactionList.addAll(list)
                TransactionsDataState.emit(transactionsRequestState("ok"))
                if (list.size < 10)
                    TransactionsDataState.emit(transactionsRequestState("to_end"))
            }
        }
    }

    fun getInternalTransactionsData(page: String) {
        viewModelScope.launch(wrongHandler){
            val bean = HttpUtils.SearchService.getInternalTransactions(Address,page)
            val list = bean.result
            if (list.size == 0)
                internalTransactionsDataState.emit(transactionsRequestState("null"))
            else{
                internalList.addAll(list)
                internalTransactionsDataState.emit(transactionsRequestState("ok"))
                if (list.size < 10)
                    internalTransactionsDataState.emit(transactionsRequestState("to_end"))
            }
        }
    }

    fun getBalance(){
        viewModelScope.launch(wrongHandler){
            val bean = HttpUtils.SearchService.getBalance(Address)
            val value = TextUtils.formatEther(bean.result, null)
            addressBalance.emit(value)
        }
    }

    data class STATE(val state : Boolean?)
    class transactionsRequestState(val state:String)
}