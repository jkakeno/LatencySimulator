package com.denso.latencysimulator.Interface;

import com.denso.latencysimulator.Model.SettingItem;

import java.util.LinkedHashMap;

public interface SettingSaveListener {
    void onSettingsSaveInteraction(LinkedHashMap<String, SettingItem> settings);
}
