package com.example.burak.gzs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.burak.gzs.Entity.Database;

import java.util.List;

public class Main4Activity extends AppCompatActivity {
    ListView list1;
    Button btn_listele;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        list1 = (ListView) findViewById(R.id.lst_data);
        btn_listele = (Button) findViewById(R.id.btn_listele);

        btn_listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database database = new Database(Main4Activity.this);
                List<String> vVeriler = database.verileriListele();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main4Activity.this, android.R.layout.simple_list_item_1, android.R.id.text1, vVeriler);
                list1.setAdapter(adapter);
            }
        });

    }
}
