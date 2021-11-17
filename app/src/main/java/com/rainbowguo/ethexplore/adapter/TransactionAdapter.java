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
import com.rainbowguo.ethexplore.beans.transactionsBean;
import com.rainbowguo.ethexplore.fragments.TransactionInfoFragment;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.mViewHolder> {

    private static final String TAG = "addressFragmentMode";
    private final String userAddress;
    private ArrayList<transactionsBean.ResultDTO> TransactionList;


    public TransactionAdapter(ArrayList<transactionsBean.ResultDTO> TransactionList, String userAddress){
        this.TransactionList = TransactionList;
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
        transactionsBean.ResultDTO transaction = TransactionList.get(position);
        String fromAddress = transaction.getFrom().toUpperCase();
        String toAddress = transaction.getTo().toUpperCase();
        String time = transaction.getTimeStamp();
        String value = transaction.getValue();
        Log.i(TAG, "fromAddress "+ fromAddress );
        Log.i(TAG, "toAddress "+ toAddress );
        holder.rootView.setOnClickListener(v -> {
            MainActivity activity = (MainActivity) holder.itemView.getContext();
            TransactionInfoFragment fragment = new TransactionInfoFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",transaction);
            fragment.setArguments(bundle);
            activity.addFragment(fragment);
        });
        if (fromAddress.equals(userAddress.toUpperCase())){
            holder.address.setText(toAddress);
            holder.role.setText("Send To");
        }
        else{
            holder.address.setText(fromAddress);
            holder.role.setText("Get from");
        }
        holder.time.setText(TextUtils.timeStampFormat(time));
        holder.value.setText(TextUtils.formatEther(value,null));


    }


    @Override
    public int getItemCount() {
        return TransactionList.size();
    }


    public static class mViewHolder extends RecyclerView.ViewHolder{
        public TextView role,address,time,value;
        public LinearLayout rootView;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);

            rootView = itemView.findViewById(R.id.rootView);
            role = itemView.findViewById(R.id.role);
            address = itemView.findViewById(R.id.address);
            time = itemView.findViewById(R.id.time);
            value = itemView.findViewById(R.id.value);
        }
    }
}

