package com.example.lms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivityLoginEmailBinding


class LoginEmailActivity: AppCompatActivity(), LoginView {
    lateinit var binding: ActivityLoginEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.okBtn.setOnClickListener {
            login()
        }


    }

    private fun login() {
        if (binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val authService = AuthService()
        authService.setLoginView(this)

        authService.login(getUser())
        Log.d("Login-ACT/ASYNC", "Hello, LMS")

        startActivity(Intent(this,LoginPasswordActivity::class.java))
    }


    private fun getUser(): User {
        val email = binding.loginIdEt.text.toString()

        return User(email = email, pwd = "",name ="", univ ="",ssn="")
    }

    private fun saveJwt2(jwt: String) {
        val spf = getSharedPreferences("auth2" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    override fun onLoginSuccess(code : Int , result: Result) {
        when(code) {
            1000 -> {
                saveJwt2(result.jwt)
                Log.d("Logintest", "로그인 성공")

            }
        }
    }

    override fun onLoginFailure() {

    }

}