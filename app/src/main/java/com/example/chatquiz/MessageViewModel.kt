package com.example.chatquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatquiz.databinding.FragmentChatBinding
import com.example.chatquiz.databinding.MessageFragmentBinding
import com.example.data.models.SomethingDB
import com.example.data.repositories.SomethingRepository
import kotlinx.coroutines.launch
import kotlinx.android.synthetic.main.message_fragment.*

class MessageViewModel : ViewModel() {

    //private var _binding: MessageFragmentBinding? = null
    //private val binding get() = _binding!!


    fun addStaticSomething(model: SomethingDB) {

        viewModelScope.launch {

            SomethingRepository.instance.addSomething(model)
        }
    }
}