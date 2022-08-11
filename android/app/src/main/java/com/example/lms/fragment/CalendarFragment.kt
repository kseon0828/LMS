package com.example.lms.fragment

import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lms.*
import com.example.lms.databinding.FragmentCalendarBinding
import com.example.lms.dialog.MyCustomDialog
import com.example.lms.dialog.MyCustomDialog2
import com.example.lms.dialog.MyCustomDialogInterface


class CalendarFragment : Fragment(), MyCustomDialogInterface {

    private var binding : FragmentCalendarBinding? = null
    private val memoViewModel: MemoViewModel by viewModels() // 뷰모델 연결
    private val adapter : TodoAdapter by lazy { TodoAdapter(memoViewModel) } // 어댑터 선언

    private val homeworkViewModel: HomeworkViewModel by viewModels() // 뷰모델 연결
    private val adapter2 : HomeworkAdapter by lazy { HomeworkAdapter(homeworkViewModel) } // 어댑터 선언

    private var year : Int = 0
    private var month : Int = 0
    private var day : Int = 0

    private var isFabOpen = false // Fab 버튼 default는 닫혀있음

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 뷰바인딩
        binding = FragmentCalendarBinding.inflate(inflater,container,false)

        // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
        adapter.setHasStableIds(true)
        adapter2.setHasStableIds(true)

        // 아이템을 가로로 하나씩 보여주고 어댑터 연결
        binding!!.homeworkCalendarRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        binding!!.homeworkCalendarRecyclerview.adapter = adapter2
        binding!!.memoCalendarRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        binding!!.memoCalendarRecyclerview.adapter = adapter

        binding!!.calendarView.setOnDateChangeListener { _, year, month, day ->
            // 날짜 선택시 그 날의 정보 할당
            this.year = year
            this.month = month+1
            this.day = day

            binding!!.calendarDateText.text = "${this.year}/${this.month}/${this.day}"

            // 해당 날짜 데이터를 불러옴 (currentData 변경)
            memoViewModel.readDateData(this.year,this.month,this.day)
            homeworkViewModel.readDateData(this.year,this.month,this.day)
        }

        // 메모 데이터가 수정되었을 경우 날짜 데이터를 불러옴 (currentData 변경)
        memoViewModel.readAllData.observe(viewLifecycleOwner, {
            memoViewModel.readDateData(year, month, day)
        })

        // 현재 날짜 데이터 리스트(currentData) 관찰하여 변경시 어댑터에 전달해줌
        memoViewModel.currentData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            Log.d("test5", "onCreateView: gg")
        })


        homeworkViewModel.readAllData.observe(viewLifecycleOwner, {
            homeworkViewModel.readDateData(year, month, day)
        })


        homeworkViewModel.currentData.observe(viewLifecycleOwner, Observer {
            adapter2.setData(it)
            Log.d("test6", "onCreateView: ggg")
        })




//        // Fab 클릭시 다이얼로그 띄움
//        binding!!.calendarDialogButton.setOnClickListener {
//            if(year == 0) {
//                Toast.makeText(activity, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                onFabClicked()
//            }
//        }
//
//
//        // Fab 클릭시 다이얼로그 띄움
//        binding!!.calendarDialogButton2.setOnClickListener {
//            if(year == 0) {
//                Toast.makeText(activity, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                onFabClicked2()
//            }
//        }

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFABClickEvent()
    }

    private fun setFABClickEvent() {
        // 플로팅 버튼 클릭시 애니메이션 동작 기능
        binding!!.calendarAdd.setOnClickListener {
            toggleFab()
        }

        // Fab 클릭시 다이얼로그 띄움
        binding!!.calendarTodo.setOnClickListener {
            if(year == 0) {
                Toast.makeText(activity, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                onFabClicked()
            }
        }

        // Fab 클릭시 다이얼로그 띄움
        binding!!.calendarHomework.setOnClickListener {
            if(year == 0) {
                Toast.makeText(activity, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                onFabClicked2()
            }
        }
    }

    private fun toggleFab() {
        Toast.makeText(this.context, "메인 버튼 클릭!", Toast.LENGTH_SHORT).show()
        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding!!.calendarHomework, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding!!.calendarTodo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding!!.calendarAdd, View.ROTATION, 45f, 0f).apply { start() }
        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator.ofFloat(binding!!.calendarHomework, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding!!.calendarTodo, "translationY", -180f).apply { start() }
            ObjectAnimator.ofFloat(binding!!.calendarAdd, View.ROTATION, 0f, 45f).apply { start() }
        }

        isFabOpen = !isFabOpen

    }

    // Fab 클릭시 사용되는 함수
    private fun onFabClicked(){
        val myCustomDialog = MyCustomDialog(requireActivity(),this)
        myCustomDialog.show()
    }

    // Fab 클릭시 사용되는 함수
    private fun onFabClicked2(){
        val myCustomDialog2 = MyCustomDialog2(requireActivity(),this)
        myCustomDialog2.show()
    }

    // 프래그먼트는 뷰보다 오래 지속 . 프래그먼트의 onDestroyView() 메서드에서 결합 클래스 인스턴스 참조를 정리
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onOkButtonClicked(content: String) {
        // 선택된 날짜로 메모를 추가해줌
        val memo = Memo(0,false, content, year, month, day)
        memoViewModel.addMemo(memo)
        Toast.makeText(activity, "추가", Toast.LENGTH_SHORT).show()

    }

    override fun onHomeworkOkButtonClicked(content: String) {

        // 선택된 날짜로 과제를 추가해줌
        val homework = Homework(0,false, content, year, month, day)
        homeworkViewModel.addHomework(homework)
        Toast.makeText(activity, "추가", Toast.LENGTH_SHORT).show()
    }

}