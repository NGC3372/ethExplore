package com.rainbowguo.ethexplore.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.rainbowguo.ethexplore.R
import com.rainbowguo.ethexplore.Utils.copyUtils
import com.rainbowguo.ethexplore.Utils.mToast
import android.widget.TextView
import java.util.HashMap

class ContractInfoAdapter(private val contractData: HashMap<String, String>) :
    RecyclerView.Adapter<ContractInfoAdapter.mViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contract, parent, false)
        return mViewHolder(view)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        var data: String? = ""
        var name = ""
        when (position) {
            0 -> {
                data += contractData["abi"]
                name = "ABI"
            }
            1 -> {
                data += contractData["code"]
                name = "Code"
            }
            2 -> {
                data += contractData["byteCode"]
                name = "ByteCode"
            }
        }
        holder.value.text = data
        holder.name.text = name
        holder.copyImg.setOnClickListener { v: View? ->
            copyUtils.copy(holder.value.text.toString(), holder.name.context)
            mToast.showCopy()
        }
    }

    override fun getItemCount(): Int {
        return contractData.size
    }

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var value: TextView = itemView.findViewById(R.id.contract)
        var name: TextView = itemView.findViewById(R.id.name)
        var copyImg: ImageView = itemView.findViewById(R.id.copy)

    }
}