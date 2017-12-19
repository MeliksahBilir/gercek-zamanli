package com.example.burak.gzs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.burak.gzs.Entity.Database;

import java.util.List;

/**
 * Created by MeliksahBilir on 18.12.2017.
 */

public class Main7Activity extends AppCompatActivity {
    ListView lst2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        lst2 = (ListView) findViewById(R.id.list_asilan);
        Database database = new Database(Main7Activity.this);
        List<String> vVeriler = database.verileriListele2();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main7Activity.this, android.R.layout.simple_list_item_1, android.R.id.text1, vVeriler);
        lst2.setAdapter(adapter);
    }
}
