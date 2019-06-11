package com.denso.latencysimulator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface SettingEditListener {
    void onSettingsChangeInteraction(LinkedHashMap<String,SettingItem> settings);
}
