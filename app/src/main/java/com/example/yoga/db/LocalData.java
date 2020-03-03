package com.example.yoga.db;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalData {

    private static final String APP_SHARED_PREFS = "RemindMePref";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private static final String reminderStatus = "reminderStatus";
    private static final String hour = "hour";
    private static final String min = "min";

    public LocalData(Context context) {

        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.apply();
    }

    // Postavuvanje potsetnik
    public boolean getReminderStatus()
    {
        return appSharedPrefs.getBoolean(reminderStatus, false);
    }

    public void setReminderStatus(boolean status){
        prefsEditor.putBoolean(reminderStatus, status);
        prefsEditor.commit();
    }

    // Vreme koga e setiran potsetnikot (cas)
    public int get_hour()
    {
        return appSharedPrefs.getInt(hour, 8);
    }

    public void set_hour(int h){
        prefsEditor.putInt(hour, h);
        prefsEditor.commit();
    }

    // Vreme kog e setiran potsetnikot (minuti)
    public int get_min()
    {
        return appSharedPrefs.getInt(min, 0);
    }

    public void set_min(int m){
        prefsEditor.putInt(min, m);
        prefsEditor.commit();
    }

    public void reset(){
        prefsEditor.clear();
        prefsEditor.commit();
    }
}
