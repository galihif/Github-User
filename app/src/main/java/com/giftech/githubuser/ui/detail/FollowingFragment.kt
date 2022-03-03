package com.giftech.githubuser.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.githubuser.adapter.UserAdapter
import com.giftech.githubuser.data.User
import com.giftech.githubuser.databinding.FragmentFollowingBinding
import com.giftech.githubuser.viewmodel.ViewModelFactory

class FollowingFragment : Fragment() {

    private lateinit var binding: FragmentFollowingBinding
    private lateinit var adapter: UserAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModel = ViewModelProvider(this,factory)[DetailViewModel::class.java]
        adapter = UserAdapter()

        val username = arguments?.getString(FollowersFragment.USERNAME)

        showLoading(true)
        viewModel.getUserFollowing(username!!).observe(viewLifecycleOwner, {
            adapter.setList(it)
            showListUser()
            showLoading(false)
            showEmpty(it.isEmpty())
        })
    }

    @SuppressLint("SetTextI18n")
    private fun showEmpty(listEmpty: Boolean) {
        if(listEmpty){
            binding.empty.root.visibility = View.VISIBLE
            binding.empty.emptyDesc.text = "This User Doesn't Follow Anyone"
        }else{
            binding.empty.root.visibility = View.INVISIBLE
        }
    }

    private fun showLoading(loading:Boolean){
        if(loading){
            binding.loading.visibility = View.VISIBLE
        } else{
            binding.loading.visibility = View.INVISIBLE
        }
    }

    private fun showListUser() {

        binding.rvFollowing.layoutManager = LinearLayoutManager(context)
        binding.rvFollowing.adapter = adapter

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