package com.mohit.financetracker.demologic

val financialSenders = listOf(
    "SBI", "SBIN",
    "HDFC", "HDFCBK",
    "ICICI",
    "KOTAK",
    "MAHABK",
    "PNB",
    "AXIS",
    "YESBANK",
    "BOB", "BARODA",
    "GPAY", "PHONEPE", "PAYTM"
)

fun isBankSender(sender: String): Boolean {
    return financialSenders.any { sender.contains(it, ignoreCase = true) }
}

fun isTransactionSms(message: String): Boolean {
    val keywords = listOf(
        "debited", "credited",
        "upi", "sent", "received",
        "spent", "payment"
    )
    return keywords.any { message.contains(it, ignoreCase = true) }
}

fun isValidTransaction(sender: String, message: String): Boolean {
    return isBankSender(sender) && isTransactionSms(message)
}