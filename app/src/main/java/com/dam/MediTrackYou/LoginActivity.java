package com.dam.MediTrackYou;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    private EditText user;
    private EditText password;
    private final String userVault = "Grupo4_DAM";
    private final String passwordVault = "desarrollomovil";

    private CheckBox mantenerSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        user = findViewById(R.id.txt_user);
        password = findViewById(R.id.txt_password);

        mantenerSesion = findViewById(R.id.chkMantenerSesion);
        cargarCredenciales();
    }
    public String getPasswordVault() {
        return passwordVault;
    }
    public String getUserVault() {
        return userVault;
    }
    private void cargarCredenciales() {
        SharedPreferences shpLogin = getSharedPreferences("CredencialAcceso", Context.MODE_PRIVATE);
        String user_tmp = shpLogin.getString("userSHP", "");
        String password_tmp = shpLogin.getString("passwordSHP", "");
        user.setText(user_tmp);
        password.setText(password_tmp);
        if (user_tmp != "") {
            mantenerSesion.setChecked(true);
        } else {
            mantenerSesion.setChecked(false);
        }
    }
    private void guardarCredenciales(String user, String password) {
        SharedPreferences shpLogin = getSharedPreferences("CredencialAcceso", Context.MODE_PRIVATE);
        SharedPreferences.Editor shpLoginEdit = shpLogin.edit();
        shpLoginEdit.putString("userSHP", user);
        shpLoginEdit.putString("passwordSHP", password);
        shpLoginEdit.commit();
    }
    protected Boolean validateCredentials(Editable userInput, Editable passwordInput) {
        return (userInput.toString().trim().equals(getUserVault()) && passwordInput.toString().trim().equals(getPasswordVault()));
    }
    public void login(View v) {
        if (mantenerSesion.isChecked()) {
            guardarCredenciales(String.valueOf(user.getText()), String.valueOf(password.getText()));
        }

        if (validateCredentials(user.getText(), password.getText())) {
            Toast.makeText(v.getContext(), "Acceso Concedido al Usuario " + user.getText(), Toast.LENGTH_LONG).show();
            Intent mainScreen = new Intent(v.getContext(), MainActivity.class);
            startActivity(mainScreen);

        } else {
            Toast.makeText(v.getContext(), "Datos Incorrectos", Toast.LENGTH_LONG).show();
        }
    }

    public void register(View v) {
        try {
            Intent ventanaRegistro = new Intent(v.getContext(), RegisterActivity.class);
            startActivity(ventanaRegistro);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            Toast.makeText(v.getContext(), "Ha ocurrido un error", Toast.LENGTH_LONG).show();
        }
    }


}