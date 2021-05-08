package com.example.theparkingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import static com.example.theparkingapp.ParkingLot.CREATED_AT;

public class SelectionActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeContainer;

    RecyclerView rvSelection;
    public static final String TAG="SelectionActivity";
    LotsAdapter  adapter;
    List<ParkingLot> parkingLots;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        // Lookup Swipe Container View
        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_dark,
                android.R.color.white,
                android.R.color.holo_orange_dark,
                android.R.color.white);
        // setup refresh listner with triggers new data loading
        //Find the recycler View
        rvSelection=findViewById(R.id.rvSelection);
        // Init the list of lots and adapter
        parkingLots=new ArrayList<>();
        adapter = new LotsAdapter(this,parkingLots);
        //Recyclerview setup: layout manager and adapter
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        rvSelection.setLayoutManager(layoutManager);
        rvSelection.setAdapter(adapter);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                queryLots();
                swipeContainer.setRefreshing(false);
            }
        });

        queryLots();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        Drawable drawable = menu.findItem(R.id.action_logout).getIcon();

        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,R.color.white));
        menu.findItem(R.id.action_logout).setIcon(drawable);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        ParseUser.logOutInBackground(e -> { });
        Intent intent = new Intent(SelectionActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void queryLots() {
        // Specify which class to query
        ParseQuery<ParkingLot> query = ParseQuery.getQuery(ParkingLot.class);
        query.include(ParkingLot.KEY_LOT_NAME);
        query.setLimit(10);
        query.addAscendingOrder(CREATED_AT);
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