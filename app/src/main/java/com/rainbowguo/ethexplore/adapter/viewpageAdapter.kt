package com.rainbowguo.ethexplore.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rainbowguo.ethexplore.R;

import java.util.ArrayList;


public class viewpageAdapter extends RecyclerView.Adapter<viewpageAdapter.mViewHolder> {
    private static final String TAG = "viewpageAdapter";
    private final TransactionAdapter adapter1;
    private final InternalTransactionAdapter adapter2;
    private ContractInfoAdapter adapter3;
    private final MutableLiveData<Integer> transactionsPage = new MutableLiveData<>(1);
    private final MutableLiveData<Integer> internalTransactionsPage = new MutableLiveData<>(1);
    private final ArrayList<mViewHolder> holderList;

    public viewpageAdapter(TransactionAdapter adapter1, InternalTransactionAdapter adapter2){
        this.adapter1 = adapter1;
        this.adapter2 = adapter2;
        holderList = new ArrayList<>();
    }

    public viewpageAdapter(TransactionAdapter adapter1,InternalTransactionAdapter adapter2, ContractInfoAdapter adapter3){
        this.adapter1 = adapter1;
        this.adapter2 = adapter2;
        this.adapter3 = adapter3;
        holderList = new ArrayList<>();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transactions_list, parent, false);
        mViewHolder holder = new mViewHolder(view);
        holderList.add(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        if (position == 0){
            holder.recyclerView.setAdapter(adapter1);
            holder.scrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                 Integer pageValue = transactionsPage.getValue();
                 if(! holder.scrollView.canScrollVertically(1) && pageValue!= null && pageValue != -1){
                     transactionsPage.setValue(pageValue + 1);
                 }
            });
        }else if(position == 1){
            holder.recyclerView.setAdapter(adapter2);
            holder.scrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                Integer pageValue = internalTransactionsPage.getValue();
                if(! holder.scrollView.canScrollVertically(1)&& pageValue!= null && pageValue != -1){
                    internalTransactionsPage.setValue(pageValue + 1);
                }
            });
        }
        else if(position == 2){
            holder.recyclerView.setAdapter(adapter3);
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
        holderList.get(0).progressBar.setVisibility(View.GONE);
    }

    public void setInternalTransactionsPageProgressBarGone(){
        holderList.get(1).progressBar.setVisibility(View.GONE);

    }
    public void setTransactionsNullContent(){
        holderList.get(0).scrollView.setVisibility(View.GONE);
        holderList.get(0).nullContentView.setVisibility(View.VISIBLE);
    }

    public void setInternalTransactionsNullContent(){
        holderList.get(1).scrollView.setVisibility(View.GONE);
        holderList.get(1).nullContentView.setVisibility(View.VISIBLE);
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView recyclerView ;
        public ProgressBar progressBar;
        public NestedScrollView scrollView;
        public ImageView nullContentView;
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            LinearLayoutManager manager = new LinearLayoutManager(itemView.getContext());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);

            progressBar = itemView.findViewById(R.id.ProgressView);
            scrollView = itemView.findViewById(R.id.scrollView);
            nullContentView = itemView.findViewById(R.id.nullContent);
        }
    }
}