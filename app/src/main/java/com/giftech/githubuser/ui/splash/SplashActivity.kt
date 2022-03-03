package com.giftech.githubuser.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.giftech.githubuser.R
import com.giftech.githubuser.databinding.ActivitySplashBinding
import com.giftech.githubuser.ui.home.HomeActivity
import com.giftech.githubuser.viewmodel.ViewModelFactory


class SplashActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this,factory)[SplashViewModel::class.java]

        setTheme(viewModel.getIsDarkTheme())

        supportActionBar?.hide()

        val delayTime = 3000L

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, delayTime)
    }

    private fun setTheme(isDark: Boolean) {
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            binding.logo.setImageResource(R.drawable.dark_logo)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            binding.logo.setImageResource(R.drawable.light_logo)
        }
    }
}