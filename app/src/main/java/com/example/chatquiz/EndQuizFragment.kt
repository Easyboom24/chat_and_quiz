package com.example.chatquiz

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chatquiz.ProcessQuizFragment.Companion.correctAnswerCount
import com.example.chatquiz.databinding.EndQuizFragmentBinding
import com.example.chatquiz.databinding.ProcessQuizFragmentBinding

class EndQuizFragment : Fragment() {

    companion object {
        fun newInstance() = EndQuizFragment()
    }

    private lateinit var viewModel: EndQuizViewModel
    private var _binding: EndQuizFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this).get(EndQuizViewModel::class.java)

        _binding = EndQuizFragmentBinding.inflate(inflater, container, false)
        binding.resultQuiz.text = "Вы ответили на "+ correctAnswerCount + " из 10 вопросов."
        val root: View = binding.root

        return root
    }



}