package com.example.tp1moyanoignacio.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp1moyanoignacio.R;
import com.example.tp1moyanoignacio.databinding.ActivityRegistroBinding;
import com.example.tp1moyanoignacio.model.Usuario;

public class RegistroActivity extends AppCompatActivity {
    private RegistroActivityViewModel vm;
    private ActivityRegistroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(usuario.getDNI());
                binding.etNombre.setText(usuario.getNombre());
                binding.etApellido.setText(usuario.getApellido());
                binding.etCorreo.setText(usuario.getCorreo());
                binding.etPassword.setText(usuario.getPassword());
            }
        });
        Intent i = getIntent();
        boolean booleano = i.getBooleanExtra("login", false);
        vm.leerDatos(booleano);
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DNI = binding.etDni.getText().toString();
                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String correo = binding.etCorreo.getText().toString();
                String password = binding.etPassword.getText().toString();
                vm.editarCampos(new Usuario(DNI, nombre, apellido, correo, password));
            }
        });
    }
}