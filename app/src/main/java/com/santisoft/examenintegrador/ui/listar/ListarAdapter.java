package com.santisoft.examenintegrador.ui.listar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.santisoft.examenintegrador.R;
import com.santisoft.examenintegrador.model.Persona;

import java.util.List;

public class ListarAdapter extends RecyclerView.Adapter<ListarAdapter.PersonaViewHolder> {

    private List<Persona> listaPersonas;

    public ListarAdapter(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Persona persona = listaPersonas.get(position);
        holder.tvDNI.setText("DNI: " + persona.getDni());
        holder.tvApellido.setText("Apellido: " + persona.getApellido());
        holder.tvNombre.setText("Nombre: " + persona.getNombre());
        holder.tvEdad.setText("Edad: " + persona.getEdad());

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorFondoPar));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorFondoImpar));
        }
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvDNI, tvApellido, tvNombre, tvEdad;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDNI = itemView.findViewById(R.id.tvDNI);
            tvApellido = itemView.findViewById(R.id.tvApellido);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEdad = itemView.findViewById(R.id.tvEdad);
        }
    }
}
