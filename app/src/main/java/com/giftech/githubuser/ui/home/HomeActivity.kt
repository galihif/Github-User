package com.giftech.githubuser.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.githubuser.data.User
import com.giftech.githubuser.databinding.ActivityHomeBinding
import com.giftech.githubuser.ui.DetailActivity
import com.giftech.githubuser.viewmodel.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        val viewmodel = ViewModelProvider(this,factory)[HomeViewModel::class.java]
        val adapter = UserAdapter()

        viewmodel.getListUser().observe(this, {listUser ->
            adapter.setList(listUser)
        })

        with(binding.rvUser){
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        adapter.setOnItemCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.USER_DATA, data)
                startActivity(intent)
            }
        })
    }
}