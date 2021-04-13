package com.example.theparkingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static com.example.theparkingapp.ParkingLot.KEY_CREATED_AT;

public class SelectionActivity extends AppCompatActivity {
    RecyclerView rvSelection;
    public static final String TAG="SelectionActivity";
    LotsAdapter  adapter;
    List<ParkingLot> parkingLots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        //Find the recycler View
        rvSelection=findViewById(R.id.rvSelection);
        // Init the list of lots and adapter
        parkingLots=new ArrayList<>();
        adapter = new LotsAdapter(this,parkingLots);
        //Recyclerview setup: layout manager and adapter
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        rvSelection.setLayoutManager(layoutManager);
        rvSelection.setAdapter(adapter);

        queryLots();

    }

    private void queryLots() {
        // Specify which class to query
        ParseQuery<ParkingLot> query = ParseQuery.getQuery(ParkingLot.class);
        query.include(ParkingLot.KEY_LOT_NAME);
        query.setLimit(10);
        query.addAscendingOrder(KEY_CREATED_AT);
        query.findInBackground(new FindCallback<ParkingLot>() {
            @Override
            public void done(List<ParkingLot> parkingLot, ParseException e) {
                if(e!= null)
                {
                    Log.e(TAG,"Issue with getting the list of Lots");
                    return;

                }
                for(ParkingLot lot: parkingLot){
                    Log.i(TAG,"Parking Lot"+lot.getKeyLotName());
                }
                parkingLots.addAll(parkingLot);
                adapter.notifyDataSetChanged();
            }
        });


    }
}