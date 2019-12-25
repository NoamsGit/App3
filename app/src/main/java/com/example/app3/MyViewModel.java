package com.example.app3;


import com.example.app3.Data.ParcelDataSource;
import com.example.app3.Data.ParcelRepository;
import com.example.app3.Entities.Parcel;
import com.example.app3.Entities.ParcelChange;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    public MyViewModel() {
        parcelChange = new MutableLiveData<ParcelChange>() ;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        parcelsRef = database.getReference("test");
        parcelRepository = new ParcelRepository();
        parcelChange = parcelRepository.getParcelChange();
    }


    static ParcelRepository parcelRepository;

    public static DatabaseReference parcelsRef;
    public static MutableLiveData<ParcelChange> parcelChange;

    public MutableLiveData<ParcelChange> getParcelChange(){

        return parcelChange;
    }
//    private static void parcelAddedSuccessfully() {
//        String temp = new String("parcelAddedSuccessfully");
//        stringTest.setValue(temp);
//    }
//    private static void parcelAddedFailure() {
//        String temp = new String("parcelAddedFailure");
//        stringTest.setValue(temp);
//    }
    public static void addParcelToFirebase( Parcel parcel) {
        //String key = Parcel.getId().toString();
        parcelRepository.addParcel(parcel);
        //ParcelDataSource.addParcel(parcel);
//        int a;
//        String key = parcelsRef.push().getKey();
//        parcelsRef.child(key).setValue(parcel).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                parcelAddedSuccessfully();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                parcelAddedFailure();
//            }
//        });

    }
//    public static void addParcelToFirebase(final String test) {
//        //String key = Parcel.getId().toString();
//        String key = parcelsRef.push().getKey();
//        parcelsRef.child(key).setValue(test).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                parcelAddedSuccessfully();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                parcelAddedFailure();
//            }
//        });
//
//    }






}
