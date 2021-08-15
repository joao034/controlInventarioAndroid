package com.example.controlinventario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetalleValidacionDAO {
    ActivoDAO dao;
    List<Activo> activos;
    List<ProcesoValidacionDetalle> listarProcesos(String response){
        List<ProcesoValidacionDetalle> procesos = new ArrayList();
        try {
            JSONObject jsonObject = new JSONObject(response);
            //JSONArray cadena = new JSONArray(response);
            for (int i = 0; ; i++) {
                //JSONObject jsonObject = cadena.getJSONObject(i);
                procesos.add(new ProcesoValidacionDetalle(Integer.valueOf(jsonObject.getJSONObject(""+i+"").get("ID_PRO_DET").toString()),
                        Integer.valueOf(jsonObject.getJSONObject(""+i+"").get("ID_PRO_PER").toString()),
                        new Funcionario(jsonObject.getJSONObject(""+i+"").get("ID_FUN_PER").toString(),
                                jsonObject.getJSONObject(""+i+"").get("NOM_FUN").toString(),
                                jsonObject.getJSONObject(""+i+"").get("APE_FUN").toString(),
                                Integer.valueOf(jsonObject.getJSONObject(""+i+"").get("NUM_ACT_FUN").toString())
                                //activos = dao.activosDeFuncionario(jsonObject.getJSONObject(""+i+"").get("ID_FUN_PER").toString())
                                ),
                        jsonObject.getJSONObject(""+i+"").get("OBS_REV").toString(),
                        jsonObject.getJSONObject(""+i+"").get("EST_PRO").toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return procesos;
    }

    List<ProcesoValidacionDetalle> listarDetalles(String tituloProceso){
        List<ProcesoValidacionDetalle> detalles = new ArrayList();


        return detalles;
    }
}
