package com.mohit.financetracker.demologic

import android.content.Context
import android.util.Log
import androidx.core.net.toUri

fun readSms(context: Context) {

    val uri = "content://sms/inbox".toUri()

    val cursor = context.contentResolver.query(
        uri,
        null,
        null,
        null,
        "date DESC"
    )

    val transactionList = mutableListOf<Pair<ParsedSms, Long>>()

    cursor?.use {
        val bodyIndex = it.getColumnIndex("body")
        val dateIndex = it.getColumnIndex("date")
        val addressIndex = it.getColumnIndex("address")

        while (it.moveToNext()) {

            val message = it.getString(bodyIndex) ?: continue
            val sender = it.getString(addressIndex) ?: ""

            // 🔥 FILTER
            if (!isValidTransaction(sender, message)) continue

            // 🔥 PARSE
            val parsed = parseSms(message) ?: continue

            val date = it.getLong(dateIndex)

            // ✅ ADD TO LIST (YOU MISSED THIS)
            transactionList.add(parsed to date)

            // ✅ LOG VALID TRANSACTION
            Log.d(
                "SMS_TXN",
                """
                💰 ${parsed.amount} | ${parsed.type}
                🧾 ${parsed.merchant ?: "Unknown"}
                🏦 ${parsed.account ?: "N/A"}
                📅 ${parsed.date ?: "N/A"}
                📩 $sender
                -------------------------------
                """.trimIndent()
            )
        }
    }

    // -----------------------------
    // 🔥 CALCULATE AFTER LOOP
    // -----------------------------

    val today = getTodayRange()
    val month = getMonthRange()

    val todayDebitTotal = getTotalDebitAmount(
        transactionList,
        today.start,
        today.end
    )
    val monthDebitTotal = getTotalDebitAmount(
        transactionList,
        month.start,
        month.end
    )
    val todayCreditTotal = getTotalCreditAmount(
        transactionList,
        today.start,
        today.end
    )
    val monthCreditTotal = getTotalCreditAmount(
        transactionList,
        month.start,
        month.end
    )


    Log.d("RESULT", "Today Spent: ₹$todayDebitTotal")
    Log.d("RESULT", "Month Spent: ₹$monthDebitTotal")

    Log.d("RESULT", "Today Credit: ₹$todayCreditTotal")
    Log.d("RESULT", "Month Credit: ₹$monthCreditTotal")
}