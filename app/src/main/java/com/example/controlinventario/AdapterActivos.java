package com.example.controlinventario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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
        }

    }

}
