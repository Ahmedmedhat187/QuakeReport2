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
package com.example.android.quakereport;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        /*
            // Create a fake list of earthquake locations.
            ArrayList<String> earthquakesString = new ArrayList<>();
            earthquakesString.add("San Francisco");
            earthquakesString.add("London");
            earthquakesString.add("Tokyo");
            earthquakesString.add("Mexico City");
            earthquakesString.add("Moscow");
            earthquakesString.add("Rio de Janeiro");
            earthquakesString.add("Paris");

            // Create a new {@link ArrayAdapter} of earthquakes
            // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, earthquakesString);

            earthquakeListView.setAdapter(adapter);
        */


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // get array list of earthquakes
        final ArrayList<Earthquake> earthquakes =  QueryUtils.extractEarthquakes();

        EarthquakeAdapter adapter = new EarthquakeAdapter(this , earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);


        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(EarthquakeActivity.this, earthquakes.get(position).getUrl(), Toast.LENGTH_SHORT).show();
        }});



    }
}
