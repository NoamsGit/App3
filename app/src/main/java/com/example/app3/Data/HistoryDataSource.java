package com.example.app3.Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.app3.Entities.Parcel;
import com.example.app3.Entities.ParcelDAO;
import com.example.app3.Entities.ParcelDatabase;

import java.util.List;

public class HistoryDataSource {
    private ParcelDAO parcelDAO;
    private ParcelDatabase parcelDatabase;

    private MutableLiveData<List<Parcel>> allParcels;

    public HistoryDataSource(Application application) {
        parcelDatabase  = ParcelDatabase.getInstance(application);
        parcelDAO = parcelDatabase.parcelDAO();
        allParcels.setValue(parcelDAO.getAllParcels());



    }


// -------- expose API for upper layers --------
    public void insert(Parcel parcel ) {
        new InsertNoteAsyncTask(parcelDAO).execute(parcel);
    }
    public void update(Parcel parcel) {
        new UpdateNoteAsyncTask(parcelDAO).execute(parcel);
    }
    public void delete(Parcel parcel) {
        new DeleteNoteAsyncTask(parcelDAO).execute(parcel);
    }
    public void deleteAllNotes(){
        new DeleteAllNoteAsyncTask(parcelDAO).execute();
    }
    public Parcel getParcelById(String id){
        return parcelDAO.getParcelById(id);
    }
    public MutableLiveData<List<Parcel>> getAllParcels(){
        return allParcels; }

// --------Internal classes --------
    public static class InsertNoteAsyncTask extends AsyncTask<Parcel,Void,Void> {
        private ParcelDAO parcelDAO;
        public InsertNoteAsyncTask(ParcelDAO parcelDAO) {
            this.parcelDAO = parcelDAO;
        }
        @Override
        protected Void doInBackground(Parcel... parcels) {
            parcelDAO.insert(parcels[0]);
            return null;
        }
    }

    public static class UpdateNoteAsyncTask extends AsyncTask<Parcel,Void,Void> {
        private ParcelDAO parcelDAO;
        public UpdateNoteAsyncTask(ParcelDAO parcelDAO) {
            this.parcelDAO = parcelDAO;
        }
        @Override
        protected Void doInBackground(Parcel... parcels) {
            parcelDAO.update(parcels[0]);
        return null;
        }
    }
    public static class DeleteNoteAsyncTask extends AsyncTask<Parcel,Void,Void> {
        private ParcelDAO parcelDAO;
        public DeleteNoteAsyncTask(ParcelDAO parcelDAO) {
            this.parcelDAO = parcelDAO;
        }
        @Override
        protected Void doInBackground(Parcel... parcels) {
            parcelDAO.delete(parcels[0]);
            return null;
        }
    }
    public static class DeleteAllNoteAsyncTask extends AsyncTask<Parcel,Void,Void> {
        private ParcelDAO parcelDAO;
        public DeleteAllNoteAsyncTask(ParcelDAO parcelDAO) {
            this.parcelDAO = parcelDAO;
        }
        @Override
        protected Void doInBackground(Parcel... parcels) {
            parcelDAO.deleteAllNotes();
            return null;
        }

    }




}
