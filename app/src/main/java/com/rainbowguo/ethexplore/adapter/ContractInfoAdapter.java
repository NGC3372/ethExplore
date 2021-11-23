package com.rainbowguo.ethexplore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rainbowguo.ethexplore.R;
import com.rainbowguo.ethexplore.Utils.copyUtils;
import com.rainbowguo.ethexplore.Utils.mToast;

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
        String name = "";
        switch (position){
            case 0: data += contractData.get("abi");name= "ABI";break;
            case 1: data += contractData.get("code");name = "Code";break;
            case 2: data += contractData.get("byteCode");name = "ByteCode";break;
        }
        holder.value.setText(data);
        holder.name.setText(name);
        holder.copyImg.setOnClickListener(v-> {
            copyUtils.copy(holder.value.getText().toString(),holder.name.getContext());
            mToast.showCopy();
        });
    }

    @Override
    public int getItemCount() {
        return contractData.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        public TextView value;
        public TextView name;
        public ImageView copyImg;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.contract);
            name = itemView.findViewById(R.id.name);
            copyImg = itemView.findViewById(R.id.copy);
        }
    }
}
