package com.qingyaweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Forecast {
    public String date;
    public Cond cond;

    public class Cond {
        @SerializedName("txt_d")
        public String info;
    }

    @SerializedName("tmp")
    public Temperature temperature;

    public class Temperature {
        public String max;
        public String min;
    }
}
