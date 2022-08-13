package com.example.lms

import android.R.id.input
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivitySignupNameBinding


class SignUpNameActivity: AppCompatActivity() {
    lateinit var binding: ActivitySignupNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent //전달할 데이터를 받을 Intent
        val email = intent.getStringExtra("email")
        binding.signupEmailEt.setText(email)


        binding.backBtn.setOnClickListener {
            startActivity(Intent(this,SignUpEmailActivity::class.java))
        }
        binding.okBtn.setOnClickListener {
            val name: String = binding.signupNameEt.getText().toString()
            val email: String = binding.signupEmailEt.getText().toString()
            //인텐트 선언 및 정의
            val intent = Intent(this@SignUpNameActivity, SignUpUnivActivity::class.java)
            //입력한 input값을 intent로 전달한다.
            intent.putExtra("name", name)
            intent.putExtra("email", email)
            //액티비티 이동
            startActivity(intent)
        }


    }
}