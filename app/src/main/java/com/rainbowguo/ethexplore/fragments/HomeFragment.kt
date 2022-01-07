package com.rainbowguo.ethexplore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rainbowguo.ethexplore.Utils.myAnimation
import com.rainbowguo.ethexplore.databinding.ActivityMainBinding
import com.rainbowguo.ethexplore.databinding.FragmentMainBinding
import com.rainbowguo.ethexplore.viewModels.homeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment:Fragment() {
    private lateinit var binding : FragmentMainBinding
    private lateinit var viewModel: homeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[homeViewModel::class.java]
        binding.refreshButton.setOnClickListener {
            myAnimation.rotationView(binding.refreshImg)
            viewModel.clearData()
            viewModel.requestData()
        }
        viewModel.clearData()
        viewModel.requestData()
        collectData()
    }


    private fun collectData(){
        lifecycleScope.launch(Dispatchers.Main) {
            launch {
                viewModel.priceOfEthToUSDT.collect{
                    binding.ethUsdt.text = it
                }
            }
            launch {
                viewModel.priceOfEthToBTC.collect{
                    binding.ethBtc.text = it
                }
            }
            launch {
                viewModel.NumberOfTotalEth.collect{
                    binding.ethNumber.text = it
                }
            }
            launch {
                viewModel.NumberOfEth2Staking.collect{
                    binding.eth2staking.text = it
                }
            }
            launch {
                viewModel.NumberOfBurntFees.collect{
                    binding.BurntFees.text = it
                }
            }
            launch {
                viewModel.ChainSize.collect{
                    binding.chainSize.text = it
                }
            }
            launch {
                viewModel.NumberOfTotalBlock.collect{
                    binding.blockNumber.text = it
                }
            }
            launch {
                viewModel.NumberOfTotalNode.collect{
                    binding.nodesNumber.text = it
                }
            }
            launch {
                viewModel.requestTime.collect{
                    binding.requestTime.text = it
                }
            }

        }


    }
}