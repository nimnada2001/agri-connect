package com.agri.connect.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agri.connect.databinding.ActivityLoginBinding
import com.agri.connect.ui.MainActivity
import com.agri.connect.data.local.AppDatabase
import com.agri.connect.data.local.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pwd = binding.etPassword.text.toString().trim()
            // Simple local registration/login using Room for demo purposes
            CoroutineScope(Dispatchers.IO).launch {
                val db = AppDatabase.getInstance(applicationContext)
                var user = db.userDao().getByEmail(email)
                if (user == null) {
                    user = User(0, email, pwd)
                    db.userDao().insert(user)
                }
                // go to main
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}
