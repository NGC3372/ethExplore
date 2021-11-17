package com.rainbowguo.ethexplore.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayoutMediator;
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
        bind.address.setText(address);
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

        viewMode.getAddressType().observe(this,s->{
            bind.role.setText(s);

            if (s.equals("Address")){
                adapter = new viewpageAdapter(transactionAdapter,internalTransactionAdapter);
                bind.viewPager.setAdapter(adapter);
                new TabLayoutMediator(bind.tabLayout, bind.viewPager, (tab, position) -> {
                    if (position == 0)
                        tab.setText("Transactions");
                    else
                        tab.setText("Internal Txns");
                }).attach();
            }else if(s.equals("Contract")){
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
            adapter.getTransactionsPage().observe(AddressFragment.this, integer ->
                    viewMode.getTransactionsData(integer.toString(),adapter, transactionAdapter));

            adapter.getInternalTransactionsPage().observe(AddressFragment.this, integer ->
                    viewMode.getInternalTransactionsData(integer.toString(),adapter, internalTransactionAdapter));
        });

        viewMode.requestAddressBytecode();
        viewMode.getBalance();
    }
}
