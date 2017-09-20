package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {
    private String[] mWeatherData;

    public ForecastAdapter() { }

    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView mWeatherTextView;

        public ForecastAdapterViewHolder (View itemView) {
            super(itemView);
            mWeatherTextView = (TextView) itemView.findViewById(R.id.tv_weather_data);
        }
    }

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.forecast_list_item, parent, false);
//        return new ForecastAdapterViewHolder(view);
        return new ForecastAdapterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
//        String weather = mWeatherData[position];
//        holder.mWeatherTextView.setText(weather);
        holder.mWeatherTextView.setText(mWeatherData[position]);
    }

    @Override
    public int getItemCount() {
        if (mWeatherData == null) {
            return 0;
        } else  {
            return mWeatherData.length;
        }
    }

    public void setWeatherData(String[] weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }
}
