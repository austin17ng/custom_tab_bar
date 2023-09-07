package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private val vpAdapter = VPAdapter(this)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.vp)
        viewPager.adapter = vpAdapter

        val tabBar = findViewById<CustomTabBar>(R.id.tab_bar)
        tabBar.attachTo(viewPager)

    }
}