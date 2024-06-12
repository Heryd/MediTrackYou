package com.dam.MediTrackYou

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.MediTrackYou.ui.theme.MediTrackYouTheme
import com.dam.MediTrackYou.view.about.AcercadeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediTrackYouTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MainText()
        }
    }

    @Composable
    fun MainText() {
        Text(
            text = "Bienvenido Usuario! :D",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

    @Preview()
    @Composable
    fun PreviewMainScreen() {
        MainScreen()
    }

    //Tarea 1 - CREACION DE MENU y LLAMADAS A OTRAS PANTALLAS
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuPrincipal: MenuInflater = menuInflater
        menuPrincipal.inflate(R.menu.menuprincipal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemAcercade -> {
                val ventanaAcercade = Intent(this, AcercadeActivity::class.java)
                startActivity(ventanaAcercade)
                return true
            }

            R.id.itemConsultarDatos -> {
                val ventanaConsultarDatos = Intent(this, ConsultarDatosActivity::class.java)
                startActivity(ventanaConsultarDatos)
                return true
            }
        }
        return true
    }

}