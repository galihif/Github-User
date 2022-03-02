package com.giftech.githubuser.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.giftech.githubuser.R
import com.giftech.githubuser.adapter.SectionsPagerAdapter
import com.giftech.githubuser.data.User
import com.giftech.githubuser.databinding.ActivityDetailBinding
import com.giftech.githubuser.utils.AppUtils
import com.giftech.githubuser.utils.AppUtils.loadCircleImage
import com.giftech.githubuser.viewmodel.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    private var isFavourited = false
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if(extras != null){
            val username = extras.getString(USERNAME,"")

            supportActionBar?.title = username

            viewModel.getDetailUser(username).observe(this, {
                populateView(it)
                user = it
                checkIsFavourited()

                binding.btnFav.setOnClickListener {
                    if(isFavourited){
                        viewModel.deleteFavUser(user.username)
                        isFavourited = false
                        setButtonFavourite()
                        AppUtils.showToast(this, "User Removed from Favourite")
                    } else{
                        viewModel.insertFavUser(user)
                        isFavourited = true
                        setButtonFavourite()
                        AppUtils.showToast(this, "User Saved to Favourite")
                    }
                }
            })

            viewModel.loading.observe(this){
                showLoading(it)
            }

            viewModel.error.observe(this){
                AppUtils.showToast(this, it)
            }

            showTabLayout(username)

        }
    }

    private fun checkIsFavourited() {
        viewModel.checkFavUserByUsername(user.username).observe(this){
            if(it.isNotEmpty()){
                isFavourited = true
                setButtonFavourite()
            } else{
                isFavourited = false
                setButtonFavourite()
            }
        }
    }

    private fun setButtonFavourite(){
        if(isFavourited){
            binding.btnFav.setImageResource(R.drawable.ic_favourited)
        }else{
            binding.btnFav.setImageResource(R.drawable.ic_favourite)
        }
    }

    private fun showTabLayout(username:String) {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = username

        with(binding.sectionFollowList){
            viewPager.adapter = sectionsPagerAdapter

            TabLayoutMediator(tabs, viewPager){tab, position ->
                tab.text = TAB_TITLES[position]
            }.attach()
        }
    }

    private fun populateView(user: User) {
        with(binding.sectionProfile){
            header.ivAvatar.loadCircleImage(this@DetailActivity,user.avatar)

            header.tvName.text = user.name
            header.tvUsername.text = user.username

            desc.tvCompany.text = user.company
            desc.tvLocation.text = user.location
            desc.tvRepository.text = getString(R.string.format_repository, user.repository)
            desc.tvFollow.text = getString(R.string.format_follow, user.followers, user.following)

        }
    }

    private fun showLoading(loading: Boolean) {
        if(loading){
            binding.loading.root.visibility = View.VISIBLE
            binding.sectionProfile.root.visibility = View.INVISIBLE
        } else {
            binding.loading.root.visibility = View.INVISIBLE
            binding.sectionProfile.root.visibility = View.VISIBLE
        }
    }

    companion object{
        const val USERNAME = "USERNAME"
        private val TAB_TITLES = arrayListOf(
            "FOLLOWERS",
            "FOLLOWING"
        )
    }
}