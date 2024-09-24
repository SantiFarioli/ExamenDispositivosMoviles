package com.santisoft.examenintegrador.ui.cargar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.santisoft.examenintegrador.MainActivity;
import com.santisoft.examenintegrador.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class CargarViewModel extends AndroidViewModel {

    private MutableLiveData<String> mError;

    public CargarViewModel(@NonNull Application application) {
        super(application);

    }


    public LiveData<String> getError() {
        if (mError == null) {
            mError = new MutableLiveData<>();
        }
        return mError;
    }

    public void cargarPersona(String dni, String apellido, String nombre, String edadStr) {
        if (dni.isEmpty() || apellido.isEmpty() || nombre.isEmpty() || edadStr.isEmpty()) {
            mError.setValue("Todos los campos deben estar completos");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            mError.setValue("La edad debe ser un número válido");
            return;
        }

        for (Persona persona : MainActivity.listaPersonas) {
            if (persona.getDni().equals(dni)) {
                mError.setValue("El DNI ya está registrado");
                return;
            }
        }

        Persona nuevaPersona = new Persona(dni, apellido, nombre, edad);
        MainActivity.listaPersonas.add(nuevaPersona);
        mError.setValue(null);
    }
}