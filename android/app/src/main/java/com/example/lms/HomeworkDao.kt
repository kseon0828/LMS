package com.example.lms

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface HomeworkDao {
    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHomework(homework : Homework)

    @Update
    suspend fun updateHomework(homework : Homework)

    @Delete
    suspend fun deleteHomework(homework : Homework)

    // 큰 날짜부터 출력
    @Query("SELECT * FROM Homework ORDER BY year DESC, month DESC, day DESC, id DESC")
    fun readAllData() : Flow<List<Homework>>

    // 날짜 정보를 입력받아 그 날짜에 해당하는 메모만 반환
    @Query("SELECT * FROM Homework WHERE year = :year AND month = :month AND day = :day ORDER BY id DESC")
    fun readDateData(year : Int, month : Int, day : Int) : List<Homework>

    // 완료한 메모만 출력
    @Query("SELECT * FROM Homework WHERE `check` = 1 ORDER BY year DESC, month DESC, day DESC, id DESC")
    fun readDoneData() : Flow<List<Homework>>

}