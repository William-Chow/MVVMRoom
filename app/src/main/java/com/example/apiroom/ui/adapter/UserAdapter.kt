package com.example.apiroom.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.apiroom.databinding.AdapterUserItemBinding
import com.example.apiroom.model.User

class UserAdapter(private var userList: ArrayList<User>) : ListAdapter<User, UserAdapter.UserViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent.context, AdapterUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, position+1)
    }

    inner class UserViewHolder(private val context: Context, private val binding: AdapterUserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(user: User, position: Int) {
            with(binding){
                tvName.text = position.toString() + ". " + user.name
                tvEmail.text = user.email
                llItem.setOnClickListener {
                    Toast.makeText(context, "" + user.name, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun submitList(list: MutableList<User>?) {
        this.userList = list as ArrayList<User>
        super.submitList(ArrayList(list))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}