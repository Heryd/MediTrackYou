package com.dam.MediTrackYou.controller

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.dam.MediTrackYou.model.User
import com.dam.MediTrackYou.model.database.BDMediTrackYou
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegisterController {
    fun OpenDialog(context: Context, onDateSelected: (String) -> Unit) {
        val dpDialog = DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, day: Int ->
            onDateSelected("$year/${month + 1}/$day")
        }, 2024, 6, 12)
        dpDialog.show()
    }

    fun Close(context: Context) {
        if (context is ComponentActivity) {
            context.finish()
        }
    }

    private fun validateEmptyFields(user: User): Int {
        if (user.user.isNullOrEmpty() ||
            user.name.isNullOrEmpty() ||
            user.password.isNullOrEmpty() ||
            user.dni.toString().length <= 0 ||
            user.email.isNullOrEmpty() ||
            user.birth_date.isNullOrEmpty() ||
            user.gender.isNullOrEmpty() ||
            user.phone.toString().length <= 0
        ) {
            return 1
        }
        return -1
    }

    private fun calculateCurrentAge(birthDate: String): Int {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val birth = dateFormat.parse(birthDate)
        val today = Calendar.getInstance().time

        val diferenteBetween = today.time - birth!!.time
        val age = diferenteBetween / (1000L * 60 * 60 * 24 * 365)

        return age.toInt()
    }

    private fun isCheckTermsAndConditions(context: Context, isCheck: Boolean): Boolean {
        return when (isCheck) {
            true -> true
            else -> false
        }
    }

    fun Register(context: Context, user: User, isCheck: Boolean) {
        when (isCheckTermsAndConditions(context, isCheck)) {
            true -> {
                when (validateEmptyFields(user)) {
                    -1 -> {
                        when (BDMediTrackYou(context).findUserExists("", "")) {
                            true -> {}
                            else -> {
                                Toast.makeText(
                                    context,
                                    "El usuario $user ya se encuentra registrado",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        }
                    }

                    else -> {
                        Toast.makeText(context, "Faltan campos por llenar", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

            else -> {
                Toast.makeText(
                    context,
                    "No ha aceptado los Términos y Condiciones",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}