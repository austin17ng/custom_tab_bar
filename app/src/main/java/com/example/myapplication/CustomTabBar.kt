package com.example.myapplication

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.LayoutTabBarBinding

class CustomTabBar(context: Context?, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {
    private var binding: LayoutTabBarBinding
    private lateinit var listTabName: List<String>
    private lateinit var listTabTv: List<TextView>

    init {
        binding = LayoutTabBarBinding.inflate(LayoutInflater.from(context), this, true)
        setupAttrs(attrs)
        setupUI()
    }

    private fun setupAttrs(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.CustomTabBar,
            0, 0
        )

        listTabName =
            typedArray.getTextArray(R.styleable.CustomTabBar_android_entries).toList().map {
                it.toString()
            }

        typedArray.recycle()
    }

    private fun setupUI() {
        //textview
        listTabTv = listTabName.mapIndexed { index, tabName ->
            initTabTv(tabName, index)
        }

        //view_tabs_wrapper
        binding.viewTabsWrapper.apply {
            weightSum = listTabTv.size.toFloat()
            listTabTv.forEach {
                this.addView(it)
            }
        }

        //view_indicator_wrapper
        binding.viewIndicatorWrapper.apply {
            weightSum = listTabTv.size.toFloat()
        }
    }

    private fun initTabTv(tabName: String, index: Int) = TextView(context).apply {
        text = tabName
        layoutParams = LinearLayout.LayoutParams(
            0,
            LayoutParams.MATCH_PARENT,
            1f
        )
        gravity = Gravity.CENTER
        setTextColor(ContextCompat.getColor(this.context, R.color.white))
        textSize = 12f
        setOnClickListener {
            onTabSelected(index)
        }
    }


    private fun onTabSelected(index: Int) {
        ObjectAnimator.ofFloat(
            binding.viewIndicator,
            View.TRANSLATION_X,
            binding.viewIndicator.x,
            listTabTv[index].x
        ).apply {
            duration = 300
            start()
        }
    }
}