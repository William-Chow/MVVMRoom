package com.example.apiroom.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiroom.R
import com.example.apiroom.base.AppApplication
import com.example.apiroom.databinding.ActivityMainBinding
import com.example.apiroom.di.viewBinding
import com.example.apiroom.model.User
import com.example.apiroom.ui.AddUserActivity.Companion.EXTRA_REPLY
import com.example.apiroom.ui.adapter.UserAdapter
import com.example.apiroom.viewmodel.MainViewModel
import com.example.apiroom.viewmodel.MainViewModelFactory
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private val mainRequestCode = 1
    private val mainViewModel: MainViewModel by viewModels { MainViewModelFactory((application as AppApplication).repository) }
    // Binding
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = UserAdapter(ArrayList())
        binding.rvUser.adapter = adapter
        binding.rvUser.layoutManager = LinearLayoutManager(this)

        mainViewModel.datas.observe(this) { users ->
            // Update the cached copy of the words in the adapter.
            users.let {
                adapter.submitList(users?.toMutableList())
            }
        }

        // val fab = findViewById<FloatingActionButton>(R.id.fab)
        binding.fab.setOnClickListener {
            val user = User(Random.nextInt(0, 1000), "", "", "", "", "")
                mainViewModel.insert(user)
//            val intent = Intent(this@MainActivity, AddUserActivity::class.java)
//            startActivityForResult(intent, mainRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == mainRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(EXTRA_REPLY)?.let { reply ->
//                val user = User(reply)
//                mainViewModel.insert(user)
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }


//    class FirstFragment: Fragment(R.layout.first_fragment) {
//        private val binding by viewBinding(FirstFragmentBinding::bind)
//
//        override fun onViewCreated(view: View, bundle: Bundle?) {
//            super.onViewCreated(view, bundle)
//
//            binding.buttonPressMe.onClick {
//                showToast("Hello binding!")
//            }
//        }
}