package com.example.app3.Data;

import androidx.lifecycle.MutableLiveData;

import com.example.app3.Entities.Parcel;
import com.example.app3.Entities.ParcelChange;

public class ParcelRepository {

    public MutableLiveData<ParcelChange> parcelChange;
    ParcelDataSource parcelDataSourse;

    public ParcelRepository() {
        parcelChange = new MutableLiveData<ParcelChange>();
        parcelDataSourse = ParcelDataSource.getInstance();
        parcelChange = parcelDataSourse.getParcelChange();
    }

    public  void addParcel(final Parcel parcel){
        parcelDataSourse.addParcel(parcel);
    }

    public  MutableLiveData<ParcelChange> getParcelChange(){
        return parcelChange;
    }





}
