package com.rainbowguo.ethexplore.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rainbowguo.ethexplore.MainActivity;
import com.rainbowguo.ethexplore.R;
import com.rainbowguo.ethexplore.fragments.TransactionInfoFragment;


import java.util.List;

public class BlockInfoAdapter extends RecyclerView.Adapter<BlockInfoAdapter.mViewHolder> {
    private List<String> transactionsList ;

    public BlockInfoAdapter(List<String> list){
        this.transactionsList = list;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_block_info,parent,false);

        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        holder.text.setText(transactionsList.get(position));
        holder.rootView.setOnClickListener(v->{
            MainActivity activity = (MainActivity) holder.itemView.getContext();
            TransactionInfoFragment fragment = new TransactionInfoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("data",transactionsList.get(position));
            fragment.setArguments(bundle);
            activity.addFragment(fragment);
            /*Intent intent = new Intent(holder.itemView.getContext(), TransactionInfoActivity.class);
            intent.putExtra("data",transactionsList.get(position));
            holder.itemView.getContext().startActivity(intent);*/
        });
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        public TextView text;
        public LinearLayout rootView;
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.transactionsHash);
            rootView = itemView.findViewById(R.id.rootView);
        }
    }

}
