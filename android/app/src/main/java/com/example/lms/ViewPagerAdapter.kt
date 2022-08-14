package com.example.lms

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lms.fragment.CalendarFragment
import com.example.lms.fragment.TodoListFragment

class ViewPagerAdapter (fragment : FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment {
        return CalendarFragment()
//        return when (position) {
//            0 -> CalendarFragment()
//            else ->TodoListFragment()
//        }
    }
}