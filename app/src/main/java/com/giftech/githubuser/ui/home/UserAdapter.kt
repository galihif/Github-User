package com.giftech.githubuser.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftech.githubuser.data.User
import com.giftech.githubuser.databinding.ItemUserBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var listUser = ArrayList<User>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setList(list:List<User>){
        listUser.clear()
        listUser.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(parent.context,binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = listUser.size

    inner class UserViewHolder(private val context: Context,private val binding: ItemUserBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(user:User){
            with(binding){
                tvName.text = user.name
                tvUsername.text = user.username

                Glide
                    .with(context)
                    .load(user.avatar)
                    .centerCrop()
                    .circleCrop()
                    .into(ivUser)

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(user)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    fun setOnItemCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
}