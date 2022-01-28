package com.example.enot.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.enot.R
import com.example.enot.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)

        viewPager.adapter = ViewPagerAdapter(activity as AppCompatActivity)
        TabLayoutMediator(tabLayout, viewPager) {tabLayout, pos ->

            when (pos) {
                0 -> {
                    tabLayout.setIcon(R.drawable.icons8_shirt_64__1_)
                }
                1 -> {
                    tabLayout.setIcon(R.drawable.icons8_dress_64)
                }
                2 -> {
                    tabLayout.setIcon(R.drawable.icons8_shirt_64)
                }
            }

        }.attach()

    }

}