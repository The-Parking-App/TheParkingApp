package com.example.theparkingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static com.example.theparkingapp.ParkingLot.CREATED_AT;
import static java.security.AccessController.getContext;

public class LotsAdapter extends RecyclerView.Adapter<LotsAdapter.ViewHolder> {
    private String TAG = "Lot";
    private Context context;
    private List <ParkingLot> parkingLots;
    private ParkingSpace parkingSpace = new ParkingSpace();
    private String lat;
    private String lon;
    private int spaceNo;
    private int parkinglotno;

    public LotsAdapter(Context context,List<ParkingLot> parkingLots)
    {
        this.context=context;
        this.parkingLots=parkingLots;
    }

    //Pass in the List of Parking Lots

    //For each row inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_selection,parent,false);
        return new ViewHolder(view);
    }
    //Bind Values Based on the position of elements
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //Get the data at the view Holder
        ParkingLot parkingLot= parkingLots.get(position);
        //Bind the data with View Holder
        holder.bind(parkingLot);
    }

    @Override
    public int getItemCount()
    {
        return parkingLots.size();
    }

    public void clear() {
        parkingLots.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<ParkingLot> list)
    {
        parkingLots.addAll(list);
        notifyDataSetChanged();
    }
    //Define a ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLotName;
        TextView tvFreeSpace;
        Button btnDirection;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLotName=itemView.findViewById(R.id.tvLotName);
            tvFreeSpace=itemView.findViewById(R.id.tvFreeSpace);
            btnDirection=itemView.findViewById(R.id.btnDirection);
        }

        public void bind(ParkingLot parkingLot) {
            tvLotName.setText(parkingLot.getKeyLotName());
            tvFreeSpace.setText(String.valueOf(parkingLot.getKeyFreeSpace())+" Available Spaces");
            tvFreeSpace.setText(String.valueOf(parkingLot.getKeyFreeSpace())+" Available Spaces");
//            Log.i(TAG, parkingLot.getKeyLotNumber()+ "  latitude: " + parkingLot.getKeyLotLat() + "  longitude: " + parkingLot.getKeyLotLong());
            queryspaces();
            btnDirection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setParkingstatus();
                    decreaseAvailableSpace();
                  //  parkingLot.setKeyFreeSpace("100");
                    Log.i(TAG, lat + lon);
                    Uri gmmIntentUri = Uri.parse("google.navigation:q="+lat+","+lon);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);

//                    Intent i = new Intent(context , GetDirection.class);
//
//                    i.putExtra("Lot", parkingLot.getKeyLotName() );
//                    i.putExtra("lot_long", parkingLot.getKeyLotLong());
//                    i.putExtra("lot_lat", parkingLot.getKeyLotLat());
//                    context.startActivity(i);

                }


            });

        }
    }

    //decrease the total available space by 1
    private void decreaseAvailableSpace() {
        ParseQuery<ParkingLot> query = ParseQuery.getQuery(ParkingLot.class);
        query.whereEqualTo("lot_number", parkinglotno);
        query.getFirstInBackground(new GetCallback<ParkingLot>() {
            @Override
            public void done(ParkingLot object, ParseException e) {
                if(e == null){
                    object.put("free_space_count",object.getKeyFreeSpace()-1 );
                    object.saveInBackground();

                }
            }
        });
    }

    //set the status unavailable
    private void setParkingstatus() {
        ParseQuery<ParkingSpace> query = ParseQuery.getQuery(ParkingSpace.class);
        query.whereEqualTo("ParkingSpace", spaceNo);
        query.getFirstInBackground(new GetCallback<ParkingSpace>() {
            @Override
            public void done(ParkingSpace p, ParseException e) {
                if (e == null) {
                    p.put("status", 0); //update the space as taken
                    p.saveInBackground();
                }
            }
        });
    }

    //query available space
    private void queryspaces() {
        ParseQuery<ParkingSpace> query = ParseQuery.getQuery(ParkingSpace.class);
        query.whereEqualTo("status", 1);
        query.getFirstInBackground(new GetCallback<ParkingSpace>() {

            @Override
            public void done(ParkingSpace p, ParseException e) {
                if (e == null) {
                    parkingSpace =  p;
                    lat = String.valueOf(parkingSpace.getSpaceLat());
                    lon = String.valueOf(parkingSpace.getSpaceLon());
                    spaceNo = parkingSpace.getSpaceNumber();
                    parkinglotno = parkingSpace.getLotNumber();
                }
            }
        });
    }
}
