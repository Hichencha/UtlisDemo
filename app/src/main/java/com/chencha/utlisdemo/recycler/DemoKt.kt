package com.chencha.utlisdemo.recycler

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.chencha.utlisdemo.DeviceUtils
import com.chencha.utlisdemo.R
import kotlinx.android.synthetic.main.item_layout.*


class DemoKt : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_layout)


        doRepeatAnim(tvWater)
    }


    private fun doRepeatAnim(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationY", 0.2f, 2f)
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.duration = 1500
        animator.start()
    }


    fun startScale(view: View) {
        val rotateAnimator = ObjectAnimator.ofFloat(view, "translationY", 0.2f, 2f)
        rotateAnimator.duration = 2000
        rotateAnimator.start()
    }


    override fun onClick(v: View?) {

    }

}