package com.rainbowguo.ethexplore.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rainbowguo.ethexplore.adapter.TransactionAdapter
import com.rainbowguo.ethexplore.adapter.InternalTransactionAdapter
import com.rainbowguo.ethexplore.adapter.ContractInfoAdapter
import androidx.lifecycle.MutableLiveData
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.rainbowguo.ethexplore.R
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.ArrayList

class viewpageAdapter : RecyclerView.Adapter<viewpageAdapter.mViewHolder> {
    private val adapter1: TransactionAdapter
    private val adapter2: InternalTransactionAdapter
    private var adapter3: ContractInfoAdapter? = null
    val transactionsPage = MutableLiveData(1)
    val internalTransactionsPage = MutableLiveData(1)
    private val holderList: ArrayList<mViewHolder>

    companion object {
        private const val TAG = "viewpageAdapter"
    }

    constructor(adapter1: TransactionAdapter, adapter2: InternalTransactionAdapter) {
        this.adapter1 = adapter1
        this.adapter2 = adapter2
        holderList = ArrayList()
    }

    constructor(
        adapter1: TransactionAdapter,
        adapter2: InternalTransactionAdapter,
        adapter3: ContractInfoAdapter?
    ) {
        this.adapter1 = adapter1
        this.adapter2 = adapter2
        this.adapter3 = adapter3
        holderList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.transactions_list, parent, false)
        val holder = mViewHolder(view)
        holderList.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.recyclerView.adapter = adapter1
                holder.scrollView.setOnScrollChangeListener(View.OnScrollChangeListener { _, _, _, _, _ ->
                    val pageValue = transactionsPage.value
                    if (!holder.scrollView.canScrollVertically(1) && pageValue != null && pageValue != -1) {
                        transactionsPage.value = pageValue + 1
                    }
                })
            }
            1 -> {
                holder.recyclerView.adapter = adapter2
                holder.scrollView.setOnScrollChangeListener(View.OnScrollChangeListener { _, _, _, _, _ ->
                    val pageValue = internalTransactionsPage.value
                    if (!holder.scrollView.canScrollVertically(1) && pageValue != null && pageValue != -1) {
                        internalTransactionsPage.value = pageValue + 1
                    }
                })
            }
            2 -> {
                holder.recyclerView.adapter = adapter3
                holder.itemView.findViewById<View>(R.id.ProgressView).visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return if (adapter3 == null) 2 else 3
    }

    fun setTransactionsPageProgressBarGone() {
        holderList[0].progressBar.visibility = View.GONE
    }

    fun setInternalTransactionsPageProgressBarGone() {
        holderList[1].progressBar.visibility = View.GONE
    }

    fun setTransactionsNullContent() {
        holderList[0].scrollView.visibility = View.GONE
        holderList[0].nullContentView.visibility = View.VISIBLE
    }

    fun setInternalTransactionsNullContent() {
        holderList[1].scrollView.visibility = View.GONE
        holderList[1].nullContentView.visibility = View.VISIBLE
    }

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)
        var progressBar: ProgressBar = itemView.findViewById(R.id.ProgressView)
        var scrollView: NestedScrollView = itemView.findViewById(R.id.scrollView)
        var nullContentView: ImageView = itemView.findViewById(R.id.nullContent)

        init {
            val manager = LinearLayoutManager(itemView.context)
            manager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = manager

        }
    }


}