package com.rainbowguo.ethexplore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rainbowguo.ethexplore.R;

import java.util.HashMap;

public class ContractInfoAdapter extends RecyclerView.Adapter<ContractInfoAdapter.mViewHolder> {
    private final HashMap<String,String> contractData;

    public ContractInfoAdapter(HashMap<String,String> contractData ){
        this.contractData = contractData;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contract,parent,false);

        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        String data = "";
        switch (position){
            case 0: data += contractData.get("abi");break;
            case 1: data += contractData.get("code");break;
            case 2: data += contractData.get("byteCode");break;
        }
        holder.textView.setText(data);
    }

    @Override
    public int getItemCount() {
        return contractData.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.contract);
        }
    }
}
