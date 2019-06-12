package com.denso.latencysimulator.Interface;

import com.denso.latencysimulator.Model.SettingItem;

import java.util.LinkedHashMap;

public interface SettingEditListener {
    void onSettingsChangeInteraction(LinkedHashMap<String, SettingItem> settings);
}
