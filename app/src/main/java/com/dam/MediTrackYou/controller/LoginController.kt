package com.dam.MediTrackYou.controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.dam.MediTrackYou.view.auth.register.RegisterActivity;

open class LoginController {
    fun goToRegister(context: Context) {
        val registerView = Intent(context, RegisterActivity::class.java)
        context.startActivity(registerView, null);
    }
}