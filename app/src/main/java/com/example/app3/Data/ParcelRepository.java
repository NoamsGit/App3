package com.example.app3.Data;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import com.example.app3.Entities.Parcel;
import com.example.app3.Entities.ParcelChange;
import com.example.app3.Entities.ParcelDAO;
import com.example.app3.Entities.ParcelDatabase;

import java.util.ArrayList;
import java.util.List;

public class ParcelRepository extends Application {

    private ParcelDAO parcelDAO;


    private MutableLiveData<ParcelChange> parcelChange;
    private ParcelDataSource parcelDataSourse;
    private HistoryDataSource historyDataSource;
    private MutableLiveData<List<Parcel>> parcparcelList;
    private List<Parcel> anotherDummyList;

    public ParcelRepository()  {


        parcelChange = new MutableLiveData<ParcelChange>();
        parcparcelList = new MutableLiveData<List<Parcel>>();
        anotherDummyList = new ArrayList<>();
        historyDataSource = new HistoryDataSource();
        parcelDataSourse = ParcelDataSource.getInstance();
        ParcelDataSource.notifyToStudentList(new ParcelDataSource.NotifyDataChange<List<Parcel>>() {
            @Override
            public void OnDataChanged(List<Parcel> obj) {
               if(){}


            }

            @Override
            public void onFailure(Exception exception) {
                ///////
            }
        });
        parcelChange = parcelDataSourse.getParcelChange();
    }

    public  void addParcel(final Parcel parcel){
        parcelDataSourse.addParcel(parcel);
    }

    public  MutableLiveData<ParcelChange> getParcelChange(){
        return parcelChange;
    }





}
