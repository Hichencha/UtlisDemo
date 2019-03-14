package com.chencha.utlisdemo.recycler

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.chencha.utlisdemo.R
import kotlinx.android.synthetic.main.recycler_item.*
import java.util.*


class RecyclerViewKt : AppCompatActivity(), View.OnClickListener {
    var itemList: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_item)

        itemList.add("1")
        itemList.add("2")
        itemList.add("3")
        itemList.add("4")
        itemList.add("5")

        val layoutManage = GridLayoutManager(this, itemList.size)
        list!!.setLayoutManager(layoutManage)
        var myAdapter = MyAdapter(this, itemList)
        list.setAdapter(myAdapter)//设置适配器

    }

    override fun onClick(v: View?) {

    }


}

class MyAdapter(var thisActivity: Activity, var itemList: ArrayList<String>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var inflater: LayoutInflater? = LayoutInflater.from(thisActivity)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater!!.inflate(R.layout.item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val objectAnimator = ObjectAnimator.ofFloat(holder.tvWater, "translationY",0.6f, 2f)
        objectAnimator.setDuration(1500)
        objectAnimator.start()

        if (position == 0) {
            setMargins(holder.item, 0, 100, 0, 20)
        } else if (position == 1) {
            setMargins(holder.item, 0, 20, 0, 100)
        } else if (position == 2) {
            setMargins(holder.item, 0, 100, 0, 20)
        } else if (position == 3) {
            setMargins(holder.item, 0, 20, 0, 100)
        } else if (position == 4) {
            setMargins(holder.item, 0, 100, 0, 20)
        }


    }

    private fun setMargins(v: RelativeLayout, l: Int, t: Int, r: Int, b: Int) {
        if (v.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = v.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(l, t, r, b)
            v.requestLayout()
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var item: RelativeLayout = view.findViewById(R.id.item)
        var tvWater: TextView = view.findViewById(R.id.tvWater)
    }



}



