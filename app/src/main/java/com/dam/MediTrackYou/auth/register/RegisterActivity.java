package com.dam.MediTrackYou.auth.register;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dam.MediTrackYou.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private CheckBox terms_and_conditions;
    private EditText cedulaP, nombresP, apellidosP, edadP, fecha_nacimientoP,
            cedulaCui, nombresCui, apellidosCui, edadCui, emailCui, telefonoCui, clave, confirmaClave;
    private Spinner spinner_genero, spinner_nacionalidad;
    private ArrayAdapter<CharSequence> adapter;

    private Button boton_registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        asignarValores();
    }

    private void asignarValores() {
        //Button
        boton_registrar = findViewById(R.id.btn_Register);

        //CheckBox
        terms_and_conditions = findViewById(R.id.chb_terms_and_conditions);

    }

    private String obtenerDatos() {
        return "Datos del Paciente:\n" +
                "Cédula: " + cedulaP.getText().toString().trim() + ";" +
                "Nombre: " + nombresP.getText().toString().trim() + ";" +
                "Apellido: " + apellidosP.getText().toString().trim() + ";" +
                "Edad: " + edadP.getText().toString().trim() + ";" +
                "Fecha de Nacimiento: " + fecha_nacimientoP.getText().toString().trim() + ";" +
                "Género: " + spinner_genero.getSelectedItem().toString().equalsIgnoreCase("Seleccionar...") + ";" +
                "Nacionalidad: " + spinner_nacionalidad.getSelectedItem().toString().equalsIgnoreCase("Seleccionar...") + "\n\n" +
                "Datos del Cuidador:\n" +
                "Cédula: " + cedulaCui.getText().toString().trim() + ";" +
                "Nombres: " + nombresCui.getText().toString().trim() + ";" +
                "Apellidos: " + apellidosCui.getText().toString().trim() + ";" +
                "Edad: " + edadCui.getText().toString().trim() + ";" +
                "Email: " + emailCui.getText().toString().trim() + ";" +
                "Teléfono: " + telefonoCui.getText().toString().trim() + ";" +
                "Clave: " + clave.getText().toString().trim() + "\n\n";

    }

    //Method for clearing user-filled fields as well as selected views
    public void cleanFields(View v) {

        terms_and_conditions.setChecked(false);
    }

    public void openDialog(View v) {
        DatePickerDialog dp_Dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                fecha_nacimientoP.setText(String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day));
            }
        }, 2024, 5, 17);

        dp_Dialog.show();
    }

    public void close(View v) {
        finish();
    }

}