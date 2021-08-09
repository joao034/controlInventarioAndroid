package com.example.controlinventario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivoDAO {

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
                        jsonObject.getJSONObject(""+i+"").get("ID_FUN_PER").toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return activos;
    }

}
