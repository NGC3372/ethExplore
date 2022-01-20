package com.rainbowguo.ethexplore

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import com.github.sumimakito.awesomeqr.AwesomeQrRenderer
import com.github.sumimakito.awesomeqr.RenderResult
import com.github.sumimakito.awesomeqr.option.RenderOption
import com.github.sumimakito.awesomeqr.option.color.Color
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

class QRCodeActivity : AppCompatActivity() , QRCodeReaderView.OnQRCodeReadListener {
    private val TAG = "QRCodeActivity"
    private lateinit var qrCodeReaderView: QRCodeReaderView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)
        ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),2)
        qrCodeReaderView = findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
        //qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();


    }

    override fun onQRCodeRead(text: String?, points: Array<out PointF>?) {
        if (text != "No QR Code found"){
            val intent = Intent()
            intent.putExtra("result",text)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        qrCodeReaderView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        qrCodeReaderView.stopCamera()
    }



}