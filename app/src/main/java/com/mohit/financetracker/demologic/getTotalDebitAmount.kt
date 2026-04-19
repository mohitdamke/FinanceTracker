package com.mohit.financetracker.demologic

fun getTotalDebitAmount(
    transactions: List<Pair<ParsedSms, Long>>,
    start: Long,
    end: Long
): Double {
    return transactions
        .filter { it.first.type == "debit" && it.second in start..end }
        .sumOf { it.first.amount }
}