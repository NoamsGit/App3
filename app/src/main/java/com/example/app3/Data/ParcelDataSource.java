package com.example.app3.Data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.app3.Entities.Parcel;
import com.example.app3.Entities.ParcelChange;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ParcelDataSource {

    private static ParcelDataSource parcelDataSource_instance = null;
    private static DatabaseReference parcelsRef;
    private static MutableLiveData<ParcelChange> parcelChange;
    private static MutableLiveData<List<Parcel>> parcelList;
    private static List<Parcel> parceldummyList;

    public interface NotifyDataChange<T> {
        void OnDataChanged(T obj);

        void onFailure(Exception exception);
    }

    private ParcelDataSource() {
        parcelChange = new MutableLiveData<ParcelChange>();
        parcelList = new MutableLiveData<List<Parcel>>();
        parceldummyList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        parcelsRef = database.getReference("test");
    }

    public static ParcelDataSource getInstance() {
        if (parcelDataSource_instance == null)
            parcelDataSource_instance = new ParcelDataSource();

        return parcelDataSource_instance;
    }


    public MutableLiveData<ParcelChange> getParcelChange() {

        return parcelChange;
    }

    public MutableLiveData<List<Parcel>> getParcelList() {

        return parcelList;
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

    public static void addParcel(Parcel parcel) {
        addParcelToFirebase(parcel);
    }

    private static void addParcelToFirebase(Parcel parcel) {
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

    private static ChildEventListener parcelRefChildEventListener;

    public static void notifyToStudentList(final NotifyDataChange<List<Parcel>> notifyDataChange) {
        if (notifyDataChange != null) {
            if (parcelRefChildEventListener != null) {
                notifyDataChange.onFailure(new Exception("first unNotify student list"));
                return;
            }
            parceldummyList.clear();

            parcelRefChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Parcel parcel = dataSnapshot.getValue(Parcel.class);
                    String key = dataSnapshot.getKey();
                    parcel.setId(key);

                    parceldummyList.add(parcel);
                    notifyDataChange.OnDataChanged(parceldummyList);
                    //parcelList.setValue(parceldummyList);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Parcel parcel = dataSnapshot.getValue(Parcel.class);
                    String id = dataSnapshot.getKey();
                    parcel.setId(id);
                    for (int i = 0; i < parceldummyList.size(); i++) {
                        if (parceldummyList.get(i).getId().equals(id)) {
                            parceldummyList.set(i, parcel);
                            break;
                        }
                    }
                    notifyDataChange.OnDataChanged(parceldummyList);

                    //parcelList.setValue(parceldummyList);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            parcelsRef.addChildEventListener(parcelRefChildEventListener);
        }


    }
    public static void stopNotifyToStudentList () {
        if (parcelRefChildEventListener != null) {
            parcelsRef.removeEventListener(parcelRefChildEventListener);
            parcelRefChildEventListener = null;
        }
    }
}