package com.example.theparkingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class GetDirection extends AppCompatActivity {
    private int REQUEST_CODE = 111;
    private FusedLocationProviderClient client;
    private SupportMapFragment mapFragment;
    private TextView lot;
    private String lotname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_direction);

        Intent intent = getIntent();
        lotname = intent.getStringExtra("Lot");
        lot = findViewById(R.id.txtLotName);
        lot.setText(lotname);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);

        client = LocationServices.getFusedLocationProviderClient(GetDirection.this);
        if(ActivityCompat.checkSelfPermission(GetDirection.this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){

            getPointsLocation();
        }
        else{
            ActivityCompat.requestPermissions(GetDirection.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }


    }

    private void getPointsLocation() {
        @SuppressLint("MissingPermission") Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){

                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                           LatLng latlng = new LatLng(32.7261602, -97.1110038);

                           MarkerOptions markerOptions = new MarkerOptions().position(latlng).title(lotname);
                            googleMap.setMyLocationEnabled(true);
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));

                            googleMap.addMarker(markerOptions).showInfoWindow();
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if (requestCode == REQUEST_CODE){
           if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
               getPointsLocation();
           }
       }else{
           Toast.makeText(this, "Permission Denied..", Toast.LENGTH_SHORT).show();
       }
    }

}