package com.giftech.githubuser.adapter

import androidx.recyclerview.widget.DiffUtil
import com.giftech.githubuser.data.User

class UserDiffUtil(
    private val oldListUser:List<User>,
    private val newListUser:List<User>
):DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldListUser.size

    override fun getNewListSize(): Int = newListUser.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListUser[oldItemPosition].username == newListUser[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldListUser[oldItemPosition].name != newListUser[newItemPosition].name -> false
            oldListUser[oldItemPosition].username != newListUser[newItemPosition].username -> false
            oldListUser[oldItemPosition].avatar != newListUser[newItemPosition].avatar -> false
            oldListUser[oldItemPosition].company != newListUser[newItemPosition].company -> false
            oldListUser[oldItemPosition].location != newListUser[newItemPosition].location -> false
            oldListUser[oldItemPosition].repository != newListUser[newItemPosition].repository -> false
            oldListUser[oldItemPosition].following != newListUser[newItemPosition].following -> false
            oldListUser[oldItemPosition].followers != newListUser[newItemPosition].followers -> false
            else -> true
        }
    }
}