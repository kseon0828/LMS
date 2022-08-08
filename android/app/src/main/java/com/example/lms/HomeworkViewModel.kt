package com.example.lms

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeworkViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<Homework>>
    val readDoneData : LiveData<List<Homework>>
    private val repository : HomeworkRepository

    // get set
    private var _currentData = MutableLiveData<List<Homework>>()
    val currentData : LiveData<List<Homework>>
        get() = _currentData

    init{
        val homeworkDao = HomeworkDatabase.getDatabase(application)!!.homeworkDao()
        repository = HomeworkRepository(homeworkDao)
        readAllData = repository.readAllData.asLiveData()
        readDoneData = repository.readDoneData.asLiveData()
    }

    fun addHomework(homework : Homework){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHomework(homework)
        }
    }

    fun updateHomework(homework : Homework){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateHomework(homework)
        }
    }

    fun deleteHomework(homework : Homework){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHomework(homework)
        }
    }

    fun readDateData(year : Int, month : Int, day : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val tmp = repository.readDateData(year, month, day)
            _currentData.postValue(tmp)
        }
    }
}