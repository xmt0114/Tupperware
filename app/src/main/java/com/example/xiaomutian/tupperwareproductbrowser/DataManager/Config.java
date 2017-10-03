package com.example.xiaomutian.tupperwareproductbrowser.DataManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

/**
 * Created by Xiaomutian on 2017/10/3.
 */

public class Config {
    private static final String TAG = "Config";
    private static final String INDEX = "INDEX";
    private Context m_context;

    public Config(Context context) {
        this.m_context = context;
    }

    public void setIndex(int index) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(m_context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(INDEX, index);
        editor.apply();
    }

    public int getIndex() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(m_context);
        return pref.getInt(INDEX, 0);
    }
}
