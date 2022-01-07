package com.rainbowguo.ethexplore.adapter

import com.rainbowguo.ethexplore.beans.transactionsBean
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.rainbowguo.ethexplore.R
import android.annotation.SuppressLint
import com.rainbowguo.ethexplore.fragments.blockFragment
import android.os.Bundle
import android.view.View
import com.rainbowguo.ethexplore.MainActivity
import android.widget.TextView
import com.rainbowguo.ethexplore.NewMainActivity
import com.rainbowguo.ethexplore.Utils.TextUtils

class TransactionsInfoAdapter(private val bean: transactionsBean.ResultDTO) :
    RecyclerView.Adapter<TransactionsInfoAdapter.mViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transactions_info, parent, false)
        return mViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.name.text = "hash" //
                holder.value.text = bean.hash
            }
            1 -> {
                holder.name.text = "date" //
                holder.value.text = TextUtils.timeStampFormat(bean.timeStamp)
            }
            2 -> {
                holder.name.text = "blockNumber" //
                holder.value.text = bean.blockNumber
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
            3 -> {
                holder.name.text = "nonce"
                holder.value.text = bean.nonce
            }
            4 -> {
                holder.name.text = "blockHash"
                holder.value.text = bean.blockHash
            }
            5 -> {
                holder.name.text = "transactionIndex"
                holder.value.text = bean.transactionIndex
            }
            6 -> {
                holder.name.text = "gas" //
                holder.value.text = bean.gas
            }
            7 -> {
                holder.name.text = "gasPrice"
                holder.value.text = TextUtils.formatEther(bean.gasPrice, "0.00000000000000000")
            }
            8 -> {
                holder.name.text = "isError" //
                holder.value.text = bean.isError
            }
            9 -> {
                holder.name.text = "txreceipt_status"
                holder.value.text = bean.txreceiptStatus
            }
            10 -> {
                holder.name.text = "cumulativeGasUsed"
                holder.value.text = bean.cumulativeGasUsed
            }
            11 -> {
                holder.name.text = "gasUsed" //
                holder.value.text = bean.gasUsed
            }
            12 -> {
                holder.name.text = "confirmations"
                holder.value.text = bean.confirmations
            }
            13 -> {
                holder.name.text = "contractAddress" //
                var contractAddress = bean.contractAddress
                if (contractAddress == "") contractAddress = "-"
                holder.value.text = contractAddress
            }
            14 -> {
                holder.name.text = "input" //
                var input = bean.input
                if (input == "0x") input = "-"
                holder.value.text = input
            }
        }
    }

    override fun getItemCount(): Int {
        return 15
    }

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var value: TextView = itemView.findViewById(R.id.value)

    }
}