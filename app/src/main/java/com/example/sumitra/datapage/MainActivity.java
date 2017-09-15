package com.example.sumitra.datapage;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends Activity {
   DatabaseHelper mydb;
    IncidentAdapter incidentAdapter;
    ListView listView;
    Button bn_popup;
    int count=1;
    List<Incident> incidentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= (ListView) this.findViewById(R.id.myListview);
        incidentAdapter=new IncidentAdapter(MainActivity.this,R.layout.display_row_data);
        //initionalize db
        mydb=new DatabaseHelper(this);

        if(count==1)
        {
            //adding data to db ,client table
            Log.d("Insert data in client: ", "Inserting ..");
            mydb.addClient(new Client(1, "Ents", "Billou"));
            mydb.addClient(new Client(2, "Ets", "VIEUX"));
            mydb.addClient(new Client(3, "martin", null));
            mydb.addClient(new Client(4, "dupont", null));
//        mydb.addClient(new Client(4,"dupont",null));

            //adding data to db incident table
            Log.d("Incident Insert data", "Inserting data");
            mydb.addIncident(new Incident(112, "Ets", 2, 1022017, 9032017, "facture impayee"));
            mydb.addIncident(new Incident(113, "Ents", 1, 1122017, 7032017, "paiement non recue"));
            mydb.addIncident(new Incident(114, "martin", 3, 12072017, 0, "contrat non recu"));
            mydb.addIncident(new Incident(115, "dupont", 4, 5092017, 0, "facture impayee"));
        }
        else
        {
            count=0;
        }

        //reading all clients
        Log.d("Reading: ", "Reading all clients..");
        List<Client> clientList=mydb.getAllClients();

        for (Client cn : clientList) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getName() + " ,First Name: " +    cn.getFirstName();
            // Writing Contacts to log
            Log.d("Name: ", log);

        }
        //trying to show data for a client on ui
        Client client=mydb.getClient(1);
        Log.d("data",client.name);





        //incident data show to ui
       // Incident incident=mydb.getIncident(112);
       //name.setText(incident.client_id);

        //reading all incidents
        Log.d("Reading: ", "Reading all clients..");
         incidentList=mydb.getAllIncidents();
        //set list adapter


        for (Incident in : incidentList) {
            String log = "Id: "+in.getPri_id()+" ,Client Name: " + in.getClientName() + " ,client ID: " +in.getClient_id()+", creation date"+in.getDcreation()+",Last exchange date"+in.getDexchange()+",status"+in.getStatus();
            // Writing Contacts to log
            Log.d("Name: ", log);
            //add objects to the list adapter
            incidentAdapter.add(in);

        }

        //adding list data to adapter
      // incidentAdapter.add(incidentList);
        listView.setAdapter(incidentAdapter);


        // listening to single list item on click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // ItemClicked item = adapter.getItemAtPosition(position);
               Object incident=listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "selected Item Name is " , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this,DetailShow.class);
                //based on item add info to intent
                startActivity(intent);
            }
        });

        //on click on list
       // listView.getOnItemClickListener()



    }



}

