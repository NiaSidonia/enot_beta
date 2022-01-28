package com.example.enot.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.enot.viewPagerFragments.DressesFragment
import com.example.enot.viewPagerFragments.JacketsFragment
import com.example.enot.viewPagerFragments.ShirtsFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 3

    override fun createFragment(pos: Int): Fragment {
        return when (pos) {
            0 -> {
                JacketsFragment()
            }
            1 -> {
                DressesFragment()
            }
            else -> {
                ShirtsFragment()
            }

        }
    }
}