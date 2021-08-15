package com.example.controlinventario;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivoDAO {
    RequestQueue queue1;
    Context context;

    List<Activo> listarActivos(String response){
        List<Activo> activos = new ArrayList();
        try {
            JSONObject jsonObject = new JSONObject(response);
            //JSONArray cadena = new JSONArray(response);
            for (int i = 0; ; i++) {
                //JSONObject jsonObject = cadena.getJSONObject(i);
                activos.add(new Activo(jsonObject.getJSONObject(""+i+"").get("ID_ACT").toString(),
                        jsonObject.getJSONObject(""+i+"").get("NOM_ACT").toString(),
                        jsonObject.getJSONObject(""+i+"").get("EST_ACT").toString(),
                        jsonObject.getJSONObject(""+i+"").get("COD_BAR_ACT").toString(),
                        jsonObject.getJSONObject(""+i+"").get("ID_FUN_PER").toString(),
                        jsonObject.getJSONObject(""+i+"").get("REV_ACT").toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return activos;
    }

    List<Activo> activosDeFuncionario(String idFuncionario){
        List<Activo> listaActivos = new ArrayList();
        String URL = "http://192.168.100.123/servicios/cargarActivosDeFuncionario.php?funcionario=" + idFuncionario;
        //String URL = "http://192.168.100.3/servicios/cargarActivosDeFuncionario.php?funcionario=" + idFuncionario;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //JSONArray cadena = new JSONArray(response);
                    for (int i = 0; ; i++) {
                        //JSONObject jsonObject = cadena.getJSONObject(i);
                        listaActivos.add(new Activo(jsonObject.getJSONObject(""+i+"").get("ID_ACT").toString(),
                                jsonObject.getJSONObject(""+i+"").get("NOM_ACT").toString(),
                                jsonObject.getJSONObject(""+i+"").get("EST_ACT").toString(),
                                jsonObject.getJSONObject(""+i+"").get("COD_BAR_ACT").toString(),
                                jsonObject.getJSONObject(""+i+"").get("ID_FUN_PER").toString(),
                                jsonObject.getJSONObject(""+i+"").get("REV_ACT").toString()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue1 = Volley.newRequestQueue(new DescriptionActivity());
        queue1.add(stringRequest);
        return listaActivos;
    }

}
