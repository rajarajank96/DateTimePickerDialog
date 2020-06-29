package com.example.dialogs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.dialogs.tabs.DateFragment
import com.example.dialogs.tabs.TimeFragment

internal class PagerAdapter(fragmentManager: FragmentManager, private var noOfTabs: Int): FragmentStatePagerAdapter(fragmentManager, noOfTabs) {

    internal lateinit var params: Params

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                DateFragment(this.params)
            }
            1 -> {
                TimeFragment(this.params)
            }
            else -> {
                DateFragment(this.params)
            }
        }
    }

    override fun getCount(): Int {
        return noOfTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "DATE"
            1 -> "TIME"
            else -> null
        }
    }
}