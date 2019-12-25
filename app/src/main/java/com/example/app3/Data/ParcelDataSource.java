package com.example.app3.Data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.app3.Entities.Parcel;
import com.example.app3.Entities.ParcelChange;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ParcelDataSource {

    private ParcelDataSource() {
        parcelChange = new MutableLiveData<ParcelChange>() ;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        parcelsRef = database.getReference("test");
    }
    private static ParcelDataSource parcelDataSource_instance = null;
    public static ParcelDataSource getInstance(){
        if (parcelDataSource_instance == null)
            parcelDataSource_instance = new ParcelDataSource();

        return parcelDataSource_instance;
    }

    public static DatabaseReference parcelsRef;
    public static MutableLiveData<ParcelChange> parcelChange;

    public MutableLiveData<ParcelChange> getParcelChange(){

        return parcelChange;
    }
    private static void parcelAddedSuccessfully() {
        ParcelChange parcelChangeTemp = new ParcelChange();
        parcelChangeTemp.setSuccess(true);
        parcelChange.setValue(parcelChangeTemp);
    }
    private static void parcelAddedFailure() {
        ParcelChange parcelChangeTemp = new ParcelChange();
        parcelChangeTemp.setFailure(true);
        parcelChange.setValue(parcelChangeTemp);
    }
    public static void addParcel(Parcel parcel){
        addParcelToFirebase(parcel);
    }
    private static void addParcelToFirebase( Parcel parcel) {
        //String key = Parcel.getId().toString();
        //int a;
        String key = parcelsRef.push().getKey();
        parcelsRef.child(key).setValue(parcel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                parcelAddedSuccessfully();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                parcelAddedFailure();
            }
        });

    }






}
