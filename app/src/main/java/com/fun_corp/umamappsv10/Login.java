package com.fun_corp.umamappsv10;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


/**
 * Created by umaaamm on 9/9/17.
 */

public class Login extends AppCompatActivity {
    Firebase bacadata;
    String user,pass;
    EditText username,password;
    Button loginp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        username = (EditText) findViewById(R.id.user);
        password =(EditText) findViewById(R.id.pass);
        loginp = (Button) findViewById(R.id.plogin);
        bacadata = new Firebase("https://kandang-pintar.firebaseio.com/login");
        bacadata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.child("username").getValue().toString();
                pass = dataSnapshot.child("password").getValue().toString();
                //Toast.makeText(Login.this, "user"+u+" , pass "+p, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        loginp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u,p;
                u = username.getText().toString();
                p = password.getText().toString();

                if (u.equals(user) && p.equals(pass)){
                    Toast.makeText(Login.this, "Selamat Anda Berhasil Login", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this,MainActivity.class);
                    startActivity(i);
                    //Toast.makeText(Login.this, "user"+user+" , pass "+pass, Toast.LENGTH_SHORT).show();
                   // Snackbar.make(, "Hidupin Aku", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{
                    Toast.makeText(Login.this, "gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
