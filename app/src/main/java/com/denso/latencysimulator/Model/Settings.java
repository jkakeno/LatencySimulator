package com.denso.latencysimulator.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedHashMap;

public class Settings implements Parcelable{

    LinkedHashMap<String,SettingItem> defaultSetting;
    LinkedHashMap<String,SettingItem> newSetting;

    public Settings() {
        defaultSetting = new LinkedHashMap<>();
        defaultSetting.put("Remote Cmd",new SettingItem("Remote Cmd",0,800));
        defaultSetting.put("POST Cmd",new SettingItem("POST Cmd",200,140));
        defaultSetting.put("Spec D",new SettingItem("Spec D",8400,800));
        defaultSetting.put("SMS",new SettingItem("SMS",6600,11200));
        defaultSetting.put("Access TSC",new SettingItem("Access TSC",4000,2000));
        defaultSetting.put("Download Cmd",new SettingItem("Download Cmd",0,800));
        defaultSetting.put("Upload Result",new SettingItem("Upload Result",4000,800));
        defaultSetting.put("Callback4",new SettingItem("Callback4",3000,400));
        defaultSetting.put("Remote Cmd Res",new SettingItem("Remote Cmd Res",220,0));
    }

    protected Settings(Parcel in) {
    }

    public static final Creator<Settings> CREATOR = new Creator<Settings>() {
        @Override
        public Settings createFromParcel(Parcel in) {
            return new Settings(in);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    };

    public LinkedHashMap<String, SettingItem> getDefaultSetting() {
        return defaultSetting;
    }

    public void setDefaultSetting(LinkedHashMap<String, SettingItem> defaultSetting) {
        this.defaultSetting = defaultSetting;
    }

    public LinkedHashMap<String, SettingItem> getNewSetting() {
        return newSetting;
    }

    public void setNewSetting(LinkedHashMap<String, SettingItem> newSetting) {
        this.newSetting = newSetting;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
