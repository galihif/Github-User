package com.giftech.githubuser.adapter

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
            0 -> fragment = FollowersFragment(username!!)
            1 -> fragment = FollowingFragment(username!!)
        }
        return fragment as Fragment
    }

}