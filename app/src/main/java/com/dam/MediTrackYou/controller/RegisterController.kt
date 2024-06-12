package com.dam.MediTrackYou.controller

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import com.dam.MediTrackYou.DatePickerFragment

class RegisterController {
    fun OpenDialog(context: Context, fecha_nacimiento: String) {

    }

    fun Close(context: Context) {
        if (context is ComponentActivity) {
            context.finish()
        }
    }
}