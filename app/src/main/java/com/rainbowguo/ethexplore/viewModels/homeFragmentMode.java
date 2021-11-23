package com.rainbowguo.ethexplore.viewModels;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rainbowguo.ethexplore.Utils.TextUtils;
import com.rainbowguo.ethexplore.Utils.mToast;
import com.rainbowguo.ethexplore.beans.EthPriceBean;
import com.rainbowguo.ethexplore.beans.TotalBlockBean;
import com.rainbowguo.ethexplore.beans.TotalEthBean;
import com.rainbowguo.ethexplore.beans.TotalNodesBean;
import com.rainbowguo.ethexplore.beans.chainSizeBean;
import com.rainbowguo.ethexplore.https.EtherScanServer;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homeFragmentMode extends ViewModel {
    private static final String TAG = "mainActivity_viewModel";

    private final MutableLiveData<String> priceOfEthToBTC = new MutableLiveData<>();
    private final MutableLiveData<String> priceOfEthToUSDT = new MutableLiveData<>();
    private final MutableLiveData<String> NumberOfTotalEth = new MutableLiveData<>();
    private final MutableLiveData<String> NumberOfEth2Staking = new MutableLiveData<>();
    private final MutableLiveData<String> NumberOfBurntFees = new MutableLiveData<>();
    private final MutableLiveData<String> NumberOfTotalNode = new MutableLiveData<>();
    private final MutableLiveData<String> NumberOfTotalBlock = new MutableLiveData<>();
    private final MutableLiveData<String> ChainSize = new MutableLiveData<>();

    private final MutableLiveData<String> requestTime = new MutableLiveData<>();
    private final EtherScanServer mServer;

    @SuppressLint("ResourceAsColor")
    public homeFragmentMode(){
        mServer = EtherScanServer.getInstance();
    }

    public MutableLiveData<String> getRequestTime() {
        return requestTime;
    }

    public MutableLiveData<String> getPriceOfEthToUSDT() {
        return priceOfEthToUSDT;
    }

    public MutableLiveData<String> getPriceOfEthToBTC() {
        return priceOfEthToBTC;
    }

    public MutableLiveData<String> getNumberOfTotalBlock() {
        return NumberOfTotalBlock;
    }

    public MutableLiveData<String> getNumberOfTotalEth() {
        return NumberOfTotalEth;
    }

    public MutableLiveData<String> getNumberOfTotalNode() {
        return NumberOfTotalNode;
    }

    public MutableLiveData<String> getChainSize() {
        return ChainSize;
    }

    public MutableLiveData<String> getNumberOfBurntFees() {
        return NumberOfBurntFees;
    }

    public MutableLiveData<String> getNumberOfEth2Staking() {
        return NumberOfEth2Staking;
    }

    public void clearData(){
        priceOfEthToUSDT.setValue("-");
        priceOfEthToBTC.setValue("-");
        NumberOfTotalEth.setValue("-");
        NumberOfTotalNode.setValue("-");
        NumberOfTotalBlock.setValue("-");
        NumberOfBurntFees.setValue("-");
        NumberOfEth2Staking.setValue("-");
        ChainSize.setValue("-");
    }

    public void getTotalNodes(){
        mServer.getTotalNodes(new Callback<TotalNodesBean>() {
            @Override
            public void onResponse(@NonNull Call<TotalNodesBean> call, @NonNull Response<TotalNodesBean> response) {

                assert response.body() != null;
                TotalNodesBean bean = response.body();
                Log.i(TAG, "onResponse: " + bean.getStatus());
                Log.i(TAG, "onResponse: "+ bean.getResult());
                Log.i(TAG, "onResponse: " + bean.getMessage());
                System.out.println("1231231");
                NumberOfTotalNode.setValue(bean.getResult().getTotalNodeCount());

            }

            @Override
            public void onFailure(@NonNull Call<TotalNodesBean> call, @NonNull Throwable t) {
                mToast.showToastRequestFail();
            }
        });
    }

    public void getTotalBlockNumber(){
        mServer.getTotalBlockNumber(new Callback<TotalBlockBean>() {
            @Override
            public void onResponse(@NonNull Call<TotalBlockBean> call, @NonNull Response<TotalBlockBean> response) {
                assert response.body() != null;
                TotalBlockBean bean = response.body();
                System.out.println(bean.getResult() + "---------");
                NumberOfTotalBlock.setValue(TextUtils.to10(bean.getResult()));

            }

            @Override
            public void onFailure(@NonNull Call<TotalBlockBean> call, @NonNull Throwable t) {
                mToast.showToastRequestFail();
            }
        });

    }
    public void getTotalEthNumber(){
        mServer.getTotalEthNumber(new Callback<TotalEthBean>() {
            @Override
            public void onResponse(@NonNull Call<TotalEthBean> call, @NonNull Response<TotalEthBean> response) {
                assert response.body() != null;
                TotalEthBean bean = response.body();
                NumberOfTotalEth.setValue(bean.getResult().getEthSupply() + " Ether");
                NumberOfEth2Staking.setValue(bean.getResult().getEth2Staking()+ " Ether");
                NumberOfBurntFees.setValue(bean.getResult().getBurntFees()+ " Ether");
            }

            @Override
            public void onFailure(@NonNull Call<TotalEthBean> call, @NonNull Throwable t) {
                mToast.showToastRequestFail();
            }
        });
    }
    public void getEthPrice(){
        mServer.getEthPrice(new Callback<EthPriceBean>() {
            @Override
            public void onResponse(@NonNull Call<EthPriceBean> call, @NonNull Response<EthPriceBean> response) {
                assert response.body() != null;
                EthPriceBean bean = response.body();
                priceOfEthToBTC.setValue("1:" + bean.getResult().getEthbtc());
                priceOfEthToUSDT.setValue("1:" + bean.getResult().getEthusd());
            }

            @Override
            public void onFailure(@NonNull Call<EthPriceBean> call, @NonNull Throwable t) {
                mToast.showToastRequestFail();
            }
        });
    }
    public void getEthSize(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String date = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        System.out.println();
        mServer.getChainSize(date, new Callback<chainSizeBean>() {
            @Override
            public void onResponse(Call<chainSizeBean> call, Response<chainSizeBean> response) {
                String size = response.body().getResult().get(0).getChainSize();
                ChainSize.setValue(TextUtils.KbToGb(size) +"GB");
            }

            @Override
            public void onFailure(Call<chainSizeBean> call, Throwable t) {
                mToast.showToastRequestFail();
            }
        });
    }

    public void setRequestTime(){
        Date date = new Date(System.currentTimeMillis());
        String formatter =
                SimpleDateFormat.getDateTimeInstance().format(date);
        requestTime.setValue(formatter);

    }

}
