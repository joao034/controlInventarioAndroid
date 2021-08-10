package com.example.controlinventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DescriptionActivity extends AppCompatActivity {

    private static final String TAG = "DescriptionActivity";
    private List<Activo> listaActivos;
    RecyclerView recyclerView;
    RequestQueue queue1;
    ProcesoValidacionDetalle element;
    Button btnGuardar;
    TextView tituloDescripcion;
    TextView nombreFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        element = (ProcesoValidacionDetalle) getIntent().getSerializableExtra("ProcesoValidacionDetalle");
        tituloDescripcion = findViewById(R.id.tituloDescripcion);
        nombreFuncionario = findViewById(R.id.nombreFuncionario);
        btnGuardar = findViewById(R.id.btnGuardar);

        nombreFuncionario.setText("Funcionario: " + element.getFuncionario().toString());
        cargarActivos(element.getFuncionario().getId());

    }

    public void cargarActivos(String idFuncionario){
        String URL = "http://192.168.100.123/servicios/cargarActivosDeFuncionario.php?funcionario=" + idFuncionario;
        //String URL = "http://192.168.100.3/servicios/cargarActivosDeFuncionario.php?funcionario=" + idFuncionario;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ActivoDAO daoActivos = new ActivoDAO();
                //lleno la listaActivos
                listaActivos = daoActivos.listarActivos(response);
                //Crea el adapter pasandole la lista de activos
                AdapterActivos adapterActivos = new AdapterActivos(DescriptionActivity.this, listaActivos );
                recyclerView = findViewById(R.id.res_activos);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(DescriptionActivity.this));
                recyclerView.setAdapter(adapterActivos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DescriptionActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue1 = Volley.newRequestQueue(this);
        queue1.add(stringRequest);
    }

    public void guardarProceso(View view){
        String idFuncionario = element.getFuncionario().getId();
        TextInputEditText observacion = findViewById(R.id.observacion);
        String obs = observacion.getText().toString();
        List<Activo> activosDetalle = listaActivos;
        recyclerView.getAdapter().





        Toast.makeText(DescriptionActivity.this, "Revisado Correctamente", Toast.LENGTH_SHORT).show();
    }


}