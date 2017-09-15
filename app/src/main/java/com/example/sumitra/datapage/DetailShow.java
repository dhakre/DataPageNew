package com.example.sumitra.datapage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailShow extends AppCompatActivity {
     Button bnBack;
    Incident incident;
    String clientname,sta;
    TextView name,clientid,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show);

        bnBack= (Button) findViewById(R.id.bn_back);
        name= (TextView) findViewById(R.id.clientName);
        clientid= (TextView) findViewById(R.id.cid);
        status= (TextView) findViewById(R.id.status);


        bnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(DetailShow.this,MainActivity.class);
               // startActivity(intent);
            }
        });

      //Intent intent=getIntent();
        //clientname=getIntent().getExtras().getString("client");
        //sta=getIntent().getExtras().getString("status");

        //set view data
        name.setText(clientname);
        status.setText(sta);

    }
}
