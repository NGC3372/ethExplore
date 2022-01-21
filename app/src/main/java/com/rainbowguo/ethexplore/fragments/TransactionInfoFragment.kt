package com.rainbowguo.ethexplore.fragments


import com.rainbowguo.ethexplore.viewModels.transactionInfoMode
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rainbowguo.ethexplore.Utils.myAnimation
import com.rainbowguo.ethexplore.R
import com.rainbowguo.ethexplore.NewMainActivity
import com.rainbowguo.ethexplore.Utils.QRCodeHelper
import com.rainbowguo.ethexplore.Utils.TextUtils

import com.rainbowguo.ethexplore.beans.transactionsBean
import com.rainbowguo.ethexplore.adapter.TransactionsInfoAdapter
import com.rainbowguo.ethexplore.beans.internalTransactionsBean
import com.rainbowguo.ethexplore.adapter.InternalTransactionInfoAdapter
import com.rainbowguo.ethexplore.adapter.proxy_transactionsInfoAdapter
import com.rainbowguo.ethexplore.databinding.FragmentTransactionsBinding
import kotlinx.coroutines.launch


class TransactionInfoFragment : Fragment() {
    private lateinit var binding: FragmentTransactionsBinding
    private lateinit var viewMode: transactionInfoMode
    private lateinit var data: Any

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionsBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        data = arguments?.getSerializable("data") ?: 0
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        viewMode = ViewModelProvider(this)[transactionInfoMode::class.java]

        initData()
        observeData()

    }

    @SuppressLint("ResourceAsColor")
    private fun observeData() {
        lifecycleScope.launch{
            launch {
                viewMode.requestState.collect{
                    if(it.state == true){
                        binding.ProgressView.visibility = View.GONE
                        binding.scrollView.visibility = View.VISIBLE
                        myAnimation.SmallToBig(binding.scrollView)
                    }else if (it.state == false){
                        binding.scrollView.visibility = View.GONE
                        binding.ProgressView.visibility = View.GONE
                        binding.failedContent.visibility = View.VISIBLE
                    }
                }
            }
            launch {
                viewMode.fromAddress.collect{
                    myAnimation.SmallToBig(binding.scrollView)
                    binding.From.setTextColor(R.color.textLink)
                    binding.From.text = it
                    binding.From.setOnClickListener {
                        val activity = activity as NewMainActivity
                        val fragment = AddressFragment()
                        val bundle = Bundle()
                        bundle.putString("address", binding.From.text.toString())
                        fragment.arguments = bundle
                        activity.addFragment(fragment)
                    }
                    binding.QRCodeButtonFrom.setOnClickListener {
                        val addressFrom = binding.From.text.toString()
                        QRCodeHelper.createQRCode(addressFrom).show(parentFragmentManager,null)
                    }
                }
            }
            launch {
                viewMode.toAddress.collect{
                    binding.To.text = it
                    binding.To.setTextColor(R.color.textLink)
                    binding.To.setOnClickListener {
                        val activity = activity as NewMainActivity
                        val fragment = AddressFragment()
                        val bundle = Bundle()
                        bundle.putString("address", binding.To.text.toString())
                        fragment.arguments = bundle
                        activity.addFragment(fragment)
                    }
                    binding.QRCodeButtonTo.setOnClickListener {
                        val addressTo = binding.To.text.toString()
                        QRCodeHelper.createQRCode(addressTo).show(parentFragmentManager,null)
                    }
                }
            }
            launch {
                viewMode.eth_value.collect{
                    binding.Value.text = it
                }
            }
            launch {
                viewMode.dataBean.collect{
                    if (it.result != null)
                        binding.recyclerView.adapter = proxy_transactionsInfoAdapter(it.result)
                }
            }

        }

    }

    private fun initData() {
        when (data) {
            is transactionsBean.ResultDTO -> {
                viewMode.requestState.value = transactionInfoMode.STATE(true)
                val bean = data as transactionsBean.ResultDTO
                binding.recyclerView.adapter = TransactionsInfoAdapter(bean)
                viewMode.fromAddress.value = bean.from
                viewMode.toAddress.value = bean.to
                val value = TextUtils.formatEther(bean.value, null)
                viewMode.eth_value.value = value
            }
            is internalTransactionsBean.ResultDTO -> {
                viewMode.requestState.value = transactionInfoMode.STATE(true)
                val bean = data as internalTransactionsBean.ResultDTO
                binding.recyclerView.adapter = InternalTransactionInfoAdapter(bean)
                viewMode.fromAddress.value = bean.from
                viewMode.toAddress.value = bean.to
                val value = TextUtils.formatEther(bean.value, null)
                viewMode.eth_value.value = value
            }
            is String -> {
                viewMode.requestProsy_TransactionsInfo(data as String)
            }
        }
    }
}