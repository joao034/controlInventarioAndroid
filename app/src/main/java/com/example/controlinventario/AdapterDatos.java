package com.example.controlinventario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolder> {
    private Context context;
    private List<ProcesoValidacionDetalle> lista;
    private LayoutInflater inflater;

    public AdapterDatos(Context context, List<ProcesoValidacionDetalle> lista) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public AdapterDatos.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //establece a cada elemento de la lista el aspecto que tendrá
        View view = inflater.inflate(R.layout.tarjeta_proceso, null);
        return new AdapterDatos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterDatos.ViewHolder holder, final int position) {
        holder.bindData(lista.get(position));
        /*ProcesoValidacionDetalle detalle = lista.get(position);
        holder.funcionario.setText(detalle.getFuncionario().getNombre() + " " +  detalle.getFuncionario().getApellido());
        holder.estado.setText(detalle.getEstado());*/
    }

    public void setItems(List<ProcesoValidacionDetalle> listaItems){
        lista = listaItems;
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView funcionario, estado;
        public ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.imagen);
            funcionario = itemView.findViewById(R.id.funcio);
            estado = itemView.findViewById(R.id.status);
        }

        void bindData(final ProcesoValidacionDetalle item){
            funcionario.setText(item.getFuncionario().toString());
            estado.setText(item.getEstado());
        }
    }
}
