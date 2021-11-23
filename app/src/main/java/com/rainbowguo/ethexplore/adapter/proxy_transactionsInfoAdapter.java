package com.rainbowguo.ethexplore.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rainbowguo.ethexplore.MainActivity;
import com.rainbowguo.ethexplore.R;
import com.rainbowguo.ethexplore.Utils.TextUtils;
import com.rainbowguo.ethexplore.beans.proxy_transactionsInfoBean;
import com.rainbowguo.ethexplore.fragments.TransactionInfoFragment;
import com.rainbowguo.ethexplore.fragments.blockFragment;

public class proxy_transactionsInfoAdapter extends RecyclerView.Adapter<proxy_transactionsInfoAdapter.mViewHolder> {
    private proxy_transactionsInfoBean.ResultDTO bean;
    public proxy_transactionsInfoAdapter(proxy_transactionsInfoBean.ResultDTO bean){
        this.bean = bean;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transactions_info,parent,false);
        return new mViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        switch (position){
            case 0:  holder.name.setText("hash");
                holder.value.setText(bean.getHash());
                break;
            case 1:  holder.name.setText("gas");
                holder.value.setText(TextUtils.to10(bean.getGas()));
                break;
            case 2:  holder.name.setText("gasPrice");
                String gasPrice = TextUtils.to10(bean.getGasPrice());
                holder.value.setText(TextUtils.formatEther(gasPrice,"0.00000000000000000"));
                break;
            case 3:  holder.name.setText("maxFeePerGas");
                holder.value.setText(TextUtils.to10(bean.getMaxFeePerGas()));
                break;
            case 4:  holder.name.setText("maxPriorityFeePerGas");
                holder.value.setText(TextUtils.to10(bean.getMaxPriorityFeePerGas()));
                break;
            case 5:  holder.name.setText("blockNumber");
                holder.value.setText(TextUtils.to10(bean.getBlockNumber()));
                holder.value.setTextColor(R.color.textLink);
                holder.value.setOnClickListener(v -> {
                    blockFragment fragment = new blockFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("blockNumber",holder.value.getText().toString());
                    fragment.setArguments(bundle);
                    MainActivity activity = (MainActivity)holder.value.getContext();
                    activity.addFragment(fragment);
                });
                break;
            case 6:  holder.name.setText("nonce");
                holder.value.setText(TextUtils.to10(bean.getNonce()));
                break;
            case 7:  holder.name.setText("transactionIndex");
                holder.value.setText(TextUtils.to10(bean.getTransactionIndex()));
                break;
            case 8:  holder.name.setText("input");
                String input = bean.getInput();
                if (input.equals("0x"))
                    input = "-";
                holder.value.setText(input);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return 9;
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        public TextView name , value;
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            value = itemView.findViewById(R.id.value);
        }
    }
}
