package com.example.app3.Entities;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Parcel.class}, version = 1)
public abstract class ParcelDatabase extends RoomDatabase{

    private static ParcelDatabase instance;
    public abstract ParcelDAO parcelDAO();

    public static synchronized ParcelDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ParcelDatabase.class,
                    "parcel_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
