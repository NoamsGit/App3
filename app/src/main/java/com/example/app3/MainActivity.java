package com.example.app3;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app3.Entities.Parcel;
import com.example.app3.Entities.ParcelChange;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextId ;
    EditText editTextAddress;
    Button buttonAdd;
    MyViewModel viewModel;




    public void makeToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);

        buttonAdd  = (Button) findViewById(R.id.buttonAddArtist);



        viewModel.getParcelChange().observe((LifecycleOwner) this, new Observer<ParcelChange>() {
            @Override
            public void onChanged(ParcelChange parcelChange) {
                if (parcelChange.isSuccess()){
                    int a;
                    parcelAddedSuccessfully();
                }else {
                    parcelAddedFailure();
                }

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String id = editTextId.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();

                Parcel parcel = new Parcel(id, name, address);
                MyViewModel.addParcelToFirebase(parcel);

                //MyViewModel.addParcelToFirebase(name);
               // addParcel();

            }
        });





    }
    public void parcelAddedSuccessfully(){
        Toast.makeText(this, "Parcel added successfully", Toast.LENGTH_LONG).show();
    }
    public void parcelAddedFailure(){
        Toast.makeText(this, "Parcel added Failure", Toast.LENGTH_LONG).show();
    }
}
