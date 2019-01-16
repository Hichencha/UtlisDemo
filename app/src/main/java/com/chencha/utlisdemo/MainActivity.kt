package com.chencha.utlisdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.chencha.utlisdemo.hencode.DrawCircleView
import com.chencha.utlisdemo.hencode.DrawColorViewKt
import com.chencha.utlisdemo.hencode.DrawPointView
import com.chencha.utlisdemo.hencode.DrawRectView
import com.chencha.utlisdemo.img.ImageFlexboxActivity
import com.chencha.utlisdemo.receipt.PrintReceiptActivity
import com.chencha.utlisdemo.seekbar.ProgressBarActviity
import com.chencha.utlisdemo.seekbar.SeekbarActivity
import com.chencha.utlisdemo.selectimg.ImageSelectActivity
import com.chencha.utlisdemo.tagview.TagViewActivity
import com.chencha.utlisdemo.text.CountTimeActivity
import com.chencha.utlisdemo.vr.VrActivity
import com.chencha.utlisdemo.vr.VrWebViewActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var imgInfo: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressbar.setOnClickListener(this)
        seekbar.setOnClickListener(this)
        img.setOnClickListener(this)
        imgselect.setOnClickListener(this)
        tag.setOnClickListener(this)
        vr.setOnClickListener(this)
        vr_webview.setOnClickListener(this)
        time.setOnClickListener(this)
        outticket.setOnClickListener(this)
        drawcolor.setOnClickListener(this)
        drawcircle.setOnClickListener(this)
        drawrect.setOnClickListener(this)
        drawpoint.setOnClickListener(this)
        imgInfo.add("http://oss.rhrsh.com/manager/test/1.png")
        imgInfo.add("http://oss.rhrsh.com/manager/test/2.png")
        imgInfo.add("http://oss.rhrsh.com/manager/test/3.png")
        imgInfo.add("http://oss.rhrsh.com/manager/test/4.png")
        imgInfo.add("http://oss.rhrsh.com/manager/test/5.png")
        imgInfo.add("http://oss.rhrsh.com/manager/test/6.png")
        imgInfo.add("http://oss.rhrsh.com/manager/test/7.png")

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.progressbar -> {
                val intent = Intent(this, ProgressBarActviity::class.java)
                startActivity(intent)
            }
            R.id.seekbar -> {
                val intent = Intent(this, SeekbarActivity::class.java)
                startActivity(intent)
            }
            R.id.img -> {
                val bundle = Bundle()
                val intent = Intent(this, ImageFlexboxActivity::class.java)
                bundle.putSerializable("IMG", imgInfo)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            R.id.imgselect -> {
                val intent = Intent(this, ImageSelectActivity::class.java)
                startActivity(intent)
            }
            R.id.tag -> {
                val intent = Intent(this, TagViewActivity::class.java)
                startActivity(intent)
            }
            R.id.vr -> {
                val intent = Intent(this, VrActivity::class.java)
                startActivity(intent)
            }
            R.id.vr_webview -> {
                val intent = Intent(this, VrWebViewActivity::class.java)
                startActivity(intent)
            }
            R.id.time -> {
                val intent = Intent(this, CountTimeActivity::class.java)
                startActivity(intent)
            }
            R.id.outticket -> {
                val intent = Intent(this, PrintReceiptActivity::class.java)
                startActivity(intent)
            }
            R.id.drawcolor -> {
                val intent = Intent(this, DrawColorViewKt::class.java)
                startActivity(intent)
            }
            R.id.drawcircle -> {
                val intent = Intent(this, DrawCircleView::class.java)
                startActivity(intent)
            }
            R.id.drawrect -> {
                val intent = Intent(this, DrawRectView::class.java)
                startActivity(intent)
            }
            R.id.drawpoint ->{
                val intent = Intent(this, DrawPointView::class.java)
                startActivity(intent)
            }


        }
    }


}









