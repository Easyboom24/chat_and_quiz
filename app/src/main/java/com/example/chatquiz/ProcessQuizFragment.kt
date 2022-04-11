package com.example.chatquiz

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.contains
import androidx.navigation.fragment.findNavController
import com.example.chatquiz.databinding.ActivityMainBinding
import com.example.chatquiz.databinding.ProcessQuizFragmentBinding
import com.example.chatquiz.ui.Question
import com.example.chatquiz.ui.QuestionsList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.process_quiz_fragment.*

class ProcessQuizFragment : Fragment() {

    companion object {
        fun newInstance() = ProcessQuizFragment()
        var correctAnswerCount: Int = 0
        var currentPosition: Int = 1
    }

    private var listQuestions = QuestionsList.questionsList
    private var currentQuestion: Question? = null
    private var listButtons : List<ConstraintLayout>? = null
    private var listAnswers : List<TextView>? = null
    private var _binding: ProcessQuizFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProcessQuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this).get(ProcessQuizViewModel::class.java)

        _binding = ProcessQuizFragmentBinding.inflate(inflater, container, false)
        setQuestionOnView()
        listButtons = listOf(binding.button,binding.button2,binding.button3,binding.button4)
        listAnswers = listOf(binding.answer1,binding.answer2,binding.answer3,binding.answer4)
        binding.buttonSubmit.isEnabled = false
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener { checkAnswer(it as ConstraintLayout,0) }
        binding.button2.setOnClickListener { checkAnswer(it as ConstraintLayout,1) }
        binding.button3.setOnClickListener { checkAnswer(it as ConstraintLayout,2) }
        binding.button4.setOnClickListener { checkAnswer(it as ConstraintLayout,3) }

        binding.buttonSubmit.setOnClickListener {
            if(currentPosition < listQuestions.size){
                binding.buttonSubmit.isEnabled = false
                setQuestionOnView()
            }
            else {
                findNavController().navigate(R.id.action_navigation_processQuiz_to_endQuizFragment)
            }
        }
    }

    private fun setQuestionOnView(){
        currentQuestion = listQuestions[currentPosition-1]
        startStyleOptions()
        binding.questionCount.text = "Вопрос "+currentPosition.toString() + "/10"
        binding.textQuestion.text = currentQuestion?.question
        binding.option1.text = currentQuestion?.optionsQuestion?.get(0)
        binding.option2.text = currentQuestion?.optionsQuestion?.get(1)
        binding.option3.text = currentQuestion?.optionsQuestion?.get(2)
        binding.option4.text = currentQuestion?.optionsQuestion?.get(3)
    }

    private fun startStyleOptions() {
        listButtons?.forEach {
            it.setBackgroundResource(R.drawable.bg_question_card)
            it.isEnabled = true
        }

        listAnswers?.forEach {
            it.setBackgroundColor(Color.TRANSPARENT)
            it.text = ""
        }
    }

    private fun checkAnswer(view: ConstraintLayout, checkedAnswer: Int){
        listButtons?.forEach { it.isEnabled = false }
        currentPosition++
        binding.buttonSubmit.isEnabled = true

        if(checkedAnswer==currentQuestion?.correctAnswer?.uuid){
            view.setBackgroundResource(R.drawable.border_correct_answer)
            var correctAnswerTabloid = listAnswers?.get(checkedAnswer)
            correctAnswerTabloid?.text = "Ваш выбор"
            correctAnswerTabloid?.setBackgroundResource(R.drawable.bg_correct_answer)
            correctAnswerCount++
        }
        else {
            var correctAnswerTabloid = listAnswers?.get(currentQuestion!!.correctAnswer.uuid)
            var incorrectAnswerTabloid = listAnswers?.get(checkedAnswer)
            view.setBackgroundResource(R.drawable.border_incorrect_answer)
            incorrectAnswerTabloid?.text = "Ваш выбор"
            incorrectAnswerTabloid?.setBackgroundResource(R.drawable.bg_incorrect_answer)

            correctAnswerTabloid?.text = "Верный ответ"
            correctAnswerTabloid?.setBackgroundResource(R.drawable.bg_correct_answer)
        }
    }
}