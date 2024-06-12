package com.dam.MediTrackYou.controller

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.activity.ComponentActivity

class RegisterController {
    fun OpenDialog(context: Context, onDateSelected: (String) -> Unit) {
        val dpDialog = DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, day: Int ->
            onDateSelected("$year/${month + 1}/$day")
        }, 2024, 5, 17)

        dpDialog.show()
    }

    fun Close(context: Context) {
        if (context is ComponentActivity) {
            context.finish()
        }
    }
}