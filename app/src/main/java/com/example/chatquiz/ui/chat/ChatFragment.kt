package com.example.chatquiz.ui.chat

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatquiz.R
import com.example.chatquiz.adapter.SomethingAdapter
import com.example.chatquiz.databinding.FragmentChatBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.message_fragment.*

class ChatFragment : Fragment() {
    private val somethingAdapter by lazy {
        SomethingAdapter()
    }

    private lateinit var chatViewModel: ChatViewModel
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        chatViewModel =
            ViewModelProvider(this).get(ChatViewModel::class.java)

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        SetUpViewModel()
        toolbar_chat.inflateMenu(R.menu.chat_menu)

        toolbar_chat.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.navigation_message -> it.onNavDestinationSelected(findNavController())

                else -> super.onOptionsItemSelected(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun SetUpViewModel() {
        chatViewModel.observeAllSomething().observe(viewLifecycleOwner) {
            somethingAdapter.set(it)
        }
    }
    
    private fun setUpUI() {
        binding.recyclerView.apply {
            adapter = somethingAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}