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
package com.example.android.sunshine.sync;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.sunshine.data.WeatherContract;


public class SunshineSyncUtils {

    //  COMPLETED (1) Declare a private static boolean field called sInitialized
    private static boolean sInitialized;

    //  COMPLETED (2) Create a synchronized public static void method called initialize
    synchronized public static void initialize(Context context) {
        //  COMPLETED (3) Only execute this method body if sInitialized is false
        if (!sInitialized) {
            new fillContentProvider(context).execute();
            //  COMPLETED (4) If the method body is executed, set sInitialized to true
            sInitialized = true;
        }
    }

    /**
     * Helper method to perform a sync immediately using an IntentService for asynchronous
     * execution.
     *
     * @param context The Context used to start the IntentService for the sync.
     */
    public static void startImmediateSync(@NonNull final Context context) {
        Intent intentToSyncImmediately = new Intent(context, SunshineSyncIntentService.class);
        context.startService(intentToSyncImmediately);
    }

    private static class fillContentProvider extends AsyncTask<Void, Void, Void> {

        @SuppressLint("StaticFieldLeak")
        Context mContext;

        public fillContentProvider(Context context) { mContext = context; }

        @Override
        protected Void doInBackground(Void... voids) {

            Uri forecastQueryUri = WeatherContract.WeatherEntry.CONTENT_URI;
            String[] projectionColumns = {WeatherContract.WeatherEntry._ID};
            String selectionStatement = WeatherContract.WeatherEntry
                    .getSqlSelectForTodayOnwards();

            Cursor cursor = mContext.getContentResolver().query(
                    forecastQueryUri
                    ,projectionColumns
                    ,selectionStatement
                    ,null
                    ,null
            );

            //  COMPLETED (5) Check to see if our weather ContentProvider is empty
            if (cursor == null || cursor.getCount() == 0) {
                //  COMPLETED (6) If it is empty or we have a null Cursor, sync the weather now!
                startImmediateSync(mContext);
            }

            if (cursor != null) {
                cursor.close();
            }

            return null;
        }

    }
}