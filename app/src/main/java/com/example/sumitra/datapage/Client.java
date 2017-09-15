package com.example.sumitra.datapage;

/**
 * Created by sumitra on 9/9/2017.
 */

public class Client {
     int id;
     String name;
     String firstName;

    public Client()
    {
        //empty
    }
  public Client(int id,String name,String fname)
  {
      this.id=id;
      this.name=name;
      this.firstName=fname;
  }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
