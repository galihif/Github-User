package com.giftech.githubuser.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giftech.githubuser.R
import com.giftech.githubuser.data.User
import com.giftech.githubuser.databinding.ActivityDetailBinding
import com.giftech.githubuser.utils.AppUtils.loadCircleImage

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if(extras != null){
            val user = extras.getParcelable<User>(USER_DATA)

            supportActionBar?.title = user?.username

            with(binding){
                header.ivAvatar.loadCircleImage(user?.avatar)

                header.ivName.text = user?.name
                header.ivUsername.text = user?.username

                desc.tvCompany.text = user?.company
                desc.tvLocation.text = user?.location
                desc.tvRepository.text = getString(R.string.format_repository, user?.repository)
                desc.tvFollow.text = getString(R.string.format_follow, user?.followers, user?.following)

            }
        }
    }

    companion object{
        const val USER_DATA = "USER_DATA"
    }
}