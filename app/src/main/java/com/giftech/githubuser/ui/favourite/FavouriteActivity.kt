package com.giftech.githubuser.ui.favourite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.githubuser.adapter.UserAdapter
import com.giftech.githubuser.data.User
import com.giftech.githubuser.databinding.ActivityFavouriteBinding
import com.giftech.githubuser.ui.detail.DetailActivity
import com.giftech.githubuser.viewmodel.ViewModelFactory

class FavouriteActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFavouriteBinding
    private lateinit var viewModel: FavouriteViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favourite Users"

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[FavouriteViewModel::class.java]
        adapter = UserAdapter()

        viewModel.getAllFavUser().observe(this){
            adapter.setList(it)
            showListUser()
        }
    }

    private fun showListUser() {
        with(binding.rvFavourite){
            layoutManager = LinearLayoutManager(context)
            adapter = this@FavouriteActivity.adapter
        }

        adapter.setOnItemCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                val intent = Intent(this@FavouriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.USERNAME, data.username)
                startActivity(intent)
            }
        })
    }
}