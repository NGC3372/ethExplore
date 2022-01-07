package com.rainbowguo.ethexplore.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.rainbowguo.ethexplore.R
import com.rainbowguo.ethexplore.NewMainActivity
import com.rainbowguo.ethexplore.fragments.TransactionInfoFragment
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.LinearLayout

class BlockInfoAdapter(private val transactionsList: List<String>) :
    RecyclerView.Adapter<BlockInfoAdapter.mViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_block_info, parent, false)
        return mViewHolder(view)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.text.text = transactionsList[position]
        holder.rootView.setOnClickListener {
            val activity = holder.itemView.context as NewMainActivity
            val fragment = TransactionInfoFragment()
            val bundle = Bundle()
            bundle.putString("data", transactionsList[position])
            fragment.arguments = bundle
            activity.addFragment(fragment)
        }
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.transactionsHash)
        var rootView: LinearLayout = itemView.findViewById(R.id.rootView)

    }
}