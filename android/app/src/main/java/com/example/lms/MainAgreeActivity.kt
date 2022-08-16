package com.example.lms


import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.lms.databinding.ActivityLoginBinding
import com.example.lms.databinding.ActivityLoginSuccessBinding
import com.example.lms.databinding.ActivityMainAgreeBinding
import com.example.lms.databinding.ActivityMainDatalinkBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class MainAgreeActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainDatalinkBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDatalinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val agreeView = layoutInflater.inflate(R.layout.activity_main_agree, null)
        val agreeDialog = BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme)
        agreeDialog.setContentView(agreeView)
        val continueButton1 = agreeDialog.findViewById<ImageButton>(R.id.continue_btn)

        val termsView = layoutInflater.inflate(R.layout.activity_main_terms, null)
        val termsDialog = BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme)
        termsDialog.setContentView(termsView)
        val checkButton = termsDialog.findViewById<CheckBox>(R.id.check_circle_btn)
        val checkMiniButton1 = termsDialog.findViewById<CheckBox>(R.id.terms_subtitle1_img)
        val checkMiniButton2 = termsDialog.findViewById<CheckBox>(R.id.terms_subtitle2_img)
        val checkMiniButton3 = termsDialog.findViewById<CheckBox>(R.id.terms_subtitle3_img)
        val continueButton2 = termsDialog.findViewById<ImageButton>(R.id.continue_btn)

        binding.dataLinkButton.setOnClickListener {
            continueButton1?.setOnClickListener {
                agreeDialog.dismiss()

                checkButton?.setOnCheckedChangeListener { _, check ->
                    if (check) {
                        checkMiniButton1?.isChecked = true
                        checkMiniButton2?.isChecked = true
                        checkMiniButton3?.isChecked = true

                        continueButton2?.setOnClickListener{
                            termsDialog.dismiss()
                            startActivity(Intent(this,MainActivity::class.java))
                        }
                    }
                    else {
                        checkMiniButton1?.isChecked = false
                        checkMiniButton2?.isChecked = false
                        checkMiniButton3?.isChecked = false

                        continueButton2?.setOnClickListener{
                            Toast.makeText(this, "약관에 모두 동의해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                termsDialog.show()
            }
            agreeDialog.show()
        }
    }

}