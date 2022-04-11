package com.example.chatquiz.ui

data class Question(
    var uuid: Int,
    var question: String,
    var optionsQuestion: List<String>,
    var correctAnswer: CorrectAnswer )
