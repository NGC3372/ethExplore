package com.rainbowguo.ethexplore.adapter

import com.rainbowguo.ethexplore.beans.proxy_transactionsInfoBean
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.rainbowguo.ethexplore.R
import android.annotation.SuppressLint
import com.rainbowguo.ethexplore.fragments.blockFragment
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.rainbowguo.ethexplore.NewMainActivity
import com.rainbowguo.ethexplore.Utils.TextUtils

class proxy_transactionsInfoAdapter(private val bean : proxy_transactionsInfoBean.ResultDTO) :
    RecyclerView.Adapter<proxy_transactionsInfoAdapter.mViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transactions_info, parent, false)
        return mViewHolder(view)
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.name.text = "hash"
                holder.value.text = bean.hash
            }
            1 -> {
                holder.name.text = "gas"
                holder.value.text = TextUtils.to10(bean.gas)
            }
            2 -> {
                holder.name.text = "gasPrice"
                val gasPrice = TextUtils.to10(bean.gasPrice)
                holder.value.text = TextUtils.formatEther(gasPrice, "0.00000000000000000")
            }
            3 -> {
                holder.name.text = "maxFeePerGas"
                holder.value.text = TextUtils.to10(bean.maxFeePerGas)
            }
            4 -> {
                holder.name.text = "maxPriorityFeePerGas"
                holder.value.text = TextUtils.to10(bean.maxPriorityFeePerGas)
            }
            5 -> {
                holder.name.text = "blockNumber"
                holder.value.text = TextUtils.to10(bean.blockNumber)
                holder.value.setTextColor(R.color.textLink)
                holder.value.setOnClickListener { v: View? ->
                    val fragment = blockFragment()
                    val bundle = Bundle()
                    bundle.putString("blockNumber", holder.value.text.toString())
                    fragment.arguments = bundle
                    val activity = holder.value.context as NewMainActivity
                    activity.addFragment(fragment)
                }
            }
            6 -> {
                holder.name.text = "nonce"
                holder.value.text = TextUtils.to10(bean.nonce)
            }
            7 -> {
                holder.name.text = "transactionIndex"
                holder.value.text = TextUtils.to10(bean.transactionIndex)
            }
            8 -> {
                holder.name.text = "input"
                var input = bean.input
                if (input == "0x") input = "-"
                holder.value.text = input
            }
        }
    }

    override fun getItemCount(): Int {
        return 9
    }

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var value: TextView = itemView.findViewById(R.id.value)

    }
}