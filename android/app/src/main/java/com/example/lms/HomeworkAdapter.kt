package com.example.lms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lms.databinding.TodoItemBinding

import com.example.lms.Memo
import com.example.lms.databinding.HomeworkItemBinding
import com.example.lms.dialog.MyCustomDialog2
import com.example.lms.dialog.UpdateDialog
import com.example.lms.dialog.UpdateDialog2
import com.example.lms.dialog.UpdateDialogInterface

class HomeworkAdapter(private val homeworkViewModel: HomeworkViewModel) : RecyclerView.Adapter<HomeworkAdapter.MyViewHolder>() {
    private var homeworkList = emptyList<Homework>()

    // 뷰 홀더에 데이터를 바인딩
    class MyViewHolder(private val binding: HomeworkItemBinding) : RecyclerView.ViewHolder(binding.root),
        UpdateDialogInterface{
        lateinit var homework : Homework
        lateinit var homeworkViewModel: HomeworkViewModel

        fun bind(currentHomework : Homework, homeworkViewModel: HomeworkViewModel){
            binding.homework = currentHomework
            this.homeworkViewModel = homeworkViewModel

            // 체크 리스너 초기화 해줘 중복 오류 방지
            binding.homeworkCheckBox.setOnCheckedChangeListener(null)

            // 메모 체크 시 체크 데이터 업데이트
            binding.homeworkCheckBox.setOnCheckedChangeListener { _, check ->
                if (check) {
                    homework = Homework(currentHomework.id, true, currentHomework.content,
                        currentHomework.year, currentHomework.month, currentHomework.day)
                    this.homeworkViewModel.updateHomework(homework)
                }
                else {
                    homework = Homework(currentHomework.id, false, currentHomework.content,
                        currentHomework.year, currentHomework.month, currentHomework.day)
                    this.homeworkViewModel.updateHomework(homework)
                }
            }

            // 삭제 버튼 클릭 시 메모 삭제
            binding.deleteButton.setOnClickListener {
                homeworkViewModel.deleteHomework(currentHomework)
            }

            // 수정 버튼 클릭 시 다이얼로그 띄움
            binding.updateButton.setOnClickListener {
                homework = currentHomework
                val myCustomDialog = UpdateDialog2(binding.updateButton.context,this)
                myCustomDialog.show()
            }

        }

        // 다이얼로그의 결과값으로 업데이트 해줌
        override fun onOkButtonClicked(content: String) {
            val updateHomework = Homework(homework.id,homework.check,content,homework.year,homework.month,homework.day)
            homeworkViewModel.updateHomework(updateHomework)
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HomeworkItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 바인딩 함수로 넘겨줌
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(homeworkList[position],homeworkViewModel)
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return homeworkList.size
    }

    // 메모 리스트 갱신
    fun setData(homework : List<Homework>){
        homeworkList = homework
        notifyDataSetChanged()
    }

    // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}