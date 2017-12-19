package com.example.burak.gzs;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.burak.gzs.Entity.Database;

public class MainActivity extends AppCompatActivity {
    EditText edt_username, edt_password;
    Button btn_login;
    Button btn_kayit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_kayit = (Button) findViewById(R.id.btn_kayit);

        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr = edt_username.getText().toString();
                String pwd = edt_password.getText().toString();
                Database database = new Database(MainActivity.this);
                database.veriEkle2(usr, pwd);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edt_username.getText().toString();
                String pwd = edt_password.getText().toString();
                Log.d(user,pwd);
                login(user, pwd);

            }
        });

    }
    private void login(String usr, String pwd) {
        Boolean Hesapvar = Boolean.FALSE;
        Database database = new Database(MainActivity.this);
        Hesapvar =  database.loginOl(usr, pwd);
        Log.d("sonuc",Hesapvar.toString());

        if(Hesapvar) {
            Toast.makeText(getBaseContext(),"giris yapildi", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getBaseContext(),  "Yanlıs Kullanıcı Adı veya Sifre", Toast.LENGTH_LONG).show();
        }
    }
}
