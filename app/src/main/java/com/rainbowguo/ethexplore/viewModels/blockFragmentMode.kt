package com.rainbowguo.ethexplore.viewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rainbowguo.ethexplore.Utils.TextUtils;
import com.rainbowguo.ethexplore.beans.blockInfoBean;
import com.rainbowguo.ethexplore.https.EtherScanServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class blockFragmentMode extends ViewModel {
    private final MutableLiveData<String> blockNumber = new MutableLiveData<>();
    private final MutableLiveData<String> date = new MutableLiveData<>();
    private final MutableLiveData<String> miner = new MutableLiveData<>();
    private final MutableLiveData<Integer> transactionsSize = new MutableLiveData<>();
    private final MutableLiveData<Boolean> requestState = new MutableLiveData<>();
    private List<String> transactionsList = new ArrayList<>();
    private final String TAG = "blockFragmentMode";

    public MutableLiveData<String> getBlockNumber() {
        return blockNumber;
    }

    public MutableLiveData<String> getDate() {
        return date;
    }

    public MutableLiveData<String> getMiner() {
        return miner;
    }

    public MutableLiveData<Boolean> getRequestState() {
        return requestState;
    }

    public List<String> getTransactionsList() {
        return transactionsList;
    }

    public MutableLiveData<Integer> getTransactionsSize() {
        return transactionsSize;
    }

    public void requestBlockData(String number){
        number = TextUtils.to16(number);
        EtherScanServer.getInstance().getBlockInfo(number, new Callback<blockInfoBean>() {
            @Override
            public void onResponse(Call<blockInfoBean> call, Response<blockInfoBean> response) {
                requestState.setValue(true);
                blockInfoBean bean = response.body();
                if (bean == null || bean.getResult() == null)
                    requestState.setValue(false);
                else {
                    blockNumber.setValue(TextUtils.to10(bean.getResult().getNumber()));
                    String timeStamp = TextUtils.to10(bean.getResult().getTimestamp());
                    date.setValue(TextUtils.timeStampFormat(timeStamp));
                    miner.setValue(bean.getResult().getMiner());
                    transactionsList.addAll(bean.getResult().getTransactions());
                    int listSize = transactionsList.size();
                    transactionsSize.setValue(listSize);
                }

            }

            @Override
            public void onFailure(Call<blockInfoBean> call, Throwable t) {
                requestState.setValue(false);
            }
        });
    }

}
