package com.mohit.financetracker.demologic

fun parseSms(message: String): ParsedSms? {

    // Extract Amount
    val amountRegex = Regex("(Rs\\.?|INR)\\s?(\\d+)")
    val amountMatch = amountRegex.find(message)
    val amount = amountMatch?.groups?.get(2)?.value?.toDoubleOrNull()

    // Detect Type
    val type = when {
        message.contains("debited", true) -> "debit"
        message.contains("spent", true) -> "debit"
        message.contains("sent", true) -> "debit"

        message.contains("credited", true) -> "credit"
        message.contains("received", true) -> "credit"

        else -> "unknown"
    }

    // VALIDATION
    if (amount == null || type == "unknown") return null

    return ParsedSms(amount, type)
}