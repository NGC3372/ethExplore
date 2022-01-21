package com.rainbowguo.ethexplore.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.github.sumimakito.awesomeqr.AwesomeQrRenderer
import com.github.sumimakito.awesomeqr.RenderResult
import com.github.sumimakito.awesomeqr.option.RenderOption
import com.github.sumimakito.awesomeqr.option.color.Color
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.rainbowguo.ethexplore.R

class QRCodeFragment: DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("on create ")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        println("on create dialog")
        val builder = AlertDialog.Builder(requireActivity())
        val view: View = requireActivity().layoutInflater.inflate(R.layout.fragment_qrcode, null)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        setQRCode(imageView)
        builder.setView(view)
        return builder.create()
    }

    private fun setQRCode(imageView: ImageView){
        val renderOption = RenderOption()
        val content = arguments?.getString("value")
        if (content != null) {
            renderOption.content = content
        } // content to encode
        println(renderOption.content)
        renderOption.size = 800 // size of the final QR code image
        //renderOption.borderWidth = 20 // width of the empty space around the QR code
        renderOption.ecl = ErrorCorrectionLevel.M // (optional) specify an error correction level
        renderOption.patternScale = 0.35f // (optional) specify a scale for patterns
        renderOption.roundedPatterns = true // (optional) if true, blocks will be drawn as dots instead
        renderOption.clearBorder = true // if set to true, the background will NOT be drawn on the border area
        renderOption.color = Color() // set a color palette for the QR code
        //renderOption.background = background // set a background, keep reading to find more about it
        //renderOption.logo = logo // set a logo, keep reading to find more about it
        try {
            val result = AwesomeQrRenderer.render(renderOption)
            when {
                result.bitmap != null -> {
                    // play with the bitmap
                    imageView.setImageBitmap(result.bitmap)
                }
                result.type == RenderResult.OutputType.GIF -> {
                    // If your Background is a GifBackground, the image
                    // will be saved to the output file set in GifBackground
                    // instead of being returned here. As a result, the
                    // result.bitmap will be null.
                }
                else -> {
                    // Oops, something gone wrong.

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Oops, something gone wrong.
        }
    }
}