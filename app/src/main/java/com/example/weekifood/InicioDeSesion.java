package com.example.weekifood;

import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InicioDeSesion extends AppCompatActivity implements View.OnClickListener{
    private EditText inicioDeSesionEmail, inicioDeSesionPassword;
    private TextView inicioDeSesionRedirectBack;
    private Button inicioDeSesionEnviarBtn;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_de_sesion);

        inicioDeSesionEmail = findViewById(R.id.inicio_de_sesionEmail);
        inicioDeSesionPassword = findViewById(R.id.inicio_de_sesionPassword);

        inicioDeSesionEnviarBtn = findViewById(R.id.inicio_de_sesionButton);
        inicioDeSesionRedirectBack = findViewById(R.id.inicio_de_sesionBacker);

        auth = FirebaseAuth.getInstance();

        inicioDeSesionEnviarBtn.setOnClickListener(this);
        inicioDeSesionRedirectBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.inicio_de_sesionButton) {

            String inicioDeSesionEmailString=inicioDeSesionEmail.getText().toString().trim();
            String inicioDeSesionPasswordString=inicioDeSesionPassword.getText().toString().trim();

            if(!inicioDeSesionEmailString.isEmpty() && !inicioDeSesionPasswordString.isEmpty()){
                if(Patterns.EMAIL_ADDRESS.matcher(inicioDeSesionEmailString).matches()) {
                    auth.signInWithEmailAndPassword(inicioDeSesionEmailString, inicioDeSesionPasswordString)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    startActivity(new Intent(InicioDeSesion.this, NavLayout.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(InicioDeSesion.this, "Error al iniciar", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{Toast.makeText(InicioDeSesion.this, "La cuenta no existe" ,Toast.LENGTH_SHORT).show();}
            } else {
                Toast.makeText(InicioDeSesion.this, "Llena los campos correctamente" ,Toast.LENGTH_SHORT).show();
            }
        }

        else if(v.getId() == R.id.inicio_de_sesionBacker){
            Intent intent = new Intent(InicioDeSesion.this, MainActivity.class);
            startActivity(intent);}
    }
}