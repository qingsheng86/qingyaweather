package com.qingyaweather.android.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.qingyaweather.android.db.City;
import com.qingyaweather.android.db.County;
import com.qingyaweather.android.db.Province;
import com.qingyaweather.android.gson.Weather;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utility{
    public static boolean provinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray provinceArray=new JSONArray(response);
                for(int i=0;i<provinceArray.length();i++){
                    JSONObject provinceObject=provinceArray.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }return false;

    }
    public static boolean cityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray cityArray=new JSONArray(response);
                for(int i=0;i<cityArray.length();i++){
                    JSONObject cityObject=cityArray.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }return false;
    }
    public static boolean countyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray countyArray=new JSONArray(response);
                for(int i=0;i<countyArray.length();i++){
                    JSONObject countyObject=countyArray.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }return false;
    }
    //将返回的JSON数据解析成Weather实体类
    public static Weather WeatherResponse(String response){
        try{
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }
}
