package com.example.chatquiz.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatquiz.R
import com.example.data.models.SomethingDB
import com.example.data.repositories.SomethingRepository
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    //val text: LiveData<String> = _text

    fun observeAllSomething() = SomethingRepository.instance.getAllSomethingData()

}