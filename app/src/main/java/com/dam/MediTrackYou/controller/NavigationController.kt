package com.dam.MediTrackYou.controller

import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.DeviceUnknown
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.dam.MediTrackYou.R
import com.dam.MediTrackYou.view.about.AcercadeActivity
import com.dam.MediTrackYou.view.home.MainActivity
import com.dam.MediTrackYou.view.modules.CalendarActivity
import com.dam.MediTrackYou.view.modules.NearYouActivity
import com.dam.MediTrackYou.view.modules.PillsActivity

class NavigationController {
    @Composable
    fun SetMenuItemIcon(option: String) {
        return when (option) {
            "Calendario" -> {
                Icon(Icons.Outlined.CalendarMonth, contentDescription = "Calendario")
            }

            "Medicación" -> {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.prescriptions_24px),
                    contentDescription = "Medicinas"
                )
            }

            "Farmacias cerca" -> {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.explore_nearby_24px),
                    contentDescription = "Farmacias Cercanas"
                )
            }

            "Acerca de" -> {
                Icon(Icons.Outlined.Info, contentDescription = "Acerca de")
            }

            "Reportar" -> {
                Icon(Icons.Outlined.Flag, contentDescription = "Reportar Contenido")
            }

            else -> {
                Icon(Icons.Outlined.DeviceUnknown, contentDescription = "Sin Descripción")
            }
        }
    }

    fun GoToScreen(context: Context, option: String) {
        var goToActivity: Intent? = null
        when (option) {
            "Calendario" -> {
                goToActivity = Intent(context, CalendarActivity::class.java)
            }

            "Medicación" -> {
                goToActivity = Intent(context, PillsActivity::class.java)
            }

            "Farmacias cerca" -> {

                goToActivity = Intent(context, NearYouActivity::class.java)
            }

            "Acerca de" -> {
                goToActivity = Intent(context, AcercadeActivity::class.java)
            }

            else -> {
                goToActivity = Intent(context, MainActivity::class.java)
            }
        }
        context.startActivity(goToActivity)
    }
}