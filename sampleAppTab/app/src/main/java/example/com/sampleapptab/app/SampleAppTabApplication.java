package example.com.sampleapptab.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import example.com.sampleapptab.appframework.global.ConstantsApp;
import example.com.sampleapptab.settings.Profile;
import framework.global.Logger;

/**
 * Created by aniruddhatr on 12/22/2016.
 */

public class SampleAppTabApplication extends Application {
    public static final String TAG = "SampleAppTabApplication";

    private static String sAccessToken;
    private static String sCurrentMacId;
    private static String sSelectedProfileName;

    public void setSelectedProfileName(String selectedProfileName) {
        SampleAppTabApplication.sSelectedProfileName = selectedProfileName;

        SharedPreferences sharedPreferences = getSharedPreferences(ConstantsApp.SELECTED_PROFILES_PREFERENCES, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selected_profile_name", selectedProfileName);
        editor.apply();
    }

    public String getSelectedProfileName() {
        SharedPreferences sharedPreferences = getSharedPreferences(ConstantsApp.SELECTED_PROFILES_PREFERENCES, Context.MODE_APPEND);
        sSelectedProfileName = sharedPreferences.getString("selected_profile_name", "-1");
        return sSelectedProfileName;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i(TAG, "onCreate");
        String selectedProfileName = getSelectedProfileName();
        if(!selectedProfileName.equals("-1")) {
            SharedPreferences sharedPreferences = getSharedPreferences(ConstantsApp.PROFILES_PREFERENCES, Context.MODE_APPEND);
            String profileJsonString = sharedPreferences.getString(selectedProfileName, "");
            Gson gson = new Gson();
            Profile selectedProfile = gson.fromJson(profileJsonString, Profile.class);
            if (selectedProfile != null) {
                setCurrentMacId(selectedProfile.getMacAddress());
            }
        }
        Logger.setLogging();
    }

    public static String getAccessToken() {
        return sAccessToken;
    }

    public static void setAccessToken(String accessToken) {
        sAccessToken = accessToken;
    }

    public static String getCurrentMacId() {
        return sCurrentMacId;
    }

    public static void setCurrentMacId(String sCurrentMacId) {
        SampleAppTabApplication.sCurrentMacId = sCurrentMacId;
    }
}
