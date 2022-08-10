package com.example.lms.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lms.R

class MyCustomDialog2(context : Context, myInterface: MyCustomDialogInterface) : Dialog(context) {
    // 액티비티에서 인터페이스를 받아옴
    private var myCustomDialogInterface: MyCustomDialogInterface = myInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog2)

        var okButton : Button = findViewById(R.id.okButton)
        var cancelButton : Button = findViewById(R.id.cancelButton)
        var homeworkEditView : EditText = findViewById(R.id.homeworkEditView)
        var subjectEditView : EditText = findViewById(R.id.subjectEditView)
        var startMonthEditView : EditText = findViewById(R.id.start_month_EditView)
        var startDayEditView : EditText = findViewById(R.id.start_day_EditView)
        var endMonthEditView : EditText = findViewById(R.id.end_month_EditView)
        var endDayEditView : EditText = findViewById(R.id.end_day_EditView)
        var endHourEditView : EditText = findViewById(R.id.end_hour_EditView)
        var endMinuteEditView : EditText = findViewById(R.id.end_minute_EditView)

        // 배경 투명하게 바꿔줌
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        okButton.setOnClickListener {
            val content = homeworkEditView.text.toString()
            val subject = subjectEditView.text.toString()
            val startMonth = startMonthEditView.text.toString()
            val startDay = startDayEditView.text.toString()
            val endMonth = endMonthEditView.text.toString()
            val endDay = endDayEditView.text.toString()
            val endHour = endHourEditView.text.toString()
            val endMinute = endMinuteEditView.text.toString()

            // 입력하지 않았을 때
            if ( TextUtils.isEmpty(content)){
                Toast.makeText(context, "과제를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            if ( TextUtils.isEmpty(subject)){
                Toast.makeText(context, "과목명을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            if ( TextUtils.isEmpty(startMonth) || TextUtils.isEmpty(startDay)){
                Toast.makeText(context, "시작 날짜를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            if ( TextUtils.isEmpty(endMonth) || TextUtils.isEmpty(endDay)){
                Toast.makeText(context, "마감 날짜를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            if ( TextUtils.isEmpty(endHour) || TextUtils.isEmpty(endMinute)){
                Toast.makeText(context, "마감 시간을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

            // 입력 창이 비어 있지 않을 때
            if( content.isNotEmpty() && subject.isNotEmpty() && startMonth.isNotEmpty() && startDay.isNotEmpty() && endMonth.isNotEmpty() && endDay.isNotEmpty() && endHour.isNotEmpty() && endMinute.isNotEmpty()){
                // 메모를 추가해줌
                myCustomDialogInterface.onHomeworkOkButtonClicked(content)
                dismiss()
            }
        }

        // 취소 버튼 클릭 시 종료
        cancelButton.setOnClickListener { dismiss()}
    }
}