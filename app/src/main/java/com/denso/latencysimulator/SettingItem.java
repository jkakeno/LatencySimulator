package com.denso.latencysimulator;

import android.os.Parcel;
import android.os.Parcelable;

public class SettingItem implements Parcelable {

    String command;
    long wait;
    long duration;

    public SettingItem(String command, long wait, long duration) {
        this.command = command;
        this.wait = wait;
        this.duration = duration;
    }

    protected SettingItem(Parcel in) {
        command = in.readString();
        wait = in.readLong();
        duration = in.readLong();
    }

    public static final Creator<SettingItem> CREATOR = new Creator<SettingItem>() {
        @Override
        public SettingItem createFromParcel(Parcel in) {
            return new SettingItem(in);
        }

        @Override
        public SettingItem[] newArray(int size) {
            return new SettingItem[size];
        }
    };

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public long getWait() {
        return wait;
    }

    public void setWait(long wait) {
        this.wait = wait;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(command);
        dest.writeLong(wait);
        dest.writeLong(duration);
    }
}
