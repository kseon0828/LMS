package com.example.lms

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity(){
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding.signUpSignUpBtn.setOnClickListener {
//            signUp()
//        }
    }

//    private fun getUser(): User {
//        val email: String =
//            binding.signUpIdEt.text.toString() + "@" + binding.signUpDirectInputEt.text.toString()
//        val univ: String = binding.signUpUnivEt.text.toString()
//        val ssn: String = binding.signUpSsnEt.text.toString()
//        val name: String = binding.signUpNameEt.text.toString()
//        val pwd: String = binding.signUpPwdEt.text.toString()
//
//        return User(email, pwd, name, univ,ssn)
//    }

//    private fun signUp() {
//        if (binding.signUpIdEt.text.toString()
//                .isEmpty() || binding.signUpDirectInputEt.text.toString().isEmpty()
//        ) {
//            Toast.makeText(this, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        if (binding.signUpUnivEt.text.toString().isEmpty()) {
//            Toast.makeText(this, "학교 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        if (binding.signUpSsnEt.text.toString().isEmpty()) {
//            Toast.makeText(this, "학번 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        if (binding.signUpNameEt.text.toString().isEmpty()) {
//            Toast.makeText(this, "이름 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        if (binding.signUpPwdEt.text.toString() != binding.signUpPwdCheckEt.text.toString()) {
//            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        val authService = AuthService()
//        authService.setSignUpView(this)
//
//        authService.signUp(getUser())
//        Log.d("SIGNUP-ACT/ASYNC", "Hello, FLO")
//    }

//    override fun onSignUpSuccess() {
//        finish()
//    }
//
//    override fun onSignUpFailure() {
//        //실패처리
//    }
}