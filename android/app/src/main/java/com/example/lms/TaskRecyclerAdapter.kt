package com.example.lms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lms.databinding.HomeworkItemBinding
import com.example.lms.fragment.CalendarFragment
import okhttp3.internal.concurrent.Task


class TaskRecyclerAdapter : RecyclerView.Adapter<TaskRecyclerAdapter.MyViewHolder>() {

    private var taskList = emptyList<Data>()

    class MyViewHolder(val binding: HomeworkItemBinding) : RecyclerView.ViewHolder(binding.root)

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HomeworkItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.homeworkContentTextView.text = taskList[position].taskName.toString()
//        holder.binding.idText.text = myList[position].id.toString()
//        holder.binding.titleText.text = myList[position].title
//        holder.binding.bodyText.text = myList[position].body
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return taskList.size
    }

    // 데이터 변경시 리스트 다시 할당
    fun setData(newList : List<Data>){
        taskList = newList
        // 새로고침
        notifyDataSetChanged()
    }
}