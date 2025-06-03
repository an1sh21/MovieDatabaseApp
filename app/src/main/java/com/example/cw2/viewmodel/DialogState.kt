package com.example.cw2.viewmodel

data class DialogState(
    val show: Boolean = false,
    val message: String? = null,
    val isError: Boolean = false
)