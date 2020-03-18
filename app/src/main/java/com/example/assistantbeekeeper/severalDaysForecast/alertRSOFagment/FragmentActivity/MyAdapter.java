package com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assistantbeekeeper.R;
import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.DataModel.AlertsWeatherData;
import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentAlertsPre.DateParser;
import java.util.ArrayList;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<AlertsWeatherData> articles;


     MyAdapter(ArrayList<AlertsWeatherData> articles) {
        this.articles=articles;
    }
    @Nullable
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View alertsRow=layoutInflater.inflate(R.layout.recycleview_alerts_details, parent, false);
        return new MyViewHolder(alertsRow);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        TextView titleAlert=holder.itemView.findViewById(R.id.title_alert);
        TextView timeEventStart=holder.itemView.findViewById(R.id.time_event_start);
        TextView timeEventEnd=holder.itemView.findViewById(R.id.time_event_end);
        TextView descriptionEvent=holder.itemView.findViewById(R.id.description_events);
        String validFrom;
        String validTo;

        //format date for date time event from articles
        String formatBefore="yyyy-MM-dd HH:mm:ss";
        String formatAfter="dd/MM\nHH:mm";


        DateParser dateParser=new DateParser();

        validFrom=dateParser.parseData(formatBefore, formatAfter, articles.get(position).getDateStart());
        validTo=dateParser.parseData(formatBefore, formatAfter, articles.get(position).getDateEnd());


        titleAlert.setText(articles.get(position).getTitle());
        descriptionEvent.setText(articles.get(position).getDescriptioon());
        timeEventStart.setText(validFrom);
        timeEventEnd.setText(validTo);

    }

    @Override
    public int getItemCount() {

         return articles.size();
    }
}
