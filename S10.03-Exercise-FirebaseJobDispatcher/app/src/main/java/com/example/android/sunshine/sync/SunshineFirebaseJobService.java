package com.example.android.sunshine.sync;

// COMPLETED (2) Make sure you've imported the jobdispatcher.JobService, not job.JobService
import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

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



// COMPLETED (3) Add a class called SunshineFirebaseJobService that extends jobdispatcher.JobService
public class SunshineFirebaseJobService extends JobService {

    //  COMPLETED (4) Declare an ASyncTask field called mFetchWeatherTask
    private AsyncTask<Void, Void, Void> mFetchWeatherTask;

    //  COMPLETED (5) Override onStartJob and within it, spawn off a separate ASyncTask to sync weather data
    @Override
    public boolean onStartJob(JobParameters job) {
        mFetchWeatherTask = new mFetchWeatherTask(this, job).execute();
        return true;
    }

//  COMPLETED (7) Override onStopJob, cancel the ASyncTask if it's not null and return true
    @Override
    public boolean onStopJob(JobParameters job) {
        if (mFetchWeatherTask != null) {
            mFetchWeatherTask.cancel(true);
        }
        return false;
    }
}

class mFetchWeatherTask extends AsyncTask<Void, Void, Void> {

    @SuppressLint("StaticFieldLeak")
    private JobService mJobService;
    private JobParameters mJob;

    public mFetchWeatherTask(JobService jobService, JobParameters job) {
        mJobService = jobService;
        mJob = job;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // TODO Check if JobService is correct instead of application context
        SunshineSyncTask.syncWeather(mJobService);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //  COMPLETED (6) Once the weather data is sync'd, call jobFinished with the appropriate arguments
        mJobService.jobFinished(mJob, false);
    }
}