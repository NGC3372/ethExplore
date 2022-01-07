package com.rainbowguo.ethexplore.viewModels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainbowguo.ethexplore.Utils.TextUtils
import com.rainbowguo.ethexplore.Utils.mToast
import com.rainbowguo.ethexplore.beans.TotalBlockBean
import com.rainbowguo.ethexplore.https.HttpUtils
import kotlinx.coroutines.CompletionHandlerException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class homeViewModel:ViewModel() {
    private val _priceOfEthToBTC = MutableStateFlow<String>("")
    val priceOfEthToBTC :StateFlow<String> = _priceOfEthToBTC

    private val _priceOfEthToUSDT = MutableStateFlow<String>("")
    val priceOfEthToUSDT :StateFlow<String> = _priceOfEthToUSDT

    private val _NumberOfTotalEth = MutableStateFlow<String>("")
    val NumberOfTotalEth :StateFlow<String> = _NumberOfTotalEth

    private val _NumberOfEth2Staking = MutableStateFlow<String>("")
    val NumberOfEth2Staking :StateFlow<String> = _NumberOfEth2Staking

    private val _NumberOfBurntFees = MutableStateFlow<String>("")
    val NumberOfBurntFees :StateFlow<String> = _NumberOfBurntFees

    private val _NumberOfTotalNode = MutableStateFlow<String>("")
    val NumberOfTotalNode :StateFlow<String> = _NumberOfTotalNode

    private val _NumberOfTotalBlock = MutableStateFlow<String>("")
    val NumberOfTotalBlock :StateFlow<String> = _NumberOfTotalBlock

    private val _ChainSize = MutableStateFlow<String>("")
    val ChainSize :StateFlow<String> = _ChainSize

    private val _requestTime = MutableStateFlow<String>("")
    val requestTime :StateFlow<String> = _requestTime

    fun clearData(){

        viewModelScope.launch {
            _priceOfEthToBTC.emit("-")
            _priceOfEthToUSDT.emit("-")
            _NumberOfTotalEth.emit("-")
            _NumberOfEth2Staking.emit("-")
            _NumberOfBurntFees.emit("-")
            _NumberOfTotalNode.emit("-")
            _NumberOfTotalBlock.emit("-")
            _ChainSize.emit("-")
        }

    }


    @SuppressLint("SimpleDateFormat")
    fun requestData(){
        viewModelScope.launch(CoroutineExceptionHandler{ _, e ->
            Log.i("TAG", "requestData: $e")
            mToast.showToastRequestFail()
        }){
            val date = Date(System.currentTimeMillis())
            val formatter = SimpleDateFormat.getDateTimeInstance().format(date)
            _requestTime.emit(formatter)
            launch {
                val bean = HttpUtils.HomeService.getEthPrice()
                _priceOfEthToBTC.emit("1: " + bean.result.ethbtc)
                _priceOfEthToUSDT.emit("1: " + bean.result.ethusd)
            }
            launch {
                val bean = HttpUtils.HomeService.getTotalEtherNumber()
                _NumberOfTotalEth.emit(bean.result.ethSupply + " Ether")
                _NumberOfEth2Staking.emit(bean.result.eth2Staking+ " Ether")
                _NumberOfBurntFees.emit(bean.result.burntFees+ " Ether")
            }
            launch {
                val bean = HttpUtils.HomeService.getTotalNodesNumber()
                _NumberOfTotalNode.emit(bean.result.totalNodeCount)
            }
            launch {
                val bean = HttpUtils.HomeService.getTotalBlockNumber()
                _NumberOfTotalBlock.emit(TextUtils.to10(bean.result))
            }
            launch {
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                val date = simpleDateFormat.format(Date(System.currentTimeMillis()))
                val bean = HttpUtils.HomeService.getChainSize(date,date)
                _ChainSize.emit(TextUtils.KbToGb(bean.result[0].chainSize) + " GB")
            }



        }
    }

}