package com.rainbowguo.ethexplore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.rainbowguo.ethexplore.Utils.myAnimation;
import com.rainbowguo.ethexplore.databinding.FragmentMainBinding;
import com.rainbowguo.ethexplore.viewModels.homeFragmentMode;

public class HomeFragment extends Fragment {
    private FragmentMainBinding bind;
    private homeFragmentMode viewMode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = FragmentMainBinding.inflate(getLayoutInflater());
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bind.refreshButton.setOnClickListener(v->{
            requestData();
            myAnimation.rotationView(bind.refreshImg);
        });
        observedData();
        requestData();
    }

    private void observedData(){
        viewMode = new ViewModelProvider(this).get(homeFragmentMode.class);
        viewMode.getRequestTime().observe(this, s -> bind.requestTime.setText(s));
        viewMode.getNumberOfTotalBlock().observe(this,s-> bind.blockNumber.setText(s));
        viewMode.getNumberOfTotalNode().observe(this, s-> bind.nodesNumber.setText(s));
        viewMode.getNumberOfTotalEth().observe(this,s->bind.ethNumber.setText(s));
        viewMode.getPriceOfEthToBTC().observe(this,s->bind.ethBtc.setText(s));
        viewMode.getPriceOfEthToUSDT().observe(this,s->bind.ethUsdt.setText(s));
        viewMode.getNumberOfEth2Staking().observe(this,s->bind.eth2staking.setText(s));
        viewMode.getNumberOfBurntFees().observe(this,s->bind.BurntFees.setText(s));
        viewMode.getChainSize().observe(this,s->bind.chainSize.setText(s));
    }

    private void requestData(){
        viewMode.clearData();
        viewMode.setRequestTime();

        viewMode.getEthPrice();
        viewMode.getTotalEthNumber();
        viewMode.getTotalBlockNumber();
        viewMode.getEthSize();
        viewMode.getTotalNodes();

    }
}
