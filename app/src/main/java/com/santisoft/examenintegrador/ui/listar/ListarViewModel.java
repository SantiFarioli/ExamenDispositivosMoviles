package com.santisoft.examenintegrador.ui.listar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.santisoft.examenintegrador.MainActivity;
import com.santisoft.examenintegrador.model.Persona;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListarViewModel extends AndroidViewModel {

    private MutableLiveData<List<Persona>> mPersonas;

    public ListarViewModel(@NonNull Application application) {
        super(application);
        mPersonas = new MutableLiveData<>();
        cargarPersonas();
    }

    public LiveData<List<Persona>> getPersonas() {
        return mPersonas;
    }

    private void cargarPersonas() {
        List<Persona> listaPersonas = MainActivity.listaPersonas;

        Collections.sort(listaPersonas, new Comparator<Persona>() {
            @Override
            public int compare(Persona p1, Persona p2) {
                return Integer.compare(p2.getEdad(), p1.getEdad());
            }
        });

        mPersonas.setValue(listaPersonas);
    }

    public void actualizarLista() {
        cargarPersonas();
    }
}
