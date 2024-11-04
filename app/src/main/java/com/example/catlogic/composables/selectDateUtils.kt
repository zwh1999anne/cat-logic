package com.example.catlogic.composables

import android.app.DatePickerDialog
import android.content.Context
import java.util.Calendar


fun DatePickerDialog(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val dateString = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            onDateSelected(dateString)
        },
        year,
        month,
        day
    ).show()
}