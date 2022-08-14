package com.example.lms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivityLoginEmailBinding


class LoginEmailActivity: AppCompatActivity(){
    lateinit var binding: ActivityLoginEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.okBtn.setOnClickListener {
            val email: String = binding.loginIdEt.getText().toString()
            //인텐트 선언 및 정의
            val intent = Intent(this@LoginEmailActivity, LoginPasswordActivity::class.java)
            //입력한 input값을 intent로 전달한다.
            intent.putExtra("email", email)
            //액티비티 이동
            startActivity(intent)
        }


    }



}