package com.dam.MediTrackYou.controller

import android.content.Context
import android.content.Intent
import com.dam.MediTrackYou.view.auth.login.LoginActivity

class AboutController {
    fun BackToLogin(context: Context) {
        val loginActivity = Intent(context, LoginActivity::class.java)
        context.startActivity(loginActivity, null)
    }
}