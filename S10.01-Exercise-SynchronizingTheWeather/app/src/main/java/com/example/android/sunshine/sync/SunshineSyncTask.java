package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

//  COMPLETED (1) Create a class called SunshineSyncTask
public class SunshineSyncTask {
    //  COMPLETED (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
    synchronized public static void syncWeather(Context context) {

        try {
            //  COMPLETED (3) Within syncWeather, fetch new weather data
            URL url = NetworkUtils.getUrl(context);
            String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(url);
            ContentValues[] weatherValues =
                    OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, jsonWeatherResponse);

            //  COMPLETED (4) If we have valid results, delete the old data and insert the new
            if (weatherValues != null && weatherValues.length > 0) {
                ContentResolver sunshineContentResolver = context.getContentResolver();

                sunshineContentResolver.delete(WeatherContract.WeatherEntry.CONTENT_URI
                    , null, null);

                sunshineContentResolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI
                    , weatherValues);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
