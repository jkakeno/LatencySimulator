package com.denso.latencysimulator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface SettingSaveListener {
    void onSettingsSaveInteraction(LinkedHashMap<String,SettingItem> settings);
}
