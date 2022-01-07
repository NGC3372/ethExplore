package com.rainbowguo.ethexplore.adapter

import com.rainbowguo.ethexplore.beans.internalTransactionsBean
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.rainbowguo.ethexplore.R
import com.rainbowguo.ethexplore.adapter.InternalTransactionAdapter
import com.rainbowguo.ethexplore.MainActivity
import com.rainbowguo.ethexplore.fragments.TransactionInfoFragment
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout
import com.rainbowguo.ethexplore.NewMainActivity
import com.rainbowguo.ethexplore.Utils.TextUtils
import java.util.ArrayList

class InternalTransactionAdapter(
    private val internalTransactionList: ArrayList<internalTransactionsBean.ResultDTO>,
    private val userAddress: String
) : RecyclerView.Adapter<InternalTransactionAdapter.mViewHolder>() {

    companion object {
        private const val TAG = "internalTransactionAdapter"
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        Log.i(TAG, "onCreateViewHolder: ")
        return mViewHolder(view)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder: binddata")
        val transaction = internalTransactionList[position]
        val fromAddress = transaction.from
        val toAddress = transaction.to
        val time = transaction.timeStamp
        val value = transaction.value
        if (fromAddress == userAddress) {
            holder.address.text = toAddress
            holder.role.text = "Send To"
            holder.typeImg.setImageResource(R.drawable.get)
        } else {
            holder.address.text = fromAddress
            holder.role.text = "Get from"
            holder.typeImg.setImageResource(R.drawable.send)
        }
        holder.time.text = TextUtils.timeStampFormat(time)
        holder.value.text = TextUtils.formatEther(value, null)
        holder.rootView.setOnClickListener {
            val activity = holder.itemView.context as NewMainActivity
            val fragment = TransactionInfoFragment()
            val bundle = Bundle()
            bundle.putSerializable("data", transaction)
            fragment.arguments = bundle
            activity.addFragment(fragment)
        }
    }

    override fun getItemCount(): Int {
        return internalTransactionList.size
    }

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var role: TextView = itemView.findViewById(R.id.role)
        var address: TextView = itemView.findViewById(R.id.address)
        var time: TextView = itemView.findViewById(R.id.time)
        var value: TextView = itemView.findViewById(R.id.value)
        var rootView: LinearLayout = itemView.findViewById(R.id.rootView)
        var typeImg: ImageView = itemView.findViewById(R.id.type)

    }


}