package com.example.tp1moyanoignacio.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.tp1moyanoignacio.model.Usuario;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if(sp == null){
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario){
        SharedPreferences shared = conectar(context);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("DNI", usuario.getDNI());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("correo", usuario.getCorreo());
        editor.putString("password", usuario.getPassword());
        editor.commit();
    }

    public static Usuario leer(Context context){
        SharedPreferences shared = conectar(context);
        String DNI = shared.getString("DNI", "-1");
        String nombre = shared.getString("nombre", "-1");
        String apellido = shared.getString("apellido", "-1");
        String correo = shared.getString("correo", "-1");
        String password = shared.getString("password", "-1");
        return new Usuario(DNI, nombre, apellido, correo, password);
    }

    public static Usuario login(Context context, String correo, String password){
        Usuario usuario = null;
        SharedPreferences shared = conectar(context);
        String DNI = shared.getString("DNI", "-1");
        String nombre = shared.getString("nombre", "-1");
        String apellido = shared.getString("apellido", "-1");
        String cor = shared.getString("correo", "-1");
        String pass = shared.getString("password", "-1");
        if(cor.equals(correo) && pass.equals(password)){
            usuario = new Usuario(DNI, nombre, apellido, cor, pass);
        }
        return usuario;
    }
}
