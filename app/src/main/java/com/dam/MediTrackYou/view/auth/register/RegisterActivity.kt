package com.dam.MediTrackYou.view.auth.register

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.MediTrackYou.controller.RegisterController
import com.dam.MediTrackYou.ui.theme.MediTrackYouTheme

class RegisterActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediTrackYouTheme {
                RegisterScreen()
            }
        }
    }

    @Composable
    fun RegisterScreen() {
        val context = LocalContext.current
        HeaderApp()
        BodyApp(context)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HeaderApp() {
        Column {
            TopAppBar(
                title = {
                    Text(
                        text = "Nuevo Usuario",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onBackground)
            )
        }
    }

    @Composable
    fun BodyApp(context: Context) {
        var dni by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var birthDate by remember { mutableStateOf("") }

        var checkTermsAndConditions by remember { mutableStateOf(false) }
        var passwordVisible by remember { mutableStateOf(false) }
        var confirmPasswordVisible by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DniComponent(dni, onDniChange = { dni = it })
            NameComponent(name, onNameChange = { name = it })
            EmailComponent(email, onEmailChange = { email = it })
            PhoneComponent(phone, onPhoneChange = { phone = it })
            UsernameComponent(username, onUsernameChange = { username = it })
            PasswordComponent(password, onPasswordChange = { password = it }, passwordVisible)
            ConfirmPasswordComponent(
                confirmPassword,
                onConfirmPasswordChange = { confirmPassword = it },
                confirmPasswordVisible
            )

            TermsAndConditionsComponent(
                checkTermsAndConditions,
                onChecked = { checkTermsAndConditions = it })

            BottomApp(context)
        }
    }

    @Composable
    fun DniComponent(dni: String, onDniChange: (String) -> Unit) {
        OutlinedTextField(
            value = dni,
            onValueChange = { onDniChange },
            label = { Text("Cédula") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun NameComponent(name: String, onNameChange: (String) -> Unit) {
        OutlinedTextField(
            value = name,
            onValueChange = { onNameChange },
            label = { Text("Nombres") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun EmailComponent(email: String, onEmailChange: (String) -> Unit) {
        OutlinedTextField(
            value = email,
            onValueChange = { onEmailChange },
            label = { Text("Email") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun PhoneComponent(phone: String, onPhoneChange: (String) -> Unit) {
        OutlinedTextField(
            value = phone,
            onValueChange = { onPhoneChange },
            label = { Text("Teléfono") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun UsernameComponent(username: String, onUsernameChange: (String) -> Unit) {
        OutlinedTextField(
            value = username,
            onValueChange = { onUsernameChange },
            label = { Text("Username") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun PasswordComponent(
        password: String,
        onPasswordChange: (String) -> Unit,
        passwordVisible: Boolean
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = { onPasswordChange },
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = { !passwordVisible }) {
                    Icon(
                        imageVector = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun ConfirmPasswordComponent(
        confirmPassword: String,
        onConfirmPasswordChange: (String) -> Unit, confirmPasswordVisible: Boolean
    ) {
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword },
            label = { Text("Confirmar Contraseña") },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (confirmPasswordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = { !confirmPasswordVisible }) {
                    Icon(
                        imageVector = image,
                        contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun TermsAndConditionsComponent(termsAndConditions: Boolean, onChecked: (Boolean) -> Unit) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Checkbox(
                checked = termsAndConditions,
                onCheckedChange = onChecked,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
            Text(
                text = "Aceptar los Términos y Condiciones",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    @Composable
    fun BottomApp(context: Context) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp)
            ) {
                Text(text = "Crear Cuenta")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 2.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                ) {
                    Text(text = "Borrar")
                }
                Button(
                    onClick = { RegisterController().Close(context) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 2.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                ) {
                    Text(text = "Cancelar")
                }
            }
        }
    }

    @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
    @Composable
    fun PreviewRegisterScreen() {
        RegisterScreen()
    }
}