package com.mohit.financetracker.demologic

import android.content.Context
import android.net.Uri
import android.util.Log

fun readSms(context: Context) {

    val uri = Uri.parse("content://sms/inbox")
    val cursor = context.contentResolver.query(uri, null, null, null, null)

    cursor?.use {

        while (it.moveToNext()) {

            val bodyIndex = it.getColumnIndex("body")
            val dateIndex = it.getColumnIndex("date")
            val addressIndex = it.getColumnIndex("address")

            val message = it.getString(bodyIndex) ?: continue
            val sender = it.getString(addressIndex) ?: ""
            val date = it.getLong(dateIndex)

            // 🔥 FILTER HERE
            if (!isValidTransaction(sender, message)) {
                Log.d("SMS_SKIPPED", "Ignored: [$sender] $message")
                continue
            }

            // 🧩 PARSE
            val parsed = parseSms(message)

            if (parsed == null) {
                Log.e("SMS_FAILED", "Failed: [$sender] $message")
                continue
            }

            // STEP 3: PRINT (for now)
            Log.d(
                "SMS_DEBUG",
                """
                        -------------------------------
                        📩 ORIGINAL SMS:
                        $message
                    
                        💰 Amount: ${parsed.amount}
                        🔁 Type: ${parsed.type}
                        🧾 Merchant: ${parsed.merchant}
                        🏦 Account: ${parsed.account}
                        📅 Date: ${parsed.date}
                    
                        ⏱ Timestamp: $date
                        -------------------------------
                        """.trimIndent()
            )
        }
    }
}