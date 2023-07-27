package com.example.gamestores.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gamestores.data.models.GameDetail
import com.example.gamestores.ui.view.fragment.TabDetailGame.AllFragment
import com.example.gamestores.ui.view.fragment.TabDetailGame.DiscussionFragment
import com.example.gamestores.ui.view.fragment.TabDetailGame.GamePlayFragment
import com.example.gamestores.ui.view.fragment.TabDetailGame.GuideFragment
import com.example.gamestores.ui.view.fragment.TabDetailGame.NewsFragment
import com.example.gamestores.ui.view.fragment.TabDetailGame.ReviewsFragment


class TabAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllFragment()
            1 -> ReviewsFragment()
            2 -> GamePlayFragment()
            3 -> NewsFragment()
            4 -> GuideFragment()
            5 -> DiscussionFragment()
            else -> {
                AllFragment()
            }
        }
    }
}