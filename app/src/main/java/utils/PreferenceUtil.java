package utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {

    public static final String SHARED_PREF_FILE_NAME = "";
    public static final String SHARED_PREF_KEY = "";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public PreferenceUtil(Context context) {
        pref = context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void putData(boolean data) {
        editor.putBoolean(SHARED_PREF_KEY, data);
        editor.apply();
    }

    public boolean getData() {
        return pref.getBoolean(SHARED_PREF_KEY, false);
    }
}
