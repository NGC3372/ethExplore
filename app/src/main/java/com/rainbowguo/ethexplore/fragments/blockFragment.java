package com.rainbowguo.ethexplore.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rainbowguo.ethexplore.MainActivity;
import com.rainbowguo.ethexplore.R;
import com.rainbowguo.ethexplore.Utils.myAnimation;
import com.rainbowguo.ethexplore.adapter.BlockInfoAdapter;
import com.rainbowguo.ethexplore.databinding.FragmentBlockBinding;
import com.rainbowguo.ethexplore.viewModels.blockFragmentMode;

import java.util.Objects;

public class blockFragment extends Fragment {
    private FragmentBlockBinding binding;
    private blockFragmentMode viewMode;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBlockBinding.inflate(inflater);
        return binding.getRoot();
    }

    @SuppressLint({"NotifyDataSetChanged", "ResourceAsColor"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewMode = new ViewModelProvider(this).get(blockFragmentMode.class);
        String blockNumber = getArguments().getString("blockNumber");
        viewMode.getBlockNumber().observe(this, s -> {
            binding.blockNumber.setText(s);
        });
        viewMode.getDate().observe(this, s -> {
            binding.date.setText(s);
        });
        viewMode.getMiner().observe(this, s -> {
            binding.miner.setText(s);
        });
        viewMode.getTransactionsSize().observe(this, integer -> {
            if (integer == 0){
                binding.recyclerView.setVisibility(View.GONE);
                binding.canNotFind.setVisibility(View.VISIBLE);
            }else
                Objects.requireNonNull(binding.recyclerView.getAdapter()).notifyDataSetChanged();
        });
        viewMode.getRequestState().observe(this, aBoolean -> {
            if (aBoolean){
                binding.ProgressView.setVisibility(View.GONE);
                myAnimation.SmallToBig(binding.content);
                binding.content.setVisibility(View.VISIBLE);

            }else {
                binding.ProgressView.setVisibility(View.GONE);
                binding.content.setVisibility(View.GONE);
                binding.failedContent.setVisibility(View.VISIBLE);
            }

        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(new BlockInfoAdapter(viewMode.getTransactionsList()));

        viewMode.requestBlockData(blockNumber);

        binding.miner.setTextColor(R.color.textLink);
        binding.miner.setOnClickListener(v -> {
            String text = binding.miner.getText().toString();
            if(! text.equals("")){
                MainActivity activity = (MainActivity)getActivity();
                assert activity != null;
                AddressFragment fragment = new AddressFragment();
                Bundle bundle = new Bundle();
                bundle.putString("address",text);
                fragment.setArguments(bundle);
                activity.addFragment(fragment);
            }
        });
    }
}
