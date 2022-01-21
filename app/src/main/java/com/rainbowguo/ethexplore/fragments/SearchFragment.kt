package com.rainbowguo.ethexplore.fragments

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rainbowguo.ethexplore.QRCodeActivity
import com.rainbowguo.ethexplore.R
import com.rainbowguo.ethexplore.Utils.TextUtils
import com.rainbowguo.ethexplore.Utils.mToast
import com.rainbowguo.ethexplore.beans.proxy_transactionsInfoBean

class SearchFragment: DialogFragment() {
    private lateinit var registerResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ it1 ->
            if (it1.data != null && it1.resultCode == Activity.RESULT_OK)
                it1.data?.getStringExtra("result")?.let { //it1 -> search(it1)
                    //println(it)
                    search(it)

                    activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
                }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val view: View = requireActivity().layoutInflater.inflate(R.layout.dialog_search, null)
        val editText: EditText = view.findViewById(R.id.editText)
        val QRbutton: ImageView =view.findViewById(R.id.QRCodeButton)


        QRbutton.setOnClickListener {
            registerResult.launch(Intent(activity,QRCodeActivity::class.java))}

        builder.setView(view).setPositiveButton("Search") { _, _ ->
            val query: String = editText.text.toString()
            search(query)
        }
        return builder.create()
    }


    private fun search(query:String){
        val fragmentManager = requireActivity().supportFragmentManager
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
            else -> mToast.showWrongContent()
        }
    }
}