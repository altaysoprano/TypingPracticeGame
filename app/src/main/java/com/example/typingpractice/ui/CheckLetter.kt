package com.example.typingpractice.ui

import com.example.typingpractice.Check

data class CheckLetter(
    val text: String = "",
    var isTrue: Check = Check.NOTTRUEORFALSE
)
