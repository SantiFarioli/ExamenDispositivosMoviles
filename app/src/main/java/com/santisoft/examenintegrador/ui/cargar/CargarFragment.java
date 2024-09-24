package com.santisoft.examenintegrador.ui.cargar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.santisoft.examenintegrador.databinding.FragmentCargarBinding;
import com.santisoft.examenintegrador.model.Persona;

import java.util.List;


public class CargarFragment extends Fragment {

    private CargarViewModel mViewModel;
    private FragmentCargarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CargarViewModel.class);

        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        mViewModel.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {
                if (error != null) {
                    binding.tvError.setVisibility(View.VISIBLE);
                    binding.tvError.setText(error);
                } else {
                    binding.tvError.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Persona agregada exitosamente", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                    NavHostFragment.findNavController(CargarFragment.this).navigateUp();
                }
            }
        });

        binding.btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = binding.etDNI.getText().toString().trim();
                String apellido = binding.etApellido.getText().toString().trim();
                String nombre = binding.etNombre.getText().toString().trim();
                String edad = binding.etEdad.getText().toString().trim();

                mViewModel.cargarPersona(dni, apellido, nombre, edad);
            }
        });
        return root;
    }

    private void limpiarCampos() {
        binding.etDNI.setText("");
        binding.etApellido.setText("");
        binding.etNombre.setText("");
        binding.etEdad.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}