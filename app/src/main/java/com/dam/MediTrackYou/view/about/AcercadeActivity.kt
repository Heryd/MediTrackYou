package com.dam.MediTrackYou.view.about

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.MediTrackYou.R
import com.dam.MediTrackYou.ui.theme.MediTrackYouTheme
import com.dam.MediTrackYou.controller.AboutController

class AcercadeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediTrackYouTheme {
                AboutScreen()
            }
        }
    }

    @Composable
    fun AboutScreen() {
        val context = LocalContext.current
        Body(context)
    }

    @Composable
    fun Body(context: Context) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .pointerInput(Unit) { detectTapGestures { currentFocus?.clearFocus() } },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            InformationGroup()
            ImageGroup()
            ButtonBack(context)
        }
    }

    @Composable
    fun InformationGroup() {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Evidencia Grupo #4",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "- COLOMA GUZMAN JOHN STEVEN",
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "- MORLA GORDILLO HERYD XAVIER",
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "- TOMALA MAGALLANES KEVIN SAUL",
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "DESARROLLO DE APLICACIONES MOVILES - SOF-S-MA-2",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }

    @Composable
    fun ImageGroup() {
        Image(
            painter = painterResource(id = R.mipmap.fotogrupo4),
            contentDescription = "Foto en Zoom del Grupo 4",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(500.dp)
        )
    }

    @Composable
    fun ButtonBack(context: Context) {
        Button(
            onClick = { AboutController().BackToLogin(context) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Regresar",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    @Preview
    @Composable
    fun PreviewAboutScreen() {
        AboutScreen()
    }

}