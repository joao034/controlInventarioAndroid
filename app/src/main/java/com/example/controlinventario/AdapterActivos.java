package com.example.controlinventario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class AdapterActivos extends RecyclerView.Adapter<AdapterActivos.ViewHolder>{

    private Context context;
    private List<Activo> lista;
    private LayoutInflater inflater;

    public AdapterActivos(Context context, List<Activo> lista) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public AdapterActivos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //establece a cada elemento de la lista el aspecto que tendrá
        View view = inflater.inflate(R.layout.tarjeta_activo, null);
        return new AdapterActivos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterActivos.ViewHolder holder, final int position) {
        holder.bindData(lista.get(position));
        /*ProcesoValidacionDetalle detalle = lista.get(position);
        holder.funcionario.setText(detalle.getFuncionario().getNombre() + " " +  detalle.getFuncionario().getApellido());
        holder.estado.setText(detalle.getEstado());*/
    }

    public void setItems(List<Activo> listaItems){
        lista = listaItems;
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView activo, codBarras, idActivo;
        CheckBox checkBoxValidar;
        RequestQueue queue1;
        ViewHolder(View itemView) {
            super(itemView);
            activo = itemView.findViewById(R.id.activo);
            idActivo = itemView.findViewById(R.id.idActivo);
            codBarras = itemView.findViewById(R.id.codBarras);
            checkBoxValidar = itemView.findViewById(R.id.checkBoxValidar);
        }

        void bindData(final Activo item){
            activo.setText(item.getNombre());
            idActivo.setText("ID: "+ item.getId());
            codBarras.setText("Cod. Barras: "+ item.getCodBarras());
            if(item.getRevision().equalsIgnoreCase("S"))
                checkBoxValidar.setChecked(true);
            else
                checkBoxValidar.setChecked(false);

            checkBoxValidar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBoxValidar.isChecked())
                        validarActivo(item.getId());
                    else
                        invalidarActivo(item.getId());
                }
            });
        }

        void validarActivo(String idActivo){
            String URL = "http://192.168.100.123/servicios/actualizarRevisionActivo.php?idActivo=" + idActivo;
            //String URL = "http://192.168.100.3/servicios/actualizarRevisionActivo.php?idActivo=" + idActivo;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, "Activo Validado", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            queue1 = Volley.newRequestQueue(context);
            queue1.add(stringRequest);
        }

        void invalidarActivo(String idActivo){
            String URL = "http://192.168.100.123/servicios/invalidarActivo.php?idActivo=" + idActivo;
            //String URL = "http://192.168.100.3/servicios/invalidarActivo.php?idActivo=" + idActivo;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, "Activo Invalidado", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            queue1 = Volley.newRequestQueue(context);
            queue1.add(stringRequest);
        }
    }

    }


