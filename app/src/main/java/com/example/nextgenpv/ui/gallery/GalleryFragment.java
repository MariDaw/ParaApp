package com.example.nextgenpv.ui.gallery;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;

import android.widget.ProgressBar;

import com.example.nextgenpv.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.lifecycle.ViewModelProvider;

import com.example.nextgenpv.databinding.FragmentGalleryBinding;

import java.net.HttpCookie;

public class GalleryFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("revol");

        final TextView mRevolTextView = root.findViewById(R.id.revolTextView);
        final ProgressBar mRevolProgressBar = root.findViewById(R.id.revolProgressBar);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                mRevolTextView.setText(value+" rpm");

                assert value != null;
                mRevolProgressBar.setProgress(Integer.parseInt(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


        DatabaseReference tempRef = database.getReference("temperature");
        final TextView mTemperatureTextView = root.findViewById(R.id.temperatureTextView);

        tempRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                mTemperatureTextView.setText(value+ "ºC");

                assert value != null;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference velRef = database.getReference("velocity");
        final TextView mVelocityTextView = root.findViewById(R.id.velocityTextView);

        velRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                mVelocityTextView.setText(value+ "km/h");

                assert value != null;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference humRef = database.getReference("humidity");
        final TextView mHumidityTextView = root.findViewById(R.id.humidityTextView);

        humRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                mHumidityTextView.setText(value+ "%");

                assert value != null;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });



        return root;
    }


}