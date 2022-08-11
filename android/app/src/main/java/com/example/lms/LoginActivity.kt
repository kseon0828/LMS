package com.example.lms


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.lms.databinding.ActivityLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginEmailstartTv.setOnClickListener {
            startActivity(Intent(this,LoginEmailActivity::class.java))
        }

        val bottomSheetView = layoutInflater.inflate(R.layout.login_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)

        binding.loginNotify.setOnClickListener {
            bottomSheetDialog.show()
        }


//        binding.loginSignUpTv.setOnClickListener {
//            startActivity(Intent(this, SignUpActivity::class.java))
//        }
//
//        binding.loginSignInBtn.setOnClickListener {
//            startMainActivity()
//        }

    }


    private fun login() {
//        if (binding.loginIdEt.text.toString()
//                .isEmpty() || binding.loginDirectInputEt.text.toString().isEmpty()
//        ) {
//            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        if (binding.loginPwdEt.text.toString().isEmpty()) {
//            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        val authService = AuthService()
//        authService.setLoginView(this)
//
//        authService.login(getUser())
//        Log.d("Login-ACT/ASYNC", "Hello, LMS")
    }


//    private fun getUser(): User {
//        val email = binding.loginIdEt.text.toString() + "@" + binding.loginDirectInputEt.text.toString()
//        val pwd = binding.loginPwdEt.text.toString()
//
//        return User(email = email, pwd = pwd,name ="", univ ="",ssn="")
//    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun saveJwt(jwt: Int) {
        val spf = getSharedPreferences("auth" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putInt("jwt", jwt)
        editor.apply()
    }

     private fun saveJwt2(jwt: String) {
        val spf = getSharedPreferences("auth2" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

//    override fun onLoginSuccess(code : Int , result: Result) {
//        when(code) {
//            1000 -> {
//                saveJwt2(result.jwt)
//                startMainActivity()
//                Log.d("Logintest", "로그인 성공")
//
//            }
//        }
//    }
//
//    override fun onLoginFailure() {
//
//    }



}