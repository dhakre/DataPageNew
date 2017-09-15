package com.example.sumitra.datapage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sumitra on 9/8/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final  String DBName= "ClientData.db";
    //client table
    public static final  String TABLE_Client = "client_table";
    public static final  String PID = "ID";    //primary key
    public static final  String Cname = "Name";
    public static final  String CFirstName = "FirstName";
    //table incident values
    public static final  String TABLE_Incident = "incident_table";
    public static final  String in_ID = "incident_ID";  //primary key
    public static final  String ClientName = "incidentName";
    public static final  String Client_ID = "client_ID";//foreign key
    public static final  String creationDate = "creation_date";
    public static final  String LastModified = "Last_Modified_date";
    public static final  String status = "Status";


    public DatabaseHelper(Context context) {
        super(context, DBName, null, 1);
        //SQLiteDatabase db= this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_Client + "(" +
                PID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Cname + " TEXT, " +
                CFirstName + " INTEGER );");
       // initionalizing tables for incident table
        db.execSQL("CREATE TABLE "+ TABLE_Incident + "(" +
                in_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ClientName + " TEXT, " +
                Client_ID + " INTEGER,"+
                creationDate+" INTEGER, "+
                LastModified+" INTEGER, "+
                status+" TEXT, "+
                " FOREIGN KEY ("+Client_ID+") REFERENCES "+TABLE_Client+"("+PID+"));"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop client table"+ TABLE_Client);
        db.execSQL("Drop incident table"+TABLE_Incident);
        onCreate(db);

    }
    //db insert , update, delete for client table
    // Adding new client
    void addClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(PID, client.getId()); // Client id
        values.put(Cname, client.getName()); // Client name
        values.put(CFirstName, client.getFirstName());

        // Inserting Row
        db.insert(TABLE_Client, null, values);
        db.close(); // Closing database connection
    }
    // Getting single client
    Client getClient(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Client, new String[] { PID,
                        Cname, CFirstName }, PID + "=?",
                new String[] { String.valueOf(id) }, null, null, null,        null);
        if (cursor != null)
            cursor.moveToFirst();

        Client client = new Client(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));
        // return client
        return client;
    }

    // Getting All Clients
    public List<Client> getAllClients() {
        List<Client> clientList = new ArrayList<Client>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Client;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Client client = new Client();
                client.setId(Integer.parseInt(cursor.getString(0)));
                client.setName(cursor.getString(1));
                client.setFirstName(cursor.getString(2));
                // Adding contact to list
                clientList.add(client);
            } while (cursor.moveToNext());
        }

        // return contact list
        return clientList;
    }

    // Updating single client
    public int updateClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Cname, client.getName());
        values.put(CFirstName, client.getFirstName());

        // updating row
        return db.update(TABLE_Client, values, PID + " = ?",
                new String[] { String.valueOf(client.getId()) });
    }

    // Deleting single client
    public void deleteClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Client, PID + " = ?",
                new String[] { String.valueOf(client.getId()) });
        db.close();
    }

    // Getting total Clients
    public int getClientsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_Client;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    //db insert , update, delete for Incident table
    // Adding new incident
    void addIncident(Incident incident) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
       // values.put(in_ID, incident.getPri_id()); // incident id
        values.put(ClientName, incident.getClientName()); // Client name for this incident
        values.put(Client_ID, incident.getClient_id());
        values.put(creationDate, String.valueOf(incident.getDcreation()));
        values.put(LastModified, String.valueOf(incident.getDexchange()));
        values.put(status, incident.getStatus());

        // Inserting Row
        db.insert(TABLE_Incident, null, values);
        db.close(); // Closing database connection
    }
    /*/ Getting single client
    Incident getIncident(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Incident, new String[] { in_ID,
                        ClientName, Client_ID, creationDate, LastModified, status }, in_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null,null);
        if (cursor != null)
            cursor.moveToFirst();
        Incident incident = new Incident(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)),cursor.getString(5));
        // return client
        return incident;
    }*/

    // Getting All incidents
    public List<Incident> getAllIncidents() {
        List<Incident> incidentList = new ArrayList<Incident>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Incident;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Incident incident = new Incident();
                incident.setPri_id(Integer.parseInt(cursor.getString(0)));
                incident.setClientName(cursor.getString(1));
                incident.setClient_id(Integer.parseInt(cursor.getString(2)));
                incident.setDcreation(Integer.parseInt(cursor.getString(3)));
                incident.setDexchange(Integer.parseInt(cursor.getString(4)));
                incident.setStatus(cursor.getString(5));

                // Adding incident to list
                incidentList.add(incident);
            } while (cursor.moveToNext());
        }

        // return incident list
        return incidentList;
    }

    // Updating single incident
    public int updateIncident(Incident incident) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ClientName, incident.getClientName());
        values.put(Client_ID, incident.getClient_id());
        values.put(creationDate, incident.getDcreation());
        values.put(LastModified, incident.getDexchange());
        values.put(status, incident.getStatus());


        // updating row
        return db.update(TABLE_Incident, values, in_ID + " = ?",
                new String[] { String.valueOf(incident.getPri_id()) });
    }

    // Deleting single incident
    public void deleteIncident(Incident incident) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Incident, in_ID + " = ?",
                new String[] { String.valueOf(incident.getPri_id()) });
        db.close();
    }

    // Getting total incidents
    public int getIncidentsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_Incident;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
