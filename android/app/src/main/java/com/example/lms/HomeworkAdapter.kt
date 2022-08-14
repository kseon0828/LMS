package com.example.lms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lms.databinding.HomeworkItemBinding
import com.example.lms.dialog.MyCustomDialog2
import com.example.lms.dialog.UpdateDialog2
import com.example.lms.dialog.UpdateDialogInterface
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*

class HomeworkAdapter(private val homeworkViewModel: HomeworkViewModel) : RecyclerView.Adapter<HomeworkAdapter.MyViewHolder>() {
    private var homeworkList = emptyList<Homework>()

    // 뷰 홀더에 데이터를 바인딩
    class MyViewHolder(private val binding: HomeworkItemBinding) : RecyclerView.ViewHolder(binding.root),
        UpdateDialogInterface{
        lateinit var homework : Homework
        private lateinit var homeworkViewModel: HomeworkViewModel
//        private lateinit var eventDecorator: EventDecorator
//        lateinit var calendar: MaterialCalendarView

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
            binding.homeworkDeleteButton.setOnClickListener {
//                var date = Calendar.getInstance()
//                date.set(currentHomework.year, currentHomework.month, currentHomework.day)
//                var day = CalendarDay.from(date) // Calendar 자료형을 넣어주면 됨
//                eventDecorator.dates.remove(day)
//                calendar.removeDecorators()
//                calendar.invalidateDecorators()

                homeworkViewModel.deleteHomework(currentHomework)
            }

            // 수정 버튼 클릭 시 다이얼로그 띄움
            binding.homeworkUpdateButton.setOnClickListener {
                homework = currentHomework
                val myCustomDialog2 = UpdateDialog2(binding.homeworkUpdateButton.context,this)
                myCustomDialog2.show()
            }

        }

        // 다이얼로그의 결과값으로 업데이트 해줌
        override fun onHomeworkOkButtonClicked(content: String) {
            val updateHomework = Homework(homework.id,homework.check,content,homework.year,homework.month,homework.day)
            homeworkViewModel.updateHomework(updateHomework)
        }

        override fun onOkButtonClicked(content: String) {

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

