package com.example.tp1moyanoignacio.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp1moyanoignacio.model.Usuario;
import com.example.tp1moyanoignacio.request.ApiClient;
import com.example.tp1moyanoignacio.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mUsuario;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getMUsuario(){
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public void leerDatos(boolean booleano){
        Usuario u = ApiClient.leer(context);
        Log.d("desdeElLogin", booleano+"");
        if(u.getDNI().equals("-1") || !booleano){
            mUsuario.setValue(new Usuario());
        }else{
            mUsuario.setValue(ApiClient.leer(context));
        }
    }

    public void editarCampos(Usuario u){
        if(u.getDNI().isEmpty() || u.getNombre().isEmpty() || u.getApellido().isEmpty() || u.getCorreo().isEmpty() || u.getPassword().isEmpty()){
            Toast.makeText(context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            ApiClient.guardar(context, u);
            Toast.makeText(context, "Campos guardados correctamente", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
