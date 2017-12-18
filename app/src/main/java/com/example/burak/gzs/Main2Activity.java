package com.example.burak.gzs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.burak.gzs.Entity.Database;
import com.example.burak.gzs.Entity.Feed;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {
    public static TextView data;
    Button btn;
    Button visual;
    Button vt;
    Button nem;
    Button gaz;
    String url = "https://api.thingspeak.com/channels/385862/feeds.json?results=50";
    ListView lst_data;
    private Feed feed;
    private List<Feed> feeds;
    private OkHttpClient client;
    private Request request;
    private String test = "";
    private String veriler[] = {"ali", "gasan"};
    private String veri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        feeds = new ArrayList<>();
        client = new OkHttpClient();

        data = (TextView)findViewById(R.id.txt_data);
        btn = (Button)findViewById(R.id.btn_fetch);
        visual = (Button)findViewById(R.id.btn_visual) ;
        vt = (Button)findViewById(R.id.btn_database);
        nem = (Button)findViewById(R.id.btn_nemChart);
        gaz = (Button)findViewById(R.id.btn_gazChart);
        //lst_data = (ListView)findViewById(R.id.lst_data);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, veriler);
        //lst_data.setAdapter(adapter);

        vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ıntent = new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(ıntent);
            }
        });

        nem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ıntent = new Intent(Main2Activity.this, Main5Activity.class);
                startActivity(ıntent);
            }
        });

        gaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ıntent = new Intent(Main2Activity.this, Main6Activity.class);
                startActivity(ıntent);
            }
        });


        visual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ıntent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(ıntent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        call.cancel();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        final String myResponse = response.body().string();

                        Main2Activity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("Response", myResponse);
                                //txtString.setText(myResponse);//
                                try{

                                    JSONObject reader = new JSONObject(myResponse);
                                    JSONArray sysa  = reader.getJSONArray("feeds");
                                    for(int i = 0 ; i < sysa.length() ; i++)
                                    {
                                        JSONObject sys = sysa.getJSONObject(i);
                                        feed = new Feed(sys.getString("entry_id"),sys.getString("field1"),sys.getString("created_at"));
                                        feeds.add(feed);
                                        //test = test + sys.getString("field2")  + "<--->" + sys.getString("field1") +"\n";
                                        //veriler[i] = veri;
                                        test = test + "GAZ : " + sys.getString("field3") + "\t"
                                                + "Sicaklik -> " + sys.getString("field2") + "\t"
                                                + "Nem -> " + sys.getString("field1") + "\n";
                                        data.setText(test);

                                        Database database = new Database(Main2Activity.this);
                                        database.veriEkle(sys.getString("field2"), sys.getString("field1"), sys.getString("field3"));
                                        Log.d("Response Feed" , feed.toString());
                                    }



                                }catch (Exception ex){
                                    Log.e("Parse exception" , ex.getMessage());
                                }

                                }
                        });

                    }
                });
            }

        });


    }

}
