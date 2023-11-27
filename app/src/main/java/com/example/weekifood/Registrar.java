package com.example.weekifood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrar extends AppCompatActivity implements View.OnClickListener{
    private EditText registroUsuario, registroEmail, registroPassword;
    private TextView registroRedirectBack;
    private Button registroEnviarBtn;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        registroUsuario = findViewById(R.id.registrarUsuario);
        registroEmail = findViewById(R.id.registrarEmail);
        registroPassword = findViewById(R.id.registrarPassword);

        registroEnviarBtn = findViewById(R.id.registrarButton);
        registroRedirectBack = findViewById(R.id.registrarBacker);

        auth = FirebaseAuth.getInstance();

        registroEnviarBtn.setOnClickListener(this);
        registroRedirectBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registrarButton) {

            String registroUsuarioString=registroUsuario.getText().toString().trim();
            String registroEmailString=registroEmail.getText().toString().trim();
            String registroPasswordString=registroPassword.getText().toString().trim();

            if(!registroUsuarioString.isEmpty()&&!registroEmailString.isEmpty()&&!registroPasswordString.isEmpty()){
                auth.createUserWithEmailAndPassword(registroEmailString,registroPasswordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            database = FirebaseDatabase.getInstance();
                            reference = database.getReference("usuarios");

                            String userid = task.getResult().getUser().getUid();

                            HelperClassUser helperClassUser=new HelperClassUser(registroUsuarioString);
                            reference.child(userid).setValue(helperClassUser);

                            startActivity(new Intent(Registrar.this, InicioDeSesion.class));
                        } else if (registroPasswordString.length()<6) {
                            Toast.makeText(Registrar.this,"La contraseña debe tener al menos 6 letras",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Registrar.this,"La cuenta ya está registrada",Toast.LENGTH_SHORT).show();}
                        }
                    });
            } else {
                Toast.makeText(Registrar.this,"Llena todos los campos correctamente",Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId() == R.id.registrarBacker){
            Intent intent = new Intent(Registrar.this, MainActivity.class);
            startActivity(intent);
        }
    }
}