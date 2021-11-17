package com.rainbowguo.ethexplore.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rainbowguo.ethexplore.R;
import com.rainbowguo.ethexplore.beans.ContractCodeBean;
import com.rainbowguo.ethexplore.https.EtherScanServer;


public class viewpageAdapter extends RecyclerView.Adapter<viewpageAdapter.mViewHolder> {
    private static final String TAG = "viewpageAdapter";
    private final TransactionAdapter adapter1;
    private final InternalTransactionAdapter adapter2;
    private ContractInfoAdapter adapter3;
    private final MutableLiveData<Integer> transactionsPage = new MutableLiveData<>(1);
    private final MutableLiveData<Integer> internalTransactionsPage = new MutableLiveData<>(1);
    public boolean transactionsSearchingToEnd,internalTransactionsSearchingToEnd;
    private ProgressBar transactionsPageProgressBar,internalTransactionsPageProgressBar;


    public viewpageAdapter(TransactionAdapter adapter1, InternalTransactionAdapter adapter2){
        this.adapter1 = adapter1;
        this.adapter2 = adapter2;
        transactionsSearchingToEnd = internalTransactionsSearchingToEnd = false;
    }

    public viewpageAdapter(TransactionAdapter adapter1,InternalTransactionAdapter adapter2, ContractInfoAdapter adapter3){
        this.adapter1 = adapter1;
        this.adapter2 = adapter2;
        this.adapter3 = adapter3;
        transactionsSearchingToEnd = internalTransactionsSearchingToEnd = false;
    }

    public MutableLiveData<Integer> getTransactionsPage() {
        return transactionsPage;
    }

    public MutableLiveData<Integer> getInternalTransactionsPage() {
        return internalTransactionsPage;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_transactions, parent, false);
        view.findViewById(R.id.TransactionCard).setVisibility(View.GONE);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        RecyclerView view = holder.itemView.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(holder.itemView.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(manager);
        NestedScrollView scrollView = holder.itemView.findViewById(R.id.scrollView);
        holder.itemView.findViewById(R.id.ProgressView).setVisibility(View.GONE);
        if (position == 0){
            transactionsPageProgressBar = holder.itemView.findViewById(R.id.ProgressView);
            view.setAdapter(adapter1);
            scrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if(! scrollView.canScrollVertically(1)){
                    if (transactionsPage.getValue() != -1 && transactionsPageProgressBar.getVisibility() == View.GONE){
                        transactionsPage.setValue(transactionsPage.getValue() + 1);
                        transactionsPageProgressBar.setVisibility(View.VISIBLE);
                    }
                }
            });

        }else if(position == 1){
            view.setAdapter(adapter2);
            internalTransactionsPageProgressBar = holder.itemView.findViewById(R.id.ProgressView);
            scrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if(! scrollView.canScrollVertically(1)){
                    if (internalTransactionsPage.getValue() != -1 && internalTransactionsPageProgressBar.getVisibility() == View.GONE){
                        internalTransactionsPage.setValue(internalTransactionsPage.getValue() + 1);
                        internalTransactionsPageProgressBar.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
        else{
            view.setAdapter(adapter3);
            holder.itemView.findViewById(R.id.ProgressView).setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if(adapter3 == null)
            return 2;
        else
            return 3;
    }

    public void setTransactionsPageProgressBarGone(){
        if (transactionsPageProgressBar !=null)
            transactionsPageProgressBar.setVisibility(View.GONE);
    }

    public void setInternalTransactionsPageProgressBarGone(){
        if(internalTransactionsPageProgressBar != null)
            internalTransactionsPageProgressBar.setVisibility(View.GONE);
    }


    public static class mViewHolder extends RecyclerView.ViewHolder{
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}