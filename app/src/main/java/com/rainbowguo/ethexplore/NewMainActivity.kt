package com.rainbowguo.ethexplore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rainbowguo.ethexplore.Utils.mToast
import com.rainbowguo.ethexplore.databinding.ActivityMainBinding
import com.rainbowguo.ethexplore.fragments.SearchFragment
import com.rainbowguo.ethexplore.fragments.HomeFragment

class NewMainActivity : AppCompatActivity() {
    private val TAG = "mainActivity"
    private lateinit var bind: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setSupportActionBar(bind.toolbar)
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.fragmentBox, HomeFragment()).commit()
        bind.searchButton.setOnClickListener {
            SearchFragment().show(fragmentManager, null)
        }
        mToast.create(this)
    }


    fun addFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .add(R.id.fragmentBox, fragment).addToBackStack(null).commit()
    }


    override fun onDestroy() {
        super.onDestroy()
        mToast.clear()
    }
}