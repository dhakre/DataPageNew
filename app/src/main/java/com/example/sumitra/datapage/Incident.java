package com.example.sumitra.datapage;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sumitra on 9/9/2017.
 */

public class Incident implements Serializable {

    int pri_id;
    String clientName;
    int client_id;
    int Dcreation;
    int Dexchange;
    String status;


    public Incident()
    {
        //empty
    }
    public Incident(int pri_id,String clientName,int client_id,int dcreation,int Dexchange, String status)
    {
        this.pri_id=pri_id;
        this.clientName=clientName;
        this.client_id=client_id;
        this.Dcreation=dcreation;
        this.Dexchange=Dexchange;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public String getClientName() {
        return clientName;
    }

    public int getDcreation() {
        return Dcreation;
    }

    public int getDexchange() {
        return Dexchange;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setDcreation(int dcreation) {
        Dcreation = dcreation;
    }

    public void setDexchange(int dexchange) {
        Dexchange = dexchange;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getClient_id() {
        return client_id;
    }

    public int getPri_id() {
        return pri_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public void setPri_id(int pri_id) {
        this.pri_id = pri_id;
    }
}
