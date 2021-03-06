package com.oc.bashalir.mynews.Controllers.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class Utilities {

    /**
     * change the date according to the pattern
     *
     * @param dateSend
     * @param pattern
     * @return
     */
    public String DateShortFormatter(String dateSend, String pattern) {

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        Date dateNews = formatter.parse(dateSend, pos);

        return DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE).format(dateNews);

    }

    /**
     * change the date according to the pattern
     *
     * @param dateSend
     * @param pattern
     * @return
     */
    public String DateFormatterSearch(String dateSend, String pattern) {

        final String OLD_FORMAT = pattern;
        final String NEW_FORMAT = "yyyyMMdd";

        String oldDateString = dateSend;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = null;
        try {
            d = sdf.parse(oldDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern(NEW_FORMAT);
        return sdf.format(d);

    }

    /**
     * change the date according to the pattern
     *
     * @param dateString
     * @return
     */
    public String DateShortFormatterSearch(String dateString) {
        String articleDate = "";
        if (dateString.contains("Z")) {

            articleDate = new Utilities().DateShortFormatter(dateString, "yyyy-MM-dd'T'HH:mm:ss'Z'");
        } else {
            articleDate = new Utilities().DateShortFormatter(dateString, "yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        }

        return articleDate;
    }

    /**
     * Debug retrofit http request
     *
     * @return
     */
    public okhttp3.OkHttpClient.Builder debugRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        return httpClient;
    }

    ;


}
