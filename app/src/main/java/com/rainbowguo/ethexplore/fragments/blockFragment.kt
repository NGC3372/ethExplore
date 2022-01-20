package com.rainbowguo.ethexplore.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rainbowguo.ethexplore.NewMainActivity
import com.rainbowguo.ethexplore.R
import com.rainbowguo.ethexplore.Utils.myAnimation
import com.rainbowguo.ethexplore.adapter.BlockInfoAdapter
import com.rainbowguo.ethexplore.databinding.FragmentBlockBinding
import com.rainbowguo.ethexplore.viewModels.blockFragmentMode
import kotlinx.coroutines.launch
import java.util.*

class blockFragment : Fragment() {
    private lateinit var binding: FragmentBlockBinding
    private lateinit var viewMode: blockFragmentMode
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlockBinding.inflate(inflater)
        viewMode = ViewModelProvider(this)[blockFragmentMode::class.java]
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()

        val blockNumber = requireArguments().getString("blockNumber")
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = BlockInfoAdapter(viewMode.getTransactionsList())
        binding.QRCodeButton.setOnClickListener {
            val address = binding.miner.text.toString()
            if (address != ""){
                println("not null")
                val activity = activity as NewMainActivity
                val fragment = QRCodeFragment()
                val bundle = Bundle()
                bundle.putString("value",address)
                fragment.arguments = bundle
                activity.addFragment(fragment)
            }
        }
        blockNumber?.let { viewMode.requestBlockData(blockNumber) }
        binding.miner.setTextColor(R.color.textLink)
        binding.miner.setOnClickListener {
            val text = binding.miner.text.toString()
            if (text != "") {
                val activity = activity as NewMainActivity
                val fragment = AddressFragment()
                val bundle = Bundle()
                bundle.putString("address", text)
                fragment.arguments = bundle
                activity.addFragment(fragment)
            }
        }
    }



    @SuppressLint("NotifyDataSetChanged")
    private fun observeData(){
        lifecycleScope.launch {
            launch {
                viewMode.requestState.collect{
                    if (it.state == false){
                        binding.ProgressView.visibility = View.GONE
                        binding.content.visibility = View.GONE
                        binding.failedContent.visibility = View.VISIBLE
                    }else if (it.state == true){
                        binding.ProgressView.visibility = View.GONE
                        myAnimation.SmallToBig(binding.content)
                        binding.content.visibility = View.VISIBLE

                        val dataLength = viewMode.getTransactionsList().size
                        if (dataLength == 0) {
                            binding.recyclerView.visibility = View.GONE
                            binding.canNotFind.visibility = View.VISIBLE
                        } else binding.recyclerView.adapter?.notifyDataSetChanged()

                    }
                }
            }

            launch {
                viewMode.blockNumber.collect{
                    binding.blockNumber.text = it
                }
            }
            launch {
                viewMode.date.collect{
                    binding.date.text = it
                }
            }
            launch {
                viewMode.miner.collect{
                    binding.miner.text = it
                }
            }

        }

    }
}