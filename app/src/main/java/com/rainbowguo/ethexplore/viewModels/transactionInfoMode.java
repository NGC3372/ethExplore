package com.rainbowguo.ethexplore.viewModels;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.rainbowguo.ethexplore.beans.proxy_transactionsInfoBean;
import com.rainbowguo.ethexplore.https.EtherScanServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class transactionInfoMode extends ViewModel {
    private final MutableLiveData<String> FromAddress = new MutableLiveData<>();
    private final MutableLiveData<String> ToAddress = new MutableLiveData<>();
    private final MutableLiveData<String> Value = new MutableLiveData<>();
    private final MutableLiveData<proxy_transactionsInfoBean.ResultDTO> Bean = new MutableLiveData<>();

    public MutableLiveData<String> getFromAddress() {
        return FromAddress;
    }

    public MutableLiveData<String> getToAddress() {
        return ToAddress;
    }

    public MutableLiveData<String> getValue() {
        return Value;
    }

    public MutableLiveData<proxy_transactionsInfoBean.ResultDTO> getBean() {
        return Bean;
    }

    public void requestProsy_TransactionsInfo(String TxHash){
        EtherScanServer.getInstance().getProxyTransactionsInfo(TxHash, new Callback<proxy_transactionsInfoBean>() {
            @Override
            public void onResponse(Call<proxy_transactionsInfoBean> call, Response<proxy_transactionsInfoBean> response) {
                assert response.body() != null;
                proxy_transactionsInfoBean.ResultDTO bean = response.body().getResult();
                Bean.setValue(bean);
            }

            @Override
            public void onFailure(Call<proxy_transactionsInfoBean> call, Throwable t) {

            }
        });
    }





}
