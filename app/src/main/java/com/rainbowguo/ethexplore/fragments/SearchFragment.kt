package com.rainbowguo.ethexplore.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rainbowguo.ethexplore.R
import com.rainbowguo.ethexplore.Utils.TextUtils
import com.rainbowguo.ethexplore.beans.proxy_transactionsInfoBean

class SearchFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        val view: View = requireActivity().layoutInflater.inflate(R.layout.dialog_search, null)
        val editText: EditText = view.findViewById(R.id.editText)
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager

        builder.setView(view).setPositiveButton("Search") { _, _ ->
            val query: String = editText.text.toString()
            when {
                query.length == 42 -> {
                    val fragment = AddressFragment()
                    val bundle = Bundle()
                    bundle.putString("address", query)
                    fragment.arguments = bundle
                    fragmentManager.beginTransaction()
                        .add(R.id.fragmentBox, fragment).addToBackStack(null).commit()
                }
                query.length == 66 -> {
                    val bean: proxy_transactionsInfoBean.ResultDTO =
                        proxy_transactionsInfoBean.ResultDTO()
                    bean.hash = query
                    val fragment = TransactionInfoFragment()
                    val bundle = Bundle()
                    bundle.putString("data", query)
                    fragment.arguments = bundle
                    fragmentManager.beginTransaction()
                        .add(R.id.fragmentBox, fragment).addToBackStack(null).commit()
                    //startActivity(new Intent(MainActivity.this, TransactionInfoActivity.class).putExtra("data",query));
                }
                TextUtils.isNumber(query) -> {
                    val fragment = blockFragment()
                    val bundle = Bundle()
                    bundle.putString("blockNumber", query)
                    fragment.arguments = bundle
                    fragmentManager.beginTransaction()
                        .add(R.id.fragmentBox, fragment).addToBackStack(null).commit()
                }
            }
        }
        return builder.create()
    }
}