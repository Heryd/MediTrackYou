package com.dam.MediTrackYou.view.auth.register

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.MediTrackYou.R
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
        var typeOfUser = listOf("Seleccionar...", "Paciente", "Cuidador")
        var selectedOptionText by remember { mutableStateOf(typeOfUser[0]) }

        var checkTermsAndConditions by remember { mutableStateOf(false) }
        var passwordVisible by remember { mutableStateOf(false) }
        var confirmPasswordVisible by remember { mutableStateOf(false) }

        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            RowLayoutDniAndUsername(
                dni,
                onDniChange = { dni = it },
                username,
                onUsernameChange = { username = it })
            NameComponent(name, onNameChange = { name = it })
            RowLayoutEmailAndPhone(
                email,
                onEmailChange = { email = it },
                phone,
                onPhoneChange = { phone = it })
            BirthDateComponent(birthDate, onBirthDateChange = { birthDate = it }, context)
            TypeOfUserComponent(
                typeOfUser,
                selectedOptionText,
                onChangeOptionSelected = { selectedOptionText = it })
            PasswordComponent(
                password,
                onPasswordChange = { password = it },
                passwordVisible,
                onPasswordVisibilityChange = { passwordVisible = it })
            ConfirmPasswordComponent(
                confirmPassword,
                onConfirmPasswordChange = { confirmPassword = it },
                confirmPasswordVisible,
                onConfirmPasswordVisibilityChange = { confirmPasswordVisible = it },
            )

            TermsAndConditionsComponent(
                checkTermsAndConditions,
                onChecked = { checkTermsAndConditions = it })

            BottomApp(context, onClear = {
                dni = ""
                name = ""
                email = ""
                phone = ""
                username = ""
                password = ""
                confirmPassword = ""
                birthDate = ""
                checkTermsAndConditions = false
                passwordVisible = false
                confirmPasswordVisible = false
                selectedOptionText = typeOfUser.get(0)
            })
        }
    }

    @Composable
    fun RowLayoutDniAndUsername(
        dni: String,
        onDniChange: (String) -> Unit,
        username: String,
        onUsernameChange: (String) -> Unit
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            DniComponent(dni, onDniChange, Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp)) // Espacio entre los campos
            UsernameComponent(username, onUsernameChange, Modifier.weight(1f))
        }
    }

    @Composable
    fun RowLayoutEmailAndPhone(
        email: String,
        onEmailChange: (String) -> Unit,
        phone: String,
        onPhoneChange: (String) -> Unit
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            EmailComponent(email, onEmailChange, Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp)) // Espacio entre los campos
            PhoneComponent(phone, onPhoneChange, Modifier.weight(1f))
        }
    }

    @Composable
    fun DniComponent(dni: String, onDniChange: (String) -> Unit, modifier: Modifier = Modifier) {
        OutlinedTextField(
            value = dni,
            onValueChange = onDniChange,
            label = { Text("Cédula") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = modifier,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.id_card_24px),
                    contentDescription = "Icono de Cédula"
                )
            },
            maxLines = 1
        )
    }

    @Composable
    fun NameComponent(name: String, onNameChange: (String) -> Unit) {
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Nombres") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.description_24px),
                    contentDescription = "Icono de Candado"
                )
            },
            maxLines = 1
        )
    }

    @Composable
    fun EmailComponent(
        email: String,
        onEmailChange: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = modifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Icono de Correo Electrónico"
                )
            }
        )
    }

    @Composable
    fun PhoneComponent(
        phone: String,
        onPhoneChange: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        OutlinedTextField(
            value = phone,
            onValueChange = onPhoneChange,
            label = { Text("Teléfono") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = modifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Icono de Teléfono"
                )
            },
            maxLines = 1
        )
    }

    @Composable
    fun BirthDateComponent(
        birthDate: String,
        onBirthDateChange: (String) -> Unit,
        context: Context
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = birthDate,
                enabled = false,
                onValueChange = onBirthDateChange,
                label = { Text("Fecha de Nacimiento") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedTextColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 2.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Cake,
                        contentDescription = "Icono de Cumpleaños"
                    )
                },
            )
            IconButton(
                onClick = {
                    RegisterController().OpenDialog(
                        context
                    ) { date -> onBirthDateChange(date) }
                },
                modifier = Modifier
                    .weight(.2f)
                    .padding(start = 2.dp)
                    .align(Alignment.CenterVertically),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Icon(Icons.Default.DateRange, contentDescription = "Select Date")
            }
        }
    }

    @Composable
    fun UsernameComponent(
        username: String,
        onUsernameChange: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = onUsernameChange,
            label = { Text("Username") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = modifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Icono de Candado"
                )
            },
            maxLines = 1
        )
    }

    @Composable
    fun TypeOfUserComponent(
        typeOfUser: List<String>,
        selectedOptionText: String,
        onChangeOptionSelected: (String) -> Unit
    ) {
        OverFlowMenu({
            typeOfUser.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onChangeOptionSelected(option)
                    })
            }
        }, selectedOptionText)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun OverFlowMenu(
        context: @Composable () -> Unit,
        selectedOptionText: String,
    ) {
        var showMenu by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = showMenu,
            onExpandedChange = {
                showMenu = !showMenu
            }) {
            OutlinedTextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                label = { Text("Tipo de Usuario") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = showMenu
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                context()
            }
        }
    }


    @Composable
    fun PasswordComponent(
        password: String,
        onPasswordChange: (String) -> Unit,
        passwordVisible: Boolean,
        onPasswordVisibilityChange: (Boolean) -> Unit,
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = "Icono de Candado"
                )
            },
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = { onPasswordVisibilityChange(!passwordVisible) }) {
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
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
    }

    @Composable
    fun ConfirmPasswordComponent(
        confirmPassword: String,
        onConfirmPasswordChange: (String) -> Unit,
        confirmPasswordVisible: Boolean,
        onConfirmPasswordVisibilityChange: (Boolean) -> Unit,
    ) {
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = { Text("Confirmar Contraseña") },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = "Icono de Candado"
                )
            },
            trailingIcon = {
                val image = if (confirmPasswordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = { onConfirmPasswordVisibilityChange(!confirmPasswordVisible) }) {
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
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
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
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    @Composable
    fun BottomApp(context: Context, onClear: () -> Unit) {
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
                    onClick = onClear,
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