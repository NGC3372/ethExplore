package com.rainbowguo.ethexplore.adapter

import com.rainbowguo.ethexplore.beans.internalTransactionsBean
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

class InternalTransactionInfoAdapter(private val bean: internalTransactionsBean.ResultDTO) :
    RecyclerView.Adapter<InternalTransactionInfoAdapter.mViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transactions_info, parent, false)
        return mViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.name.text = "hash"
                holder.value.text = bean.hash
            }
            1 -> {
                holder.name.text = "date"
                holder.value.text = TextUtils.timeStampFormat(bean.timeStamp)
            }
            2 -> {
                holder.name.text = "blockNumber"
                holder.value.text = bean.blockNumber
                holder.value.setTextColor(R.color.textLink)
                holder.value.setOnClickListener {
                    val fragment = blockFragment()
                    val bundle = Bundle()
                    bundle.putString("blockNumber", holder.value.text.toString())
                    fragment.arguments = bundle
                    val activity = holder.value.context as NewMainActivity
                    activity.addFragment(fragment)
                }
            }
            3 -> {
                holder.name.text = "type"
                holder.value.text = bean.type
            }
            4 -> {
                holder.name.text = "gas" //
                holder.value.text = bean.gas
            }
            5 -> {
                holder.name.text = "gasUsed" //
                holder.value.text = bean.gasUsed
            }
            6 -> {
                holder.name.text = "traceID"
                holder.value.text = bean.traceId
            }
            7 -> {
                holder.name.text = "isError" //
                holder.value.text = bean.isError
            }
            8 -> {
                holder.name.text = "errCode"
                var errCode = bean.errCode
                if (errCode == "") errCode = "-"
                holder.value.text = errCode
            }
            9 -> {
                holder.name.text = "contractAddress" //
                var contractAddress = bean.contractAddress
                if (contractAddress == "") contractAddress = "-"
                holder.value.text = contractAddress
            }
            10 -> {
                holder.name.text = "input" //
                var input = bean.input
                if (input == "") input = "-"
                holder.value.text = input
            }
        }
    }

    override fun getItemCount(): Int {
        return 11
    }

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var value: TextView = itemView.findViewById(R.id.value)

    }
}