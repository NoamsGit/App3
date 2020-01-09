package com.example.app3.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.app3.Entities.Parcel;
import com.example.app3.Entities.ParcelDAO;
import com.example.app3.Entities.ParcelDatabase;

@Database(entities = {Parcel.class}, version = 1)
public abstract class HistoryDataSource2 extends RoomDatabase {
    private static HistoryDataSource2 instance;
    public abstract ParcelDAO parcelDAO();

    public static synchronized HistoryDataSource2 getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), HistoryDataSource2.class,
                    "parcel_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
