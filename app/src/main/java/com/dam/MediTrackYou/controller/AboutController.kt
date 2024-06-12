package com.dam.MediTrackYou.controller

import android.content.Context
import android.content.Intent
import com.dam.MediTrackYou.view.about.AcercadeActivity

class AboutController {
    fun BackToLogin(context: Context) {
        val registerView = Intent(context, AcercadeActivity::class.java)
        context.startActivity(registerView, null)
    }
}