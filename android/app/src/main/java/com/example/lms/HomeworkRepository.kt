package com.example.lms

import kotlinx.coroutines.flow.Flow

// 앱에서 사용하는 데이터와 그 데이터 통신을 하는 역할
class HomeworkRepository(private val homeworkDao: HomeworkDao) {
    val readAllData : Flow<List<Homework>> = homeworkDao.readAllData()
    val readDoneData : Flow<List<Homework>> = homeworkDao.readDoneData()

    suspend fun addHomework(homework: Homework){
        homeworkDao.addHomework(homework)
    }

    suspend fun updateHomework(homework: Homework){
        homeworkDao.updateHomework(homework)
    }

    suspend fun deleteHomework(homework: Homework){
        homeworkDao.deleteHomework(homework)
    }

    fun readDateData(year : Int, month : Int, day : Int): List<Homework> {
        return homeworkDao.readDateData(year, month, day)
    }
}