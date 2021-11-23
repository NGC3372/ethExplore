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
import com.rainbowguo.ethexplore.Utils.TextUtils;
import com.rainbowguo.ethexplore.Utils.myAnimation;
import com.rainbowguo.ethexplore.adapter.InternalTransactionInfoAdapter;
import com.rainbowguo.ethexplore.adapter.TransactionsInfoAdapter;
import com.rainbowguo.ethexplore.adapter.proxy_transactionsInfoAdapter;
import com.rainbowguo.ethexplore.beans.internalTransactionsBean;
import com.rainbowguo.ethexplore.beans.transactionsBean;
import com.rainbowguo.ethexplore.databinding.FragmentTransactionsBinding;
import com.rainbowguo.ethexplore.viewModels.transactionInfoMode;


public class TransactionInfoFragment extends Fragment {
    private FragmentTransactionsBinding binding;
    private transactionInfoMode viewMode;
    private Object data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTransactionsBinding.inflate(inflater);
        return binding.getRoot();
    }


    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        data =  getArguments().getSerializable("data");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        viewMode = new ViewModelProvider(this).get(transactionInfoMode.class);
        observeData();
        initData();

    }
    @SuppressLint("ResourceAsColor")
    private  void observeData(){
        viewMode.getRequestState().observe(this, aBoolean -> {
            if (aBoolean){
                binding.ProgressView.setVisibility(View.GONE);
                binding.scrollView.setVisibility(View.VISIBLE);
                myAnimation.SmallToBig(binding.scrollView);
            }else {
                binding.scrollView.setVisibility(View.GONE);
                binding.ProgressView.setVisibility(View.GONE);
                binding.failedContent.setVisibility(View.VISIBLE);
            }


        });
        viewMode.getFromAddress().observe(this, s -> {
            myAnimation.SmallToBig(binding.scrollView);
            binding.From.setText(s);
            binding.From.setTextColor(R.color.textLink);
            binding.From.setOnClickListener(v->{
                MainActivity activity = (MainActivity) getActivity();
                AddressFragment fragment = new AddressFragment();
                Bundle bundle = new Bundle();
                bundle.putString("address",binding.From.getText().toString());
                fragment.setArguments(bundle);
                if (activity!= null)
                    activity.addFragment(fragment);
            });
        });

        viewMode.getToAddress().observe(this, s-> {
            binding.To.setText(s);
            binding.To.setTextColor(R.color.textLink);
            binding.To.setOnClickListener(v->{
                MainActivity activity = (MainActivity) getActivity();
                AddressFragment fragment = new AddressFragment();
                Bundle bundle = new Bundle();
                bundle.putString("address",binding.To.getText().toString());
                fragment.setArguments(bundle);
                if (activity!= null)
                    activity.addFragment(fragment);
            });
        });

        viewMode.getValue().observe(this,s-> binding.Value.setText(s));

        viewMode.getBean().observe(this , s->{
            binding.recyclerView.setAdapter(new proxy_transactionsInfoAdapter(s));
            viewMode.getFromAddress().setValue(s.getFrom());
            viewMode.getToAddress().setValue(s.getTo());
            String value = TextUtils.to10(s.getValue());
            if(! value.equals("0"))
                value = TextUtils.formatEther(value,null);
            else
                value = "0 Ether";
            viewMode.getValue().setValue(value);

        });
    }
    private void initData(){
        if(data instanceof transactionsBean.ResultDTO){
            viewMode.getRequestState().setValue(true);
            transactionsBean.ResultDTO bean = (transactionsBean.ResultDTO )data;
            binding.recyclerView.setAdapter(new TransactionsInfoAdapter(bean));
            viewMode.getFromAddress().setValue(bean.getFrom());
            viewMode.getToAddress().setValue(bean.getTo());
            String value = TextUtils.formatEther(bean.getValue(),null);
            viewMode.getValue().setValue(value);
        }else if (data instanceof internalTransactionsBean.ResultDTO){
            viewMode.getRequestState().setValue(true);
            internalTransactionsBean.ResultDTO bean = (internalTransactionsBean.ResultDTO)data;
            binding.recyclerView.setAdapter(new InternalTransactionInfoAdapter(bean));
            viewMode.getFromAddress().setValue(bean.getFrom());
            viewMode.getToAddress().setValue(bean.getTo());
            String value = TextUtils.formatEther(bean.getValue(),null);
            viewMode.getValue().setValue(value);
        }else if(data instanceof String){
            viewMode.requestProsy_TransactionsInfo((String)data);
        }

    }

}
