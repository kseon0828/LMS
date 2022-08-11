package com.example.lms.fragment

import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lms.*
import com.example.lms.databinding.FragmentTodoListBinding
import com.example.lms.dialog.MyCustomDialog
import com.example.lms.dialog.MyCustomDialog2
import com.example.lms.dialog.MyCustomDialogInterface

import java.util.*

class TodoListFragment : Fragment(), MyCustomDialogInterface {

    private var binding : FragmentTodoListBinding? = null
    private val memoViewModel: MemoViewModel by viewModels() // 뷰모델 연결
    private val adapter : TodoAdapter by lazy { TodoAdapter(memoViewModel) } // 어댑터 선언

    private val homeworkViewModel: HomeworkViewModel by viewModels() // 뷰모델 연결
    private val adapter2 : HomeworkAdapter by lazy { HomeworkAdapter(homeworkViewModel) } // 어댑터 선언

    private var isFabOpen = false // Fab 버튼 default는 닫혀있음

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        // 상단 메뉴 추가
//        setHasOptionsMenu(true)
        // 뷰바인딩
        binding = FragmentTodoListBinding.inflate(inflater,container,false)


        // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
        adapter.setHasStableIds(true)
        adapter2.setHasStableIds(true)

        // 아이템을 가로로 하나씩 보여주고 어댑터 연결
        binding!!.homeworkTodoRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        binding!!.homeworkTodoRecyclerView.adapter = adapter2
        binding!!.memoTodoRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        binding!!.memoTodoRecyclerView.adapter = adapter

        // 리스트 관찰하여 변경시 어댑터에 전달해줌
        memoViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        homeworkViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter2.setData(it)
        })

//        // Fab 클릭시 다이얼로그 띄움
//        binding!!.dialogButton.setOnClickListener {
//            onFabClicked()
//        }

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFABClickEvent()
    }

    private fun setFABClickEvent() {
        // 플로팅 버튼 클릭시 애니메이션 동작 기능
        binding!!.listAdd.setOnClickListener {
            toggleFab()
        }

        // Fab 클릭시 다이얼로그 띄움
        binding!!.listTodo.setOnClickListener {
            onFabClicked()
        }

        // Fab 클릭시 다이얼로그 띄움
        binding!!.listHomework.setOnClickListener {
            onFabClicked2()
        }
    }

    private fun toggleFab() {
        Toast.makeText(this.context, "메인 버튼 클릭!", Toast.LENGTH_SHORT).show()
        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding!!.listHomework, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding!!.listTodo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding!!.listAdd, View.ROTATION, 45f, 0f).apply { start() }
        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator.ofFloat(binding!!.listHomework, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding!!.listTodo, "translationY", -180f).apply { start() }
            ObjectAnimator.ofFloat(binding!!.listAdd, View.ROTATION, 0f, 45f).apply { start() }
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

//    // 서치바 추가
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.main_menu,menu)
//
//        val search = menu?.findItem(R.id.menu_search)
//        val searchView = search?.actionView as? SearchView
//        searchView?.isSubmitButtonEnabled = true
//        searchView?.setOnQueryTextListener(this)
//    }

    // 프래그먼트는 뷰보다 오래 지속 . 프래그먼트의 onDestroyView() 메서드에서 결합 클래스 인스턴스 참조를 정리
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    // 다이얼로그에서 추가버튼 클릭 됐을 때
    override fun onOkButtonClicked(content: String) {

        // 현재의 날짜를 불러옴
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val day = cal.get(Calendar.DATE)

        // 현재의 날짜로 메모를 추가해줌
        val memo = Memo(0,false,content, year, month, day)
        memoViewModel.addMemo(memo)
        Toast.makeText(activity,"추가", Toast.LENGTH_SHORT).show()
    }

    override fun onHomeworkOkButtonClicked(content: String) {
        // 현재의 날짜를 불러옴
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val day = cal.get(Calendar.DATE)

        // 선택된 날짜로 과제를 추가해줌
        val homework = Homework(0,false, content, year, month, day)
        homeworkViewModel.addHomework(homework)
        Toast.makeText(activity, "추가", Toast.LENGTH_SHORT).show()
    }
}