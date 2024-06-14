package com.dam.MediTrackYou.controller

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import com.dam.MediTrackYou.view.auth.login.LoginActivity

class AboutController {
    fun Back(context: Context) {
        if (context is ComponentActivity) {
            context.finish()
        }
    }
}