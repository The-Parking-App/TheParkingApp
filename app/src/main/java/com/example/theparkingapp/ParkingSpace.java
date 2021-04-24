package com.example.theparkingapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("ParkingSpace")
public class ParkingSpace extends ParseObject {

    public static final String KEY_LOT_NUMBER = "ParkingLot";
    public static final String KEY_SPACE_NUMBER = "ParkingSpace";
    public static final String KEY_SPACE_DISTANCE = "distance";
    public static final String KEY_SPACE_LONG = "longitude";
    public static final String KEY_SPACE_LAT = "latitude";
    public static final String KEY_SPACE_STATUS = "status";
    public static final String KEY_SPACE_TYPE = "space_type";
    public static final String KEY_SPACE_UPDATE = "updatedAt";
    public static final String CREATED_AT = "createdAt";

    public int getLotNumber() { return getInt("ParkingLot");}
    public int getSpaceNumber(){ return getInt("ParkingSpace");}
    public double getSpaceDistance() { return getDouble("distance");}
    public double getSpaceLon() { return getDouble("longitude");}
    public double getSpaceLat() { return getDouble("latitude");}
    public String getUpdateAt() {return getString("updatedAt");}
    public int getSpaceStatus() { return getInt("status");}
    public void setSpaceStatus(int status) { put(KEY_SPACE_STATUS,status);}

}
