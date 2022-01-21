package com.rainbowguo.ethexplore.Utils

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.rainbowguo.ethexplore.fragments.QRCodeFragment

object QRCodeHelper {
    fun createQRCode(value :String):DialogFragment{
        val fragment = QRCodeFragment()
        val bundle = Bundle()
        bundle.putString("value",value)
        fragment.arguments = bundle
        return fragment
    }
}