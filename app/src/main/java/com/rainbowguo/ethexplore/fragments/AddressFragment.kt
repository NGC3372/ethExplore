package com.rainbowguo.ethexplore.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rainbowguo.ethexplore.R
import com.rainbowguo.ethexplore.Utils.mToast
import com.rainbowguo.ethexplore.Utils.TextUtils
import com.rainbowguo.ethexplore.Utils.myAnimation
import com.rainbowguo.ethexplore.adapter.ContractInfoAdapter
import com.rainbowguo.ethexplore.adapter.InternalTransactionAdapter
import com.rainbowguo.ethexplore.adapter.TransactionAdapter
import com.rainbowguo.ethexplore.adapter.viewpageAdapter
import com.rainbowguo.ethexplore.beans.internalTransactionsBean
import com.rainbowguo.ethexplore.beans.transactionsBean
import com.rainbowguo.ethexplore.databinding.FragmentAddressBinding
import com.rainbowguo.ethexplore.https.HttpUtils
import com.rainbowguo.ethexplore.viewModels.addressFragmentMode
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddressFragment : Fragment() {
    private lateinit var bind: FragmentAddressBinding
    private lateinit var viewMode: addressFragmentMode
    private lateinit var address: String
    private lateinit var adapter: viewpageAdapter

    companion object {
        private const val TAG = "addressFragment"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentAddressBinding.inflate(inflater)
        address = arguments?.getString("address").toString()
        bind.viewPager.offscreenPageLimit = 1
        viewMode = ViewModelProvider(this,object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return addressFragmentMode(address) as T
            }
        })[addressFragmentMode::class.java]
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewMode.requestAddressBytecode()
        viewMode.getBalance()
        observeData()
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private fun observeData(){
        val transactionAdapter = TransactionAdapter(viewMode.transactionList, address)
        val internalTransactionAdapter = InternalTransactionAdapter(viewMode.internalList, address)
        val contractInfoAdapter = ContractInfoAdapter(viewMode.contractData)

        lifecycleScope.launch{
            launch {
                viewMode.requestState.collect{
                    if (it.state == false)
                        mToast.showToastRequestFail()
                }
            }
            launch {
                viewMode.addressBalance.collect{
                    bind.balance.text = "Balance:$it"
                }
            }
            launch {
                viewMode.contractDataState.collect{
                    if (it == "ok"){
                        Log.i(TAG, "observeData: get contract data")
                        contractInfoAdapter.notifyDataSetChanged()
                    }
                }
            }
            launch {
                viewMode.TransactionsDataState.collect{
                    when (it.state) {
                        "null" -> {
                            adapter.transactionsPage.value = -1
                            adapter.setTransactionsPageProgressBarGone()
                        }
                        "to_end" -> {
                            adapter.transactionsPage.value = -1
                            adapter.setTransactionsPageProgressBarGone()
                            transactionAdapter.notifyDataSetChanged()
                        }
                        "ok" -> {
                            Log.i(TAG, "observeData: get transaction data")

                            transactionAdapter.notifyDataSetChanged()

                        }
                    }
                }
            }
            launch {
                viewMode.internalTransactionsDataState.collect{
                    when (it.state) {
                        "null" -> {
                            adapter.internalTransactionsPage.value = -1
                            adapter.setInternalTransactionsPageProgressBarGone()
                        }
                        "to_end" -> {
                            adapter.setInternalTransactionsPageProgressBarGone()
                            adapter.internalTransactionsPage.value = -1
                            internalTransactionAdapter.notifyDataSetChanged()
                        }
                        "ok" -> {
                            internalTransactionAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }

            launch {
                viewMode.addressType.collect{
                    if (it == "Address") {
                        bind.roleImg.setImageResource(R.drawable.user)
                        adapter = viewpageAdapter(transactionAdapter, internalTransactionAdapter)
                        bind.viewPager.adapter = adapter
                        TabLayoutMediator(
                            bind.tabLayout,
                            bind.viewPager
                        ) { tab: TabLayout.Tab, position: Int ->
                            when (position) {
                                0 -> tab.text = "Transactions"
                                1 -> tab.text = "Internal Txns"
                            }
                        }.attach()
                    } else if (it == "Contract") {
                        bind.roleImg.setImageResource(R.drawable.contract)
                        adapter = viewpageAdapter(
                            transactionAdapter,
                            internalTransactionAdapter,
                            contractInfoAdapter
                        )
                        bind.viewPager.adapter = adapter
                        TabLayoutMediator(
                            bind.tabLayout,
                            bind.viewPager
                        ) { tab: TabLayout.Tab, position: Int ->
                            when (position) {
                                0 -> tab.text = "Transactions"
                                1 -> tab.text = "Internal Txns"
                                2 -> tab.text = "Contract"
                            }
                        }.attach()
                        viewMode.getContractData()
                    }
                    if (it != ""){
                        bind.content.visibility = View.VISIBLE
                        bind.progressBar.visibility = View.GONE
                        myAnimation.SmallToBig(bind.content)
                        bind.role.text = it
                        bind.address.text = address
                        adapter.transactionsPage.observe(viewLifecycleOwner, { integer: Int ->
                            if (integer != -1) {
                                viewMode.getTransactionsData(integer.toString())
                                Log.i(TAG, "observeData: transaction data ++ $integer")
                            }
                               
                            else if (viewMode.transactionList.size == 0) {
                                Log.i(TAG, "onViewCreated: transaction 000000")
                                adapter.setTransactionsNullContent()
                            }

                        }
                        )
                        adapter.internalTransactionsPage.observe(viewLifecycleOwner, { integer: Int ->
                            if (integer != -1) viewMode.getInternalTransactionsData(integer.toString(),)
                            else if (viewMode.internalList.size == 0) {
                                Log.i(TAG, "onViewCreated: internaltransaction 000000")
                                adapter.setInternalTransactionsNullContent()
                            }
                        })
                    }

                }
            }
        }
    }



}