package com.dam.MediTrackYou.controller

import android.content.Context
import android.content.Intent
import android.net.wifi.hotspot2.pps.HomeSp
import com.dam.MediTrackYou.view.about.AcercadeActivity
import com.dam.MediTrackYou.view.auth.register.RegisterActivity
import com.dam.MediTrackYou.view.home.MainActivity

open class LoginController {
    fun goToRegister(context: Context) {
        val registerView = Intent(context, RegisterActivity::class.java)
        context.startActivity(registerView, null)
    }

    fun login(username: String, password: String, keepSession: Boolean, context: Context) {
//        if (keepSession) {
//            saveCredentials(username, password, context)
//        }
//        when (validateCredentials()) {
//            true -> Toast.makeText(
//                context,
//                "Acceso Concedido al Usuario $username",
//                Toast.LENGTH_LONG
//            ).show()
//
//            else -> Toast.makeText(context, "Datos Incorrectos", Toast.LENGTH_LONG).show()
//
//        }
        val activity = Intent(context, MainActivity::class.java)
        context.startActivity(activity, null)
    }

    private fun saveCredentials(user: String, password: String, context: Context) {
        val shpLogin = context.getSharedPreferences("CredencialAcceso", Context.MODE_PRIVATE)
        val shpLoginEdit = shpLogin.edit()
        shpLoginEdit.putString("userSHP", user)
        shpLoginEdit.putString("passwordSHP", password)
        shpLoginEdit.apply()
    }

    private fun validateCredentials(): Boolean {
        return true
    }
}