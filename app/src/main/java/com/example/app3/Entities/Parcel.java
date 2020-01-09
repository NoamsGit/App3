package com.example.app3.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "Parcels")
public class Parcel {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String address;

    public Parcel() {
    }

    public Parcel(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
