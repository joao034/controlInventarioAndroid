package com.example.controlinventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity {

    private static final String TAG = "DescriptionActivity";
    public static List<Activo> listaActivos;
    RecyclerView recyclerView;
    RequestQueue queue1, queue2;
    static RequestQueue queue;
    static ProcesoValidacionDetalle element;
    Button btnGuardar, btnScan;
    TextView tituloDescripcion;
    TextView nombreFuncionario;
    AdapterActivos adapterActivos;
    TextInputEditText observacion;
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        element = (ProcesoValidacionDetalle) getIntent().getSerializableExtra("ProcesoValidacionDetalle");
        tituloDescripcion = findViewById(R.id.tituloDescripcion);
        nombreFuncionario = findViewById(R.id.nombreFuncionario);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnScan = findViewById(R.id.btnScan);
        observacion = findViewById(R.id.observacion);
        checkbox = findViewById(R.id.checkBox);

        nombreFuncionario.setText("Funcionario: " + element.getFuncionario().toString());
        cargarActivos(element.getFuncionario().getId());
        if (!element.getObservacion().equals("NULL"))
            observacion.setText(element.getObservacion());
        if (element.getEstado().equalsIgnoreCase("FINALIZADO"))
            checkbox.setChecked(true);
    }

    public List<Activo> cargarActivos(String idFuncionario){
        //String URL = "http://192.168.100.123/servicios/cargarActivosDeFuncionario.php?funcionario=" + idFuncionario;
        String URL = "http://192.168.2.111/servicios/cargarActivosDeFuncionario.php?funcionario=" + idFuncionario;
        //String URL = "http://192.168.100.3/servicios/cargarActivosDeFuncionario.php?funcionario=" + idFuncionario;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ActivoDAO daoActivos = new ActivoDAO();
                //lleno la listaActivos
                listaActivos = daoActivos.listarActivos(response);
                //Crea el adapter pasandole la lista de activos
                adapterActivos = new AdapterActivos(DescriptionActivity.this, listaActivos );
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
        return listaActivos;
    }

    public void guardarProceso(View view){
        String idProceso = String.valueOf(element.getId());
        String obs = observacion.getText().toString();
        String estado;
        if (checkbox.isChecked())
            estado = "FINALIZADO";
        else
            estado = "EN CURSO";

        /*String URL = "http://192.168.100.3/servicios/actualizarEstadoProceso.php?idProcesoDet=" + idProceso +
                    "&obs=" + obs + "&estadoProceso=" + estado;*/

        /*String URL = "http://192.168.100.3/servicios/actualizarEstadoProceso.php?idProcesoDet=" + idProceso +
                    "&obs=" + obs + "&estadoProceso=" + estado;*/

        String URL = "http://192.168.2.111/servicios/actualizarEstadoProceso.php?idProcesoDet=" + idProceso +
                "&obs=" + obs + "&estadoProceso=" + estado;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    Toast.makeText(DescriptionActivity.this, "Guardado Correctamente", Toast.LENGTH_SHORT).show();
                    Intent refresh = new Intent(DescriptionActivity.this, MainActivity.class);
                    startActivity(refresh);
                    DescriptionActivity.this.finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DescriptionActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue2 = Volley.newRequestQueue(this);
        queue2.add(stringRequest);
    }

    public void escanearCodigoBarras(View view){
        IntentIntegrator integrator = new IntentIntegrator(DescriptionActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        //Titulo
        integrator.setPrompt("Lector - CDP");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if ( result != null ){
            if ( result.getContents() == null ) {
                Toast.makeText(this, "Lector cancelado", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                validarActivoPorScanner(result.getContents());
            }
        }else
            super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean validarActivoPorScanner(String resultadoScan){
        for( int i = 0; i < listaActivos.size() ;i++ ){
            if (listaActivos.get(i).getCodBarras().equals(resultadoScan)){
                listaActivos.get(i).setRevision("S");
                validarActivo(listaActivos.get(i).getId());
                this.recreate();
                return true;
            }

        }
        return false;
    }

    void validarActivo(String idActivo){
        //String URL = "http://192.168.100.123/servicios/actualizarRevisionActivo.php?idActivo=" + idActivo;
        String URL = "http://192.168.2.111/servicios/actualizarRevisionActivo.php?idActivo=" + idActivo;
        //String URL = "http://192.168.100.3/servicios/actualizarRevisionActivo.php?idActivo=" + idActivo;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(DescriptionActivity.this, "'"+ idActivo + "' Validado", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue1 = Volley.newRequestQueue(this);
        queue1.add(stringRequest);
    }

}