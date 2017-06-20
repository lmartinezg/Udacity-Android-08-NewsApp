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
package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

//import static com.example.android.newsapp.MainActivity.context;

/**
 * An {@link NewsItemAdapter} knows how to create a list item layout for each news item
 * in the data source (a list of {@link NewsItem} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsItemAdapter extends ArrayAdapter<NewsItem> {

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Constructs a new {@link NewsItemAdapter}.
     *
     * @param context of the app
     * @param newsItems is the list of news items, which is the data source of the adapter
     */
    public NewsItemAdapter(Context context, List<NewsItem> newsItems) {
        super(context, 0, newsItems);
    }

    /**
     * Returns a list item view that displays information about the news item at the given position
     * in the list of news items.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Find the news item at the given position in the list of news items
        NewsItem currentNewsItem = getItem(position);


        // Published Date

        // Find the TextView with view ID published_date
        TextView publishedDate = (TextView) listItemView.findViewById(R.id.published_date);

        // Convert the published date from ISO JSON format to java Date type
//        SimpleDateFormat sdf_ISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            String iso8601string = currentNewsItem.getWebPublicationDate();
            String s = iso8601string.replace("Z", "+0000");
//            String s = iso8601string.replace("Z", "+00:00");
//            s = s.substring(0, 22) + s.substring(23);  // to get rid of the ":"
            Date dateTime = null;
        try {
            dateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Convert the date to user format
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(dateTime);

        String dateOut;
        DateFormat dateFormatter;

        //Locale locale = Locale.getDefault();
//        dateFormatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, locale);
        dateFormatter = DateFormat.getDateTimeInstance();
        dateOut = dateFormatter.format(dateTime);
        publishedDate.setText(dateOut);


        // Section

        // Find the TextView with view ID published_date
        TextView sectionName = (TextView) listItemView.findViewById(R.id.section_name);
        sectionName.setText(currentNewsItem.getSectionName());


        // Title

        // Find the TextView with view ID title
        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentNewsItem.getWebTitle());


        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }


    /**
     * Converts the date/time format from JSON response to default locale format
     */
    private String formatDateTime(String dateTimeISO) {

        SimpleDateFormat sdf_ISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        Date date = null;
        try {
            date = sdf_ISO.parse(dateTimeISO);
        } catch (ParseException e) {
            // handle exception here !
        }
        String resultDateTime = DateFormat.getDateTimeInstance().format(date);

        return resultDateTime;
    }
}
