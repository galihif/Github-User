package com.giftech.githubuser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.giftech.githubuser.data.User
import com.giftech.githubuser.databinding.ItemUserBinding
import com.giftech.githubuser.utils.AppUtils.loadCircleImage

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var oldListUser = emptyList<User>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setList(newList:List<User>){
        val diffUtil = UserDiffUtil(oldListUser, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldListUser = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(parent.context,binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = oldListUser[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = oldListUser.size

    inner class UserViewHolder(private val context: Context,private val binding: ItemUserBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(user:User){
            binding.tvUsernameItem.text = user.username

            binding.ivUser.loadCircleImage(context,user.avatar)

            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(user)
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