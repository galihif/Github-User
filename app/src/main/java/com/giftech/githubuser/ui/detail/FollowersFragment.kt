package com.giftech.githubuser.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.githubuser.data.User
import com.giftech.githubuser.databinding.FragmentFollowersBinding
import com.giftech.githubuser.ui.home.UserAdapter
import com.giftech.githubuser.viewmodel.ViewModelFactory

class FollowersFragment() : Fragment() {

    private lateinit var binding: FragmentFollowersBinding
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this,factory)[DetailViewModel::class.java]
        adapter = UserAdapter()

        val username = arguments?.getString(USERNAME)

        showLoading(true)
        viewModel.getUserFollowers(username!!).observe(viewLifecycleOwner, {
            adapter.setList(it)
            showListUser()
            showLoading(false)
        })
    }

    private fun showLoading(loading:Boolean){
        if(loading){
            binding.loading.visibility = View.VISIBLE
        } else{
            binding.loading.visibility = View.INVISIBLE
        }
    }

    private fun showListUser() {

        binding.rvFollowers.layoutManager = LinearLayoutManager(context)
        binding.rvFollowers.adapter = adapter

        adapter.setOnItemCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.USERNAME, data.username)
                startActivity(intent)
            }

        })
    }

    companion object{
        const val USERNAME = "USERNAME"
    }

}