package com.example.burak.gzs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_username, edt_password;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);

        btn_login = (Button) findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(edt_username, edt_password);
            }
        });
    }
    private void login(EditText usr, EditText pwd) {
        if(usr.getText().toString().equals("admin") && pwd.getText().toString().equals("admin")) {
            Toast.makeText(getBaseContext(),"giris yapildi", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getBaseContext(),  "Yanlıs Kullanıcı Adı veya Sifre", Toast.LENGTH_LONG).show();
        }
    }
}
