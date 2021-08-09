package com.example.controlinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    private static final String TAG = "DescriptionActivity";

    TextView tituloDescripcion;
    TextView nombreFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        ProcesoValidacionDetalle element = (ProcesoValidacionDetalle) getIntent().getSerializableExtra("ProcesoValidacionDetalle");
        tituloDescripcion = findViewById(R.id.tituloDescripcion);
        nombreFuncionario = findViewById(R.id.nombreFuncionario);

        nombreFuncionario.setText("Funcionario: " + element.getFuncionario().toString());

    }
}