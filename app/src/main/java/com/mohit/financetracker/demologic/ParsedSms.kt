package com.mohit.financetracker.demologic

data class ParsedSms(
    val amount: Double,
    val type: String,
    val merchant: String?,
    val account: String?,
    val date: String?
)