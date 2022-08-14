package com.example.lms

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivitySignupEmailBinding


class SignUpEmailActivity: AppCompatActivity() {
    lateinit var binding: ActivitySignupEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.okBtn.setOnClickListener {
            val email: String = binding.signupEmailEt.getText().toString()
            val intent = Intent(this@SignUpEmailActivity, SignUpNameActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }
}