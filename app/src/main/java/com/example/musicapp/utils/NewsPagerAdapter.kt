package com.example.musicapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class NewsPagerAdapter(fragmentManager: FragmentManager, vararg fragment: Fragment) :
    FragmentPagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    private var fragment = mutableListOf<Fragment>()

    init {
        this.fragment = fragment.toMutableList()
    }

    override fun getItem(position: Int): Fragment = fragment[position]

    override fun getCount(): Int = fragment.size

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Songs"
            1 -> return "Artists"
        }
        return super.getPageTitle(position)
    }
}
