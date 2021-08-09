package com.example.controlinventario;

import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProcesoValidacionDAO {

    List<ProcesoValidacion> listarProcesos(String response){
        List<ProcesoValidacion> procesos = new ArrayList();
        try {
            JSONObject jsonObject = new JSONObject(response);
            //JSONArray cadena = new JSONArray(response);
            for (int i = 0; ; i++) {
                //JSONObject jsonObject = cadena.getJSONObject(i);
                procesos.add((new ProcesoValidacion(Integer.valueOf(jsonObject.getJSONObject(""+i+"").get("ID_PRO").toString()),
                        jsonObject.getJSONObject(""+i+"").get("TIT_PRO").toString(),
                        jsonObject.getJSONObject(""+i+"").get("FEC_PRO").toString())));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return procesos;
    }

}
