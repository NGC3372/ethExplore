package com.rainbowguo.ethexplore.viewModels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rainbowguo.ethexplore.Utils.TextUtils;
import com.rainbowguo.ethexplore.adapter.ContractInfoAdapter;
import com.rainbowguo.ethexplore.adapter.InternalTransactionAdapter;
import com.rainbowguo.ethexplore.adapter.TransactionAdapter;
import com.rainbowguo.ethexplore.adapter.viewpageAdapter;
import com.rainbowguo.ethexplore.beans.ContractByteCodeBean;
import com.rainbowguo.ethexplore.beans.ContractCodeBean;
import com.rainbowguo.ethexplore.beans.balanceBean;
import com.rainbowguo.ethexplore.beans.internalTransactionsBean;
import com.rainbowguo.ethexplore.beans.transactionsBean;
import com.rainbowguo.ethexplore.https.EtherScanServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addressFragmentMode extends ViewModel {
    private final String TAG = "addressFragmentMode";
    private final String Address;
    private String byteCode;
    public final MutableLiveData<String> addressType = new MutableLiveData<>();
    public final MutableLiveData<String> addressBalance = new MutableLiveData<>();
    private final MutableLiveData<Boolean> requestState = new MutableLiveData<>();
    public  String addressByteCode ;
    private final ArrayList<transactionsBean.ResultDTO> transactionList = new ArrayList<>();
    private final ArrayList<internalTransactionsBean.ResultDTO> internalList = new ArrayList<>();
    private final HashMap<String,String> contractData = new HashMap<>();
    private final EtherScanServer mServer;



    public MutableLiveData<String> getAddressType() {
        return addressType;
    }

    public MutableLiveData<String> getAddressBalance() {
        return addressBalance;
    }

    public MutableLiveData<Boolean> getRequestState() {
        return requestState;
    }

    public ArrayList<transactionsBean.ResultDTO> getTransactionList() {
        return transactionList;
    }

    public ArrayList<internalTransactionsBean.ResultDTO> getInternalList() {
        return internalList;
    }

    public HashMap<String, String> getContractData() {
        return contractData;
    }

    @SuppressLint("SetTextI18n")
    public addressFragmentMode(String address){
        mServer = EtherScanServer.getInstance();
        Address = address;
    }

    public void requestAddressBytecode() throws NumberFormatException{
        mServer.getContractByteCode(Address, new Callback<ContractByteCodeBean>() {
            @Override
            public void onResponse(Call<ContractByteCodeBean> call, Response<ContractByteCodeBean> response) {
                ContractByteCodeBean bean = response.body();
                byteCode = bean.getResult();
                addressByteCode = byteCode;
                if(addressByteCode.equals("0x")){
                    addressType.setValue("Address");
                }
                else{
                    addressType.setValue("Contract");
                }

            }

            @Override
            public void onFailure(Call<ContractByteCodeBean> call, Throwable t) {
                requestState.setValue(false);
            }
        });
    }
    public void getContractData(ContractInfoAdapter adapter){
        mServer.getContractCode(Address, new Callback<ContractCodeBean>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ContractCodeBean> call, Response<ContractCodeBean> response) {
                String abi ;
                String code ;
                abi = response.body().getResult().get(0).getAbi();
                code = response.body().getResult().get(0).getSourceCode();
                if (code.equals(""))
                    code = "-";
                if (abi.equals("Contract source code not verified"))
                    abi = "-";
                contractData.put("byteCode", byteCode);
                contractData.put("abi", abi);
                contractData.put("code",code);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ContractCodeBean> call, Throwable t) {
                requestState.setValue(false);
            }
        });
    }
    public void getTransactionsData(String page,viewpageAdapter adapter ,TransactionAdapter transactionAdapter){
        mServer.getTransactions(Address, page,new Callback<transactionsBean>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<transactionsBean> call, @NonNull Response<transactionsBean> response) {
                transactionsBean bean = response.body();
                List<transactionsBean.ResultDTO> list = bean.getResult();
                if (list.size() == 0){
                    adapter.getTransactionsPage().setValue(-1);
                    adapter.setTransactionsPageProgressBarGone();
                }else {
                    transactionList.addAll(list);
                    transactionAdapter.notifyDataSetChanged();
                    if (list.size() < 10){
                        adapter.getTransactionsPage().setValue(-1);
                        adapter.setTransactionsPageProgressBarGone();
                    }
                }
            }

            @Override
            public void onFailure(Call<transactionsBean> call, Throwable t) {
                Log.i(TAG, "onFailure: getTransactions");
                requestState.setValue(false);
            }
        });
    }
    public void getInternalTransactionsData(String page, viewpageAdapter adapter, InternalTransactionAdapter internalTransactionAdapter){
        mServer.getInternalTransactions(Address, page,new Callback<internalTransactionsBean>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<internalTransactionsBean> call, Response<internalTransactionsBean> response) {
                List<internalTransactionsBean.ResultDTO> list = response.body().getResult();
                Log.i(TAG, "onResponse: internal " + list.size() + page);
                if (list.size() == 0){
                    adapter.getInternalTransactionsPage().setValue(-1);
                    adapter.setInternalTransactionsPageProgressBarGone();
                }else {
                    internalList.addAll(list);
                    internalTransactionAdapter.notifyDataSetChanged();
                    if (list.size() < 10){
                        Log.i(TAG, "onResponse: gone");
                        adapter.setInternalTransactionsPageProgressBarGone();
                        adapter.getInternalTransactionsPage().setValue(-1);
                    }
                }
            }

            @Override
            public void onFailure(Call<internalTransactionsBean> call, Throwable t) {
                requestState.setValue(false);
            }
        });
    }

    public void getBalance(){
        mServer.getBalance(Address, new Callback<balanceBean>() {
            @Override
            public void onResponse(Call<balanceBean> call, Response<balanceBean> response) {
                String value = response.body().getResult();
                addressBalance.setValue(TextUtils.formatEther(value,null));
            }

            @Override
            public void onFailure(Call<balanceBean> call, Throwable t) {
                requestState.setValue(false);
            }
        });

    }



}
