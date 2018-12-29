package com.example.android.quakereport;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolderItems viewHolderItems;
        Earthquake earthquake = getItem(position);
        String primaryLocation;
        String locationOffset;
        final String LOCATION_SEPARATOR = " of ";

        if (convertView == null) {
            viewHolderItems = new ViewHolderItems();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_item, parent , false);
            viewHolderItems.magText = (TextView)convertView.findViewById(R.id.magText);
            viewHolderItems.timeText = (TextView)convertView.findViewById(R.id.timeText);
            viewHolderItems.dateText = (TextView)convertView.findViewById(R.id.dateText);
            viewHolderItems.placeText = (TextView)convertView.findViewById(R.id.placeText);
            viewHolderItems.countryText = (TextView)convertView.findViewById(R.id.countryText);
            convertView.setTag(viewHolderItems);
        }
        else{
            viewHolderItems = (ViewHolderItems)convertView.getTag();
        }

        Date dateObject = new Date(earthquake.getTime());

        DecimalFormat  decimalFormat = new DecimalFormat("0.0");

        if ((earthquake.getPlace()).contains(LOCATION_SEPARATOR)) {

            String[] parts = (earthquake.getPlace()).split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = "Near of The";
            primaryLocation = (earthquake.getPlace());
        }

        GradientDrawable magnitudeCircle  = (GradientDrawable) viewHolderItems.magText.getBackground();
        int magnitudeColor = getMagnitudeColor(earthquake.getMag());
        magnitudeCircle.setColor(magnitudeColor);

        viewHolderItems.magText.setText(""+ decimalFormat.format(earthquake.getMag()));
        viewHolderItems.placeText.setText("" + locationOffset);
        viewHolderItems.countryText.setText(""+ primaryLocation);
        viewHolderItems.timeText.setText(""+ formatDate(dateObject));
        viewHolderItems.dateText.setText(""+formatTime(dateObject));

        return convertView;
    }


    private String formatDate (Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd LLL yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime (Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:
            case 1: magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2: magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3: magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4: magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5: magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6: magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7: magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8: magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9: magnitudeColorResourceId = R.color.magnitude9;
                break;
            default: magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext() , magnitudeColorResourceId);
    }


    private static class ViewHolderItems {
        private TextView magText ;
        private TextView placeText;
        private TextView countryText;
        private TextView timeText;
        private TextView dateText;
    }


}
