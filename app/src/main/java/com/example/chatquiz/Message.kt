package com.example.chatquiz

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.chatquiz.databinding.FragmentChatBinding
import com.example.chatquiz.databinding.MessageFragmentBinding
import com.example.chatquiz.ui.chat.ChatViewModel
import com.example.data.models.SomethingDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.message_fragment.*

class Message : Fragment() {

    companion object {
        fun newInstance() = Message()
    }

    private lateinit var viewModel: MessageViewModel
    private var _binding: MessageFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(MessageViewModel::class.java)

        _binding = MessageFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.addButton.isEnabled = false
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar_message.setNavigationIcon(R.drawable.ic_close)
        toolbar_message.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_navigation_message_to_navigation_chat)
        }
        val messageObj = binding.messageText
        val fioObj = binding.fio
        var boolMessage = false
        var boolFio = false

        messageObj.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(binding.messageText.text.toString() != "") boolMessage = true
                else boolMessage = false

                if(boolMessage && boolFio) addButton.isEnabled = true
                else addButton.isEnabled = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        fioObj.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(binding.fio.text.toString() != "") boolFio = true
                else boolFio = false

                if(boolMessage && boolFio) addButton.isEnabled = true
                else addButton.isEnabled = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        addButton.setOnClickListener {
            val model = SomethingDB(
                message = binding.messageText.text.toString(),
                fio = binding.fio.text.toString())
            viewModel.addStaticSomething(model)
            findNavController().navigate(R.id.action_navigation_message_to_navigation_chat)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}