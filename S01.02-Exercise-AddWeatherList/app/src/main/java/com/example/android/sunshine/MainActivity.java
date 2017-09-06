/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // COMPLETED (1) Create a field to store the weather display TextView
    private TextView mWeatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        // COMPLETED (2) Use findViewById to get a reference to the weather display TextView
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);

        // CIMPLETED (3) Create an array of Strings that contain fake weather data
        String[] mDummyWeatherData = {
                "Placeholder Weather 001"
                ,"Placeholder Weather 002"
                ,"Placeholder Weather 003"
                ,"Placeholder Weather 004"
                ,"Placeholder Weather 005"
                ,"Placeholder Weather 006"
                ,"Placeholder Weather 007"
                ,"Placeholder Weather 008"
                ,"Placeholder Weather 009"
                ,"Placeholder Weather 010"
                ,"Placeholder Weather 011"
                ,"Placeholder Weather 012"
                ,"Placeholder Weather 013"
                ,"Placeholder Weather 014"
        };

        // COMPLETED (4) Append each String from the fake weather data array to the TextView
        for (String mDummyWeather: mDummyWeatherData) {
            mWeatherTextView.append(mDummyWeather + "\n\n\n");
        }
    }
}