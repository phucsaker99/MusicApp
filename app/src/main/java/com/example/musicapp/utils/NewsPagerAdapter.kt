package com.example.musicapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class NewsPagerAdapter(fragmentManager: FragmentManager, vararg fragmentPair: Pair<Fragment, String>) :
    FragmentPagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    private var fragment = mutableListOf<Pair<Fragment, String>>()

    init {
        this.fragment = fragmentPair.toMutableList()
    }

    override fun getItem(position: Int): Fragment = fragment[position].first

    override fun getCount(): Int = fragment.size

    override fun getPageTitle(position: Int): CharSequence? = fragment[position].second
}
