package com.example.lms

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivitySignupSaveBinding

class SignUpSaveActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupSaveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupSaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent //전달할 데이터를 받을 Intent
        val name = intent.getStringExtra("name")
        val univ = intent.getStringExtra("univ")
        val ssn = intent.getStringExtra("ssn")
        val email = intent.getStringExtra("email")
        binding.signupNameEt.setText(name)
        binding.signupUnivEt.setText(univ)
        binding.signupSsnEt.setText(ssn)
        binding.signupEmailEt.setText(email)

        binding.backBtn.setOnClickListener {
            startActivity(Intent(this,SignUpSsnActivity::class.java))
        }

        binding.okBtn.setOnClickListener {
            val ssn: String = binding.signupSsnEt.getText().toString()
            val univ: String = binding.signupUnivEt.getText().toString()
            val name: String = binding.signupNameEt.getText().toString()
            val email: String = binding.signupEmailEt.getText().toString()
            //인텐트 선언 및 정의
            val intent = Intent(this@SignUpSaveActivity, SignUpPasswordActivity::class.java)
            //입력한 input값을 intent로 전달한다.
            intent.putExtra("name",name)
            intent.putExtra("univ",univ)
            intent.putExtra("ssn",ssn)
            intent.putExtra("email",email)
            //액티비티 이동
            startActivity(intent)
        }



    }



}