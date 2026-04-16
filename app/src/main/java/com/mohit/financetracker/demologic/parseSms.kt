package com.mohit.financetracker.demologic

fun parseSms(message: String): ParsedSms? {

    val amountRegex = Regex("(Rs\\.?|INR)\\s?(\\d+(\\.\\d{1,2})?)")
    val amount = amountRegex.find(message)
        ?.groups?.get(2)?.value?.toDoubleOrNull()

    val type = when {
        message.contains("debited", true) -> "debit"
        message.contains("sent", true) -> "debit"
        message.contains("paid", true) -> "debit"
        message.contains("credited", true) -> "credit"
        message.contains("received", true) -> "credit"
        else -> "unknown"
    }

    val accountRegex = Regex("(A/c|AC)\\s?X?(\\d{4})")
    val account = accountRegex.find(message)
        ?.groups?.get(2)?.value

    val merchant = Regex("to\\s([A-Za-z0-9@. ]+)")
        .find(message)?.groups?.get(1)?.value
        ?: Regex("payment to\\s([A-Za-z ]+)")
            .find(message)?.groups?.get(1)?.value

    val dateRegex = Regex("(\\d{2}[-/][A-Za-z0-9]{2,3}[-/][0-9]{2})")
    val date = dateRegex.find(message)
        ?.groups?.get(1)?.value

    if (amount == null || type == "unknown") return null

    return ParsedSms(amount, type, merchant, account, date)
}