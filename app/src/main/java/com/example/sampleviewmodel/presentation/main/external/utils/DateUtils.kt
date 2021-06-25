/**
 * News Android App
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 13/06/21 23.44 PM
 * Last modified 13/06/21 23.44 PM by Nurholis
 */
package com.example.sampleviewmodel.presentation.main.external.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import com.example.sampleviewmodel.presentation.main.external.utils.extention.Callback
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
     private val format_date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

    @SuppressLint("NewApi")
    fun showDatePicker(context: Context, date: Date?, callback: Callback) {
        val calender = Calendar.getInstance()
        date?.let {
            calender.time = it
        } ?: kotlin.run {
           val text = "Date null"
            println(text)
        }

        val dialog = DatePickerDialog (
            context, { _, year, month, dayOfMonth ->
               val c = Calendar.getInstance()
               c.set(year, month, dayOfMonth)
               callback.onDateSelected(c.time)
            },
           calender.get(Calendar.YEAR),
           calender.get(Calendar.MONTH),
           calender.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()
    }

    fun parseDate(
        date: String?,
        format: SimpleDateFormat
    ): Date? {
        return when (date) {
            null -> null
            else -> try {
                format.parse(date)
            } catch (e: Exception) {
                null
            }
        }
    }

    fun formatToDate(date: Date?): String {
       return if (date == null) ""
       else format_date.format(date)
    }

}