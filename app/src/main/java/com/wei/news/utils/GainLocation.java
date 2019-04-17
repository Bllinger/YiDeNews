package com.wei.news.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.wei.base.utils.LogUtils;
import com.wei.news.config.Constant;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by 社会主义好 on 2019/4/14.
 */

public class GainLocation {

    public static String getAddress(Context context,double latitude, double longitude) {

        if (latitude == 0.0 || longitude == 0.0){
            return "来自火星";
        }else{
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());

            try {
                List<Address> addresses = geocoder.getFromLocation(latitude,
                        longitude, 1);
                if (addresses.size() > 0) {
                    Address address = addresses.get(0);
                    String data = address.toString();
                    int startCity = data.indexOf("1:\"") + "1:\"".length();
                    int endCity = data.indexOf("\"", startCity);
                    String city = data.substring(startCity, endCity);

                    int startPlace = data.indexOf("feature=") + "feature=".length();
                    int endplace = data.indexOf(",", startPlace);
                    String place = data.substring(startPlace, endplace);
                    return city + place ;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "来自火星";
            }
        }

}
