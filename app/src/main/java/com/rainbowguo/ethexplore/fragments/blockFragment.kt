package com.rainbowguo.ethexplore.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rainbowguo.ethexplore.NewMainActivity
import com.rainbowguo.ethexplore.R
import com.rainbowguo.ethexplore.Utils.myAnimation
import com.rainbowguo.ethexplore.adapter.BlockInfoAdapter
import com.rainbowguo.ethexplore.databinding.FragmentBlockBinding
import com.rainbowguo.ethexplore.viewModels.blockFragmentMode
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
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewMode = ViewModelProvider(this).get<blockFragmentMode>(blockFragmentMode::class.java)
        val blockNumber = requireArguments().getString("blockNumber")
        viewMode.blockNumber
            .observe(viewLifecycleOwner, Observer { s: String? -> binding!!.blockNumber.text = s })
        viewMode.date.observe(viewLifecycleOwner, Observer { s: String? -> binding!!.date.text = s })
        viewMode.miner.observe(viewLifecycleOwner, Observer { s: String? -> binding!!.miner.text = s })
        viewMode.transactionsSize.observe(viewLifecycleOwner, Observer { integer: Int ->
            if (integer == 0) {
                binding!!.recyclerView.visibility = View.GONE
                binding!!.canNotFind.visibility = View.VISIBLE
            } else Objects.requireNonNull(binding!!.recyclerView.adapter).notifyDataSetChanged()
        })
        viewMode.requestState.observe(viewLifecycleOwner, Observer { aBoolean: Boolean ->
            if (aBoolean) {
                binding!!.ProgressView.visibility = View.GONE
                myAnimation.SmallToBig(binding!!.content)
                binding!!.content.visibility = View.VISIBLE
            } else {
                binding!!.ProgressView.visibility = View.GONE
                binding!!.content.visibility = View.GONE
                binding!!.failedContent.visibility = View.VISIBLE
            }
        })
        binding!!.recyclerView.layoutManager = LinearLayoutManager(context)
        binding!!.recyclerView.adapter = BlockInfoAdapter(viewMode.getTransactionsList())
        if (blockNumber != null) {
            viewMode.requestBlockData(blockNumber)
        }
        binding!!.miner.setTextColor(R.color.textLink)
        binding!!.miner.setOnClickListener { v ->
            val text = binding!!.miner.text.toString()
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
}