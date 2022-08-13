package com.example.lms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivitySignupPasswordBinding


class SignUpPasswordActivity : AppCompatActivity(), SignUpView  {
    lateinit var binding: ActivitySignupPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupPasswordBinding.inflate(layoutInflater)
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
            startActivity(Intent(this,SignUpSaveActivity::class.java))
        }


        binding.okBtn.setOnClickListener {

            signUp()
        }

    }

    private fun getUser(): User {
        val email: String = binding.signupEmailEt.text.toString()
        val univ: String = binding.signupUnivEt.text.toString()
        val ssn: String = binding.signupSsnEt.text.toString()
        val name: String = binding.signupNameEt.text.toString()
        val pwd: String = binding.signupPasswordEt.text.toString()

        return User(email, pwd, name, univ,ssn)
    }

    private fun signUp() {
        if (binding.signupPasswordEt.text.toString() != binding.signupPassword2Et.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val authService = AuthService()
        authService.setSignUpView(this)

        authService.signUp(getUser())
        Log.d("SIGNUP-ACT/ASYNC", "Hello, FLO")
    }

    override fun onSignUpSuccess() {
        val name: String = binding.signupNameEt.getText().toString()
        //인텐트 선언 및 정의
        val intent = Intent(this@SignUpPasswordActivity, SignUpSuccessActivity::class.java)
        //입력한 input값을 intent로 전달한다.
        intent.putExtra("name",name)
        //액티비티 이동
        startActivity(intent)



    }

    override fun onSignUpFailure() {
        //실패처리
    }


}