package com.mohit.financetracker.demologic

fun getTotalCreditAmount(
    transactions: List<Pair<ParsedSms, Long>>,
    start: Long,
    end: Long
): Double {
    return transactions
        .filter { it.first.type == "credit" && it.second in start..end }
        .sumOf { it.first.amount }
}