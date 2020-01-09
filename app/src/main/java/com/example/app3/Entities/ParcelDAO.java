package com.example.app3.Entities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ParcelDAO {
    //@Insert
    //void Insert(Parcel... parcels);
    @Insert
    void insert(Parcel... parcels);
    @Update
    void update(Parcel... parcels);
    @Delete
    void delete(Parcel parcel);
    @Query("SELECT * FROM Parcels")
    List<Parcel> getAllParcels();
    @Query("SELECT * FROM Parcels WHERE id = :id")
    Parcel getParcelById(String id);
    @Query("Delete From Parcels")
    void deleteAllNotes();

}
