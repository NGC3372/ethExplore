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
import com.rainbowguo.ethexplore.beans.internalTransactionsBean;
import com.rainbowguo.ethexplore.fragments.blockFragment;

public class InternalTransactionInfoAdapter extends RecyclerView.Adapter<InternalTransactionInfoAdapter.mViewHolder> {
    private final internalTransactionsBean.ResultDTO bean;
    public InternalTransactionInfoAdapter(internalTransactionsBean.ResultDTO bean){
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
            case 0: {
                holder.name.setText("hash");//
                holder.value.setText(bean.getHash());
                break;
            }
            case 1: {
                holder.name.setText("date");//
                holder.value.setText(TextUtils.timeStampFormat(bean.getTimeStamp()));
                break;
            }

            case 2: {
                holder.name.setText("blockNumber");//
                holder.value.setText(bean.getBlockNumber());
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
            }

            case 3: {
                holder.name.setText("type");
                holder.value.setText(bean.getType());
                break;
            }
            case 4: {
                holder.name.setText("gas");//
                holder.value.setText(bean.getGas());
                break;
            }
            case 5: {
                holder.name.setText("gasUsed");//
                holder.value.setText(bean.getGasUsed());
                break;
            }

            case 6: {
                holder.name.setText("traceID");
                holder.value.setText(bean.getTraceId());
                break;
            }
            case 7: {
                holder.name.setText("isError");//
                holder.value.setText(bean.getIsError());
                break;
            }
            case 8: {
                holder.name.setText("errCode");
                String errCode = bean.getErrCode();
                if (errCode.equals(""))
                    errCode = "-";
                holder.value.setText(errCode);
                break;
            }
            case 9: {
                holder.name.setText("contractAddress");//
                String contractAddress = bean.getContractAddress();
                if (contractAddress.equals(""))
                    contractAddress = "-";
                holder.value.setText(contractAddress);
                break;
            }
            case 10: {
                holder.name.setText("input");//
                String input = bean.getInput();
                if (input.equals(""))
                    input= "-";
                holder.value.setText(input);
                break;
            }

        }
    }

    @Override
    public int getItemCount() {
        return 11;
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
