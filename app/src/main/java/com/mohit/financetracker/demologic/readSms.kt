package com.mohit.financetracker.demologic

import android.content.Context
import android.util.Log
import androidx.core.net.toUri

fun readSms(context: Context) {

    val uri = "content://sms/inbox".toUri()
    val start = getStartOfDayMillis()
    val end = System.currentTimeMillis()

    val cursor = context.contentResolver.query(
        uri,
        null,
        null,
        null,
        "date DESC"
    )

    cursor?.use {
        val bodyIndex = it.getColumnIndex("body")
        val dateIndex = it.getColumnIndex("date")
        val addressIndex = it.getColumnIndex("address")

        while (it.moveToNext()) {

            val message = it.getString(bodyIndex) ?: continue
            val sender = it.getString(addressIndex) ?: ""
            val date = it.getLong(dateIndex)

            // 🔥 FILTER (hard gate)
            if (!isValidTransaction(sender, message, date, start, end)) {
                continue
            }

            // 🧩 PARSE (hard gate)
            val parsed = parseSms(message) ?: continue

            // ✅ ONLY SUCCESS LOG
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
}