package com.giftech.githubuser.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.giftech.githubuser.ui.detail.FollowersFragment
import com.giftech.githubuser.ui.detail.FollowingFragment

class SectionsPagerAdapter(activity: AppCompatActivity):FragmentStateAdapter(activity) {

    var username:String? = null

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment? = null
        when(position){
            0 -> {
                fragment = FollowersFragment()
                fragment.arguments = Bundle().apply {
                    putString(FollowersFragment.USERNAME, username)
                }
            }
            1 -> {
                fragment = FollowingFragment()
                fragment.arguments = Bundle().apply {
                    putString(FollowingFragment.USERNAME, username)
                }
            }
        }
        return fragment as Fragment
    }

}