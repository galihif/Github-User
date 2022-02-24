package com.giftech.githubuser.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.githubuser.R
import com.giftech.githubuser.data.User
import com.giftech.githubuser.databinding.ActivityHomeBinding
import com.giftech.githubuser.ui.detail.DetailActivity
import com.giftech.githubuser.viewmodel.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]
        adapter = UserAdapter()

        viewModel.getListUser().observe(this, { listUser ->
            adapter.setList(listUser)
            showListUser()
        })

    }

    private fun showListUser() {
        with(binding.rvUser){
            layoutManager = LinearLayoutManager(context)
            adapter = this@HomeActivity.adapter
        }

        adapter.setOnItemCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.USER_DATA, data)
                startActivity(intent)
            }
        })
    }

    private fun searchUser(keyword:String){
        viewModel.getSearchedUser(keyword).observe(this,{
            adapter.setList(it)
            showListUser()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Search User"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                searchUser(query)
                searchView.clearFocus()
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }
}