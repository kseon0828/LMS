package com.example.lms

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivitySignupSuccessBinding

class SignUpSuccessActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent //전달할 데이터를 받을 Intent
        val name = intent.getStringExtra("name")
        binding.successName.setText(name+"님,")


        Handler().postDelayed({
            val intent = Intent(this, MainAgreeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        },DURATION)

    }
    companion object {
        private const val DURATION : Long = 2000
    }

}