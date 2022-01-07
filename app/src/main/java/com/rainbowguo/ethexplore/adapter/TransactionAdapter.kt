package com.rainbowguo.ethexplore.adapter

import com.rainbowguo.ethexplore.beans.transactionsBean
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.rainbowguo.ethexplore.R
import com.rainbowguo.ethexplore.adapter.TransactionAdapter
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
import java.util.*

class TransactionAdapter(
    private val TransactionList: ArrayList<transactionsBean.ResultDTO>,
    private val userAddress: String
) : RecyclerView.Adapter<TransactionAdapter.mViewHolder>() {

    companion object {
        private const val TAG = "addressFragmentMode"
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        Log.i(TAG, "onCreateViewHolder: ")
        return mViewHolder(view)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val transaction = TransactionList[position]
        val fromAddress = transaction.from.uppercase(Locale.getDefault())
        val toAddress = transaction.to.uppercase(Locale.getDefault())
        val time = transaction.timeStamp
        val value = transaction.value
        Log.i(TAG, "fromAddress $fromAddress")
        Log.i(TAG, "toAddress $toAddress")
        holder.rootView.setOnClickListener {
            val activity = holder.itemView.context as NewMainActivity
            val fragment = TransactionInfoFragment()
            val bundle = Bundle()
            bundle.putSerializable("data", transaction)
            fragment.arguments = bundle
            activity.addFragment(fragment)
        }
        if (fromAddress == userAddress.uppercase(Locale.getDefault())) {
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
    }

    override fun getItemCount(): Int {
        return TransactionList.size
    }

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val role: TextView = itemView.findViewById(R.id.role)
        val address: TextView = itemView.findViewById(R.id.address)
        val time: TextView = itemView.findViewById(R.id.time)
        val value: TextView = itemView.findViewById(R.id.value)
        val rootView: LinearLayout = itemView.findViewById(R.id.rootView)
        val typeImg: ImageView = itemView.findViewById(R.id.type)

    }


}