package com.example.weekifood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnInicioDeSesion, btnRegistrar;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (user==null) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            btnInicioDeSesion = findViewById(R.id.accessBtnInicioDeSesion);
            btnRegistrar = findViewById(R.id.accessBtnRegistrar);

            btnInicioDeSesion.setOnClickListener(this);
            btnRegistrar.setOnClickListener(this);
        }else{startActivity(new Intent(MainActivity.this,NavLayout.class));};
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.accessBtnInicioDeSesion) {
            startActivity(new Intent(MainActivity.this,InicioDeSesion.class));}
        if(v.getId() == R.id.accessBtnRegistrar){
            startActivity(new Intent(MainActivity.this, Registrar.class));}
        finish();
    }
}