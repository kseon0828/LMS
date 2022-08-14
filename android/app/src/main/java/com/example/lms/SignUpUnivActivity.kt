package com.example.lms

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivitySignupUnivBinding

class SignUpUnivActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupUnivBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupUnivBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent //전달할 데이터를 받을 Intent
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        binding.signupNameEt.setText(name)
        binding.signupEmailEt.setText(email)

        binding.backBtn.setOnClickListener {
            startActivity(Intent(this,SignUpNameActivity::class.java))
        }

        binding.okBtn.setOnClickListener {
            val univ: String = binding.signupUnivEt.getText().toString()
            val name: String = binding.signupNameEt.getText().toString()
            val email: String = binding.signupEmailEt.getText().toString()
            //인텐트 선언 및 정의
            val intent = Intent(this@SignUpUnivActivity, SignUpSsnActivity::class.java)
            //입력한 input값을 intent로 전달한다.
            intent.putExtra("name",name)
            intent.putExtra("univ",univ)
            intent.putExtra("email",email)
            //액티비티 이동
            startActivity(intent)

            //startActivity(Intent(this,SignUpUnivActivity::class.java))
        }


    }
}