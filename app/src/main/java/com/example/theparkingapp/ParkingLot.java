package com.example.theparkingapp;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

@ParseClassName("ParkingLot")
public class ParkingLot extends ParseObject {
    public static final String KEY_LOT_NAME = "lot_name";
    public static final String KEY_LOT_NUMBER = "lot_number";
    public static final String KEY_LOT_LONG = "lot_long";
    public static final String KEY_LOT_LAT = "lot_lat";
    public static final String KEY_CREATED_AT = "createdAt";


    public String getKeyLotName() {
        return getString(KEY_LOT_NAME);
    }

    public void setKeyLotName(String lot_name) { put(KEY_LOT_NAME, lot_name); }

    public String getKeyLotNumber() { return getString(KEY_LOT_NUMBER); }

    public void setKeyLotNumber(String lot_number) { put(KEY_LOT_LONG, lot_number); }

    public String getKeyLotLong() { return getString(KEY_LOT_LONG); }
    public void setKeyLotLong(String lot_long) { put(KEY_LOT_LONG, lot_long); }

    public String getKeyLotLat() { return getString(KEY_LOT_LAT); }
    public void setKeyLotLat(String lot_lat) { put(KEY_LOT_LONG, lot_lat); }


}


