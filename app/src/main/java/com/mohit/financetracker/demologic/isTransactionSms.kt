package com.mohit.financetracker.demologic

// -----------------------------
// 🔹 Sender List
// -----------------------------
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

// -----------------------------
// 🔹 Filters
// -----------------------------
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

fun isInDateRange(date: Long, start: Long, end: Long): Boolean {
    return date in start..end
}

fun isValidTransaction(
    sender: String,
    message: String,
    date: Long,
    start: Long,
    end: Long
): Boolean {
    return isBankSender(sender) &&
            isTransactionSms(message) &&
            isInDateRange(date, start, end)
}