package com.mohit.financetracker.demologic

import android.content.Context
import android.net.Uri
import android.util.Log

fun readSms(context: Context) {

    val uri = Uri.parse("content://sms/inbox")
    val cursor = context.contentResolver.query(uri, null, null, null, null)

    cursor?.use {

        val bodyIndex = it.getColumnIndex("body")
        val dateIndex = it.getColumnIndex("date")

        while (it.moveToNext()) {

            val message = it.getString(bodyIndex) ?: continue
            val date = it.getLong(dateIndex)

            // STEP 1: FILTER
            if (!isTransactionSms(message)) continue

            // STEP 2: EXTRACT
            val parsed = parseSms(message) ?: continue

            // STEP 3: PRINT (for now)
            Log.d("SMS_DATA", "Amount: ${parsed.amount}, Type: ${parsed.type}")
        }
    }
}