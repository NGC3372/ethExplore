package com.rainbowguo.ethexplore.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayoutMediator;
import com.rainbowguo.ethexplore.R;
import com.rainbowguo.ethexplore.Utils.mToast;
import com.rainbowguo.ethexplore.Utils.myAnimation;
import com.rainbowguo.ethexplore.adapter.ContractInfoAdapter;
import com.rainbowguo.ethexplore.adapter.InternalTransactionAdapter;
import com.rainbowguo.ethexplore.adapter.TransactionAdapter;
import com.rainbowguo.ethexplore.adapter.viewpageAdapter;
import com.rainbowguo.ethexplore.databinding.FragmentAddressBinding;
import com.rainbowguo.ethexplore.viewModels.addressFragmentMode;

public class AddressFragment extends Fragment {
    private static final String TAG = "addressFragment";
    private FragmentAddressBinding bind;
    private addressFragmentMode viewMode;
    private String address;
    private viewpageAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = FragmentAddressBinding.inflate(inflater);
        assert getArguments() != null;
        address = getArguments().getString("address");
        bind.viewPager.setOffscreenPageLimit(1);
        return bind.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewMode = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T)new addressFragmentMode(address);
            }
        }).get(addressFragmentMode.class);


        viewMode.getAddressBalance().observe(this,s->bind.balance.setText("Balance:" + s));

        TransactionAdapter transactionAdapter = new TransactionAdapter(viewMode.getTransactionList(),address);
        InternalTransactionAdapter internalTransactionAdapter = new InternalTransactionAdapter(viewMode.getInternalList(),address);
        ContractInfoAdapter contractInfoAdapter = new ContractInfoAdapter(viewMode.getContractData());

        viewMode.getRequestState().observe(this,s->{
            if (!s){
                mToast.showToastRequestFail();
            }
        });

        viewMode.getAddressType().observe(this,s->{
            bind.content.setVisibility(View.VISIBLE);
            bind.progressBar.setVisibility(View.GONE);
            myAnimation.SmallToBig(bind.content);
            bind.role.setText(s);
            bind.address.setText(address);
            if (s.equals("Address")){
                bind.roleImg.setImageResource(R.drawable.user);
                adapter = new viewpageAdapter(transactionAdapter,internalTransactionAdapter);
                bind.viewPager.setAdapter(adapter);
                new TabLayoutMediator(bind.tabLayout, bind.viewPager, (tab, position) -> {
                    if (position == 0)
                        tab.setText("Transactions");
                    else
                        tab.setText("Internal Txns");
                }).attach();
            }else if(s.equals("Contract")){
                bind.roleImg.setImageResource(R.drawable.contract);
                adapter = new viewpageAdapter(transactionAdapter,internalTransactionAdapter,contractInfoAdapter);
                bind.viewPager.setAdapter(adapter);
                new TabLayoutMediator(bind.tabLayout, bind.viewPager, (tab, position) -> {
                    if (position == 0)
                        tab.setText("Transactions");
                    else if(position == 1)
                        tab.setText("Internal Txns");
                    else
                        tab.setText("Contract");
                }).attach();
                viewMode.getContractData(contractInfoAdapter);
            }
            adapter.getTransactionsPage().observe(AddressFragment.this, integer ->{
                if (integer != -1)
                    viewMode.getTransactionsData(integer.toString(),adapter, transactionAdapter);
                else
                    if (viewMode.getTransactionList().size() == 0){
                        Log.i(TAG, "onViewCreated: transaction 000000");
                        adapter.setTransactionsNullContent();
                    }
                }
            );

            adapter.getInternalTransactionsPage().observe(AddressFragment.this, integer ->{
                if (integer != -1)
                    viewMode.getInternalTransactionsData(integer.toString(),adapter, internalTransactionAdapter);
                else
                    if (viewMode.getInternalList().size() == 0){
                        Log.i(TAG, "onViewCreated: internaltransaction 000000");
                        adapter.setInternalTransactionsNullContent();
                    }
                }
            );
        });

        viewMode.requestAddressBytecode();
        viewMode.getBalance();
    }
}
