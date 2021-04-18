package com.example.apiroom.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.apiroom.R
import com.example.apiroom.data.local.UserDao
import com.example.apiroom.databinding.ActivityAddUserBinding
import com.example.apiroom.databinding.ActivityMainBinding
import com.example.apiroom.di.viewBinding
import com.example.apiroom.model.User

import kotlin.random.Random

class AddUserActivity : AppCompatActivity() {

    // Binding
    private val binding by viewBinding(ActivityAddUserBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvID.text = Random.nextInt(0, 1000).toString()

        binding.btnSave.setOnClickListener {
            val intent = Intent()
            if (TextUtils.isEmpty(binding.etName.text) || TextUtils.isEmpty(binding.etEmail.text) || TextUtils.isEmpty(binding.etUsername.text)) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val user = User(Integer.parseInt(binding.tvID.text.toString()), binding.etName.text.toString(), binding.etEmail.text.toString(), binding.etUsername.text.toString(), "", "")
                intent.putExtra(EXTRA_REPLY, user)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.apiroom.sql.REPLY"
    }
}