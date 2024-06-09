package com.dam.MediTrackYou;

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
        //EditText Datos Paciente
        cedulaP = findViewById(R.id.txt_CedulaPaciente);
        nombresP = findViewById(R.id.txt_NombrePaciente);
        apellidosP = findViewById(R.id.txt_ApellidoPaciente);
        edadP = findViewById(R.id.txt_EdadPaciente);
        fecha_nacimientoP = findViewById(R.id.txt_FechaNacimientoPaciente);

        //EditText Datos Cuidador
        cedulaCui = findViewById(R.id.txt_CedulaCuidador);
        nombresCui = findViewById(R.id.txt_NombresCuidador);
        apellidosCui = findViewById(R.id.txt_ApellidosCuidador);
        edadCui = findViewById(R.id.txt_EdadCuidador);
        emailCui = findViewById(R.id.txt_EmailCui);
        telefonoCui = findViewById(R.id.txt_PhoneCui);
        clave = findViewById(R.id.txt_Clave);

        //Spinner
        spinner_genero = findViewById(R.id.spn_Gender);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_genero.setAdapter(adapter);
        spinner_nacionalidad = findViewById(R.id.spn_Nationality);

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

    //Check the status of the SD card and save the field data to the external memory.
    private int verifyStatusSD() {
        String status = Environment.getExternalStorageState();
        switch (status) {
            case Environment.MEDIA_MOUNTED:
                Toast.makeText(this, "Memoria montada correctamente", Toast.LENGTH_LONG).show();
                return 0;
            case Environment.MEDIA_MOUNTED_READ_ONLY:
                Toast.makeText(this, "Memoria solo de Lectura", Toast.LENGTH_LONG).show();
                return 1;
            default:
                Toast.makeText(this, "Error de Memoria, no se puede guardar", Toast.LENGTH_LONG).show();
                return 2;
        }
    }

    private boolean saveDataOnSD(String data) {
        try {
            OutputStreamWriter out;
            out = new OutputStreamWriter(new FileOutputStream(
                    new File(getExternalFilesDir(null),
                            "Registro_Usuario.txt"), true));
            out.write(data);
            out.close();
            return true;
        } catch (Exception e) {
            Log.e("SaveData_on_SD", "Error al guardar en SD");
            return false;
        }
    }

    //Method for clearing user-filled fields as well as selected views
    public void cleanFields(View v) {

        // EditTexts del paciente
        cedulaP.setText("");
        nombresP.setText("");
        apellidosP.setText("");
        edadP.setText("");
        fecha_nacimientoP.setText("");
        spinner_genero.setSelection(0);
        spinner_nacionalidad.setSelection(0);

        // EditTexts del cuidador
        cedulaCui.setText("");
        nombresCui.setText("");
        apellidosCui.setText("");
        edadCui.setText("");
        emailCui.setText("");
        telefonoCui.setText("");
        clave.setText("");

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