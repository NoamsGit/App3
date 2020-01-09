package com.example.app3.Data;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.app3.Entities.Parcel;
import com.example.app3.Entities.ParcelChange;
import com.example.app3.Entities.ParcelDAO;

import java.util.List;

public class ParcelRepository2 {
    private static  ParcelRepository2 instance = null;

    private MutableLiveData<ParcelChange> parcelChange;
    private ParcelDataSource parcelDataSourse;

    private HistoryDataSource2 historyDataSource2 ;
    private ParcelDAO parcelDAO;
    private MutableLiveData<List<Parcel>> parcelList;

//--------- singleton implementation ---------
    public static ParcelRepository2 getInstance(Application application){
        if(instance == null){
            instance = new ParcelRepository2(application);
        }
        return instance;
    }

//--------- constractor ---------
    private ParcelRepository2(Application application){
        historyDataSource2 = HistoryDataSource2.getInstance(application.getApplicationContext());
        parcelDAO = historyDataSource2.parcelDAO();
        parcelDataSourse = ParcelDataSource.getInstance();
        parcelList = new MutableLiveData<List<Parcel>>();
        parcelChange = new MutableLiveData<ParcelChange>();

        ParcelDataSource.notifyToStudentList(new ParcelDataSource.NotifyDataChange<List<Parcel>>(){
            @Override
            public void OnDataChanged(List<Parcel> obj) {
                Parcel parcel = (Parcel) obj;
                Parcel ExistsInRoom = parcelDAO.getParcelById(parcel.getId());
                if(ExistsInRoom == null){
                    parcelDAO.insert(parcel);
                }
                else {
                    parcelDAO.update(parcel);
                }
                parcelList.setValue(parcelDAO.getAllParcels());
            }

            @Override
            public void onFailure(Exception exception) {
                ///////
            }
        });

        parcelChange = parcelDataSourse.getParcelChange();
    }

// -------- expose API for upper layers --------
    public  void addParcel(final Parcel parcel){
        parcelDataSourse.addParcel(parcel);
    }

    public  MutableLiveData<ParcelChange> getParcelChange(){
        return parcelChange;
    }
}
