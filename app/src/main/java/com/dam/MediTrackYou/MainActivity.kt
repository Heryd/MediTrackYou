package com.dam.MediTrackYou

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dam.MediTrackYou.AcercadeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //Tarea 1 - CREACION DE MENU y LLAMADAS A OTRAS PANTALLAS
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuPrincipal: MenuInflater = menuInflater
        menuPrincipal.inflate(R.menu.menuprincipal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
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