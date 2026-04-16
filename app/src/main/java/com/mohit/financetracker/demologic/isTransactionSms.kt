package com.mohit.financetracker.demologic

fun isTransactionSms(message: String): Boolean {

    val keywords = listOf(
        "debited", "credited", "upi", "spent", "sent", "received"
    )

    return keywords.any { message.contains(it, ignoreCase = true) }
}