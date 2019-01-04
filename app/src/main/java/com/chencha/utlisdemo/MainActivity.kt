package com.chencha.utlisdemo

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.chencha.utlisdemo.img.ImageFlexboxActivity
import com.chencha.utlisdemo.seekbar.ProgressBarActviity
import com.chencha.utlisdemo.seekbar.SeekbarActivity
import com.chencha.utlisdemo.selectimg.ImageSelectActivity
import com.chencha.utlisdemo.tagview.TagViewActivity
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
        }
    }


}









