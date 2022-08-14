package com.example.lms.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.lms.R
import com.example.lms.databinding.LayoutDialog2Binding
import android.widget.ArrayAdapter
import android.widget.AdapterView
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class MyCustomDialog2(context : Context, myInterface: MyCustomDialogInterface) : Dialog(context) {
    // 액티비티에서 인터페이스를 받아옴
    private var myCustomDialogInterface: MyCustomDialogInterface = myInterface

    lateinit var binding : LayoutDialog2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutDialog2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        // 스피너 선언
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter=ArrayAdapter.createFromResource(context,R.array.itemList,android.R.layout.simple_spinner_item)


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.optionEditView.setText("과제 종류를 골라주세요.")
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    //선택안함
                    0 -> {
                        binding.optionEditView.setText("온라인강의")
                    }
                    1 -> {
                        binding.optionEditView.setText("과제")
                    }
                    2 -> {
                        binding.optionEditView.setText("팀프로젝트")
                    }
                    3 -> {
                        binding.optionEditView.setText("토론")
                    }
                    4 -> {
                        binding.optionEditView.setText("설문")
                    }
                    5 -> {
                        binding.optionEditView.setText("투표")
                    }
                    6 -> {
                        binding.optionEditView.setText("시험")
                    }
                    7 -> {
                        binding.optionEditView.setText("실시간강의")
                    }


                    //일치하는게 없는 경우
                    else -> {

                    }
                }
            }
        }


        binding.dateBtn.setOnClickListener {
            val cal = Calendar.getInstance()    //캘린더뷰 만들기
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    binding.dateEditView.text = year.toString()+"-"+(month + 1).toString()+"-"+dayOfMonth.toString()
                }
            DatePickerDialog(
                context,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        var okButton : Button = findViewById(R.id.okButton)
        var cancelButton : Button = findViewById(R.id.cancelButton)
        var homeworkEditView : EditText = findViewById(R.id.homeworkEditView)
        var subjectEditView : EditText = findViewById(R.id.subjectEditView)
//        var startMonthEditView : EditText = findViewById(R.id.start_month_EditView)
//        var startDayEditView : EditText = findViewById(R.id.start_day_EditView)
//        var endMonthEditView : EditText = findViewById(R.id.end_month_EditView)
//        var endDayEditView : EditText = findViewById(R.id.end_day_EditView)
//        var endHourEditView : EditText = findViewById(R.id.end_hour_EditView)
//        var endMinuteEditView : EditText = findViewById(R.id.end_minute_EditView)

        // 배경 투명하게 바꿔줌
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        okButton.setOnClickListener {
            val content = homeworkEditView.text.toString()
            val subject = subjectEditView.text.toString()
//            val startMonth = startMonthEditView.text.toString()
//            val startDay = startDayEditView.text.toString()
//            val endMonth = endMonthEditView.text.toString()
//            val endDay = endDayEditView.text.toString()
//            val endHour = endHourEditView.text.toString()
//            val endMinute = endMinuteEditView.text.toString()

            // 입력하지 않았을 때
            if ( TextUtils.isEmpty(content)){
                Toast.makeText(context, "과제를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            if ( TextUtils.isEmpty(subject)){
                Toast.makeText(context, "과목명을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
//            if ( TextUtils.isEmpty(startMonth) || TextUtils.isEmpty(startDay)){
//                Toast.makeText(context, "시작 날짜를 입력해주세요.", Toast.LENGTH_SHORT).show()
//            }
//            if ( TextUtils.isEmpty(endMonth) || TextUtils.isEmpty(endDay)){
//                Toast.makeText(context, "마감 날짜를 입력해주세요.", Toast.LENGTH_SHORT).show()
//            }
//            if ( TextUtils.isEmpty(endHour) || TextUtils.isEmpty(endMinute)){
//                Toast.makeText(context, "마감 시간을 입력해주세요.", Toast.LENGTH_SHORT).show()
//            }

//            // 입력 창이 비어 있지 않을 때
//            if( content.isNotEmpty() && subject.isNotEmpty() && startMonth.isNotEmpty() && startDay.isNotEmpty() && endMonth.isNotEmpty() && endDay.isNotEmpty() && endHour.isNotEmpty() && endMinute.isNotEmpty()){
//                // 메모를 추가해줌
//                myCustomDialogInterface.onHomeworkOkButtonClicked(content)
//                dismiss()
//            }

            if( content.isNotEmpty() && subject.isNotEmpty() ){
                // 메모를 추가해줌
                myCustomDialogInterface.onHomeworkOkButtonClicked(content)
                dismiss()
            }
        }

        // 취소 버튼 클릭 시 종료
        cancelButton.setOnClickListener { dismiss()}
    }
}