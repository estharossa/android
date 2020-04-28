package com.android.translator.model

data class Word(
    val code: Int,
    val lang: String,
    val text: List<String>
)