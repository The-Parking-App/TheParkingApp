package com.example.theparkingapp;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import static java.lang.Integer.parseInt;

@ParseClassName("ParkingLot")
public class ParkingLot extends ParseObject {
    public static final String KEY_LOT_NAME = "lot_name";
    public static final String KEY_LOT_NUMBER = "lot_number";
    public static final String KEY_LOT_LONG = "lot_long";
    public static final String KEY_LOT_LAT = "lot_lat";
    public static final String KEY_FREE_SPACE = "free_space_count";
    public static final String CREATED_AT = "createdAt";
    public static final String KEY_LOT_ID = "objectId";
    public static final String KEY_IMAGE = "thumbnail";

    public String getKeyLotName() {
        return getString(KEY_LOT_NAME);
    }

    public String getKeyLotId() {return getString(KEY_LOT_ID);}

    public void setKeyLotName(String lot_name) { put(KEY_LOT_NAME, lot_name); }

    public Integer getKeyLotNumber() { return getInt(KEY_LOT_NUMBER); }

    public void setKeyLotNumber(String lot_number) { put(KEY_LOT_LONG, lot_number); }

    public Integer getKeyFreeSpace() { return getInt(KEY_FREE_SPACE); }

    public void setKeyFreeSpace(String free_space_count) { put(KEY_FREE_SPACE, parseInt(free_space_count)); }
    public double getKeyLotLong() { return getDouble(KEY_LOT_LONG); }
    public void setKeyLotLong(String lot_long) { put(KEY_LOT_LONG, lot_long); }

    public double getKeyLotLat() { return getDouble(KEY_LOT_LAT); }
    public void setKeyLotLat(String lot_lat) { put(KEY_LOT_LONG, lot_lat); }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }


}


