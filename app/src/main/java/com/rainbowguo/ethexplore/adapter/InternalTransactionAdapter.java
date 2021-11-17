package com.rainbowguo.ethexplore.adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rainbowguo.ethexplore.MainActivity;
import com.rainbowguo.ethexplore.R;
import com.rainbowguo.ethexplore.Utils.TextUtils;
import com.rainbowguo.ethexplore.beans.internalTransactionsBean;
import com.rainbowguo.ethexplore.fragments.TransactionInfoFragment;

import java.util.ArrayList;

public class InternalTransactionAdapter extends RecyclerView.Adapter<InternalTransactionAdapter.mViewHolder> {
    private static final String TAG = "internalTransactionAdapter";
    private ArrayList<internalTransactionsBean.ResultDTO> internalTransactionList;
    private final String userAddress;

    public InternalTransactionAdapter(ArrayList<internalTransactionsBean.ResultDTO> list,String userAddress){
        this.internalTransactionList = list;
        this.userAddress = userAddress;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_transaction,parent,false);
        Log.i(TAG, "onCreateViewHolder: " );
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: binddata" );
        internalTransactionsBean.ResultDTO transaction = internalTransactionList.get(position);
        String fromAddress = transaction.getFrom();
        String toAddress = transaction.getTo();
        String time = transaction.getTimeStamp();
        String value = transaction.getValue();
        if (fromAddress.equals(userAddress)){
            holder.address.setText(toAddress);
            holder.role.setText("Send To");
        }
        else {
            holder.address.setText(fromAddress);
            holder.role.setText("Get from");
        }
        holder.time.setText(TextUtils.timeStampFormat(time));
        holder.value.setText(TextUtils.formatEther(value,null));
        holder.rootView.setOnClickListener(v->{
            MainActivity activity = (MainActivity) holder.itemView.getContext();
            TransactionInfoFragment fragment = new TransactionInfoFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",transaction);
            fragment.setArguments(bundle);
            activity.addFragment(fragment);

            //holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), TransactionInfoActivity.class).putExtra("data",transaction));
        });
    }

    @Override
    public int getItemCount() {
        return internalTransactionList.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        public TextView role,address,time,value;
        public LinearLayout rootView;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            role = itemView.findViewById(R.id.role);
            address = itemView.findViewById(R.id.address);
            time = itemView.findViewById(R.id.time);
            value = itemView.findViewById(R.id.value);
            rootView = itemView.findViewById(R.id.rootView);
        }
    }
}
