package com.santisoft.examenintegrador.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.santisoft.examenintegrador.databinding.FragmentListarBinding;
import com.santisoft.examenintegrador.model.Persona;

import java.util.List;

public class ListarFragment extends Fragment {

    private ListarViewModel listarViewModel;
    private FragmentListarBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listarViewModel = new ViewModelProvider(this).get(ListarViewModel.class);

        listarViewModel.getPersonas().observe(getViewLifecycleOwner(), new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> personas) {
                ListarAdapter adapter = new ListarAdapter(personas);
                binding.lista.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.lista.setAdapter(adapter);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        listarViewModel.actualizarLista();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
