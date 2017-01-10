package example.com.sampleapptab.app;

import android.app.Application;

import framework.global.Logger;

/**
 * Created by aniruddhatr on 12/22/2016.
 */

public class SampleAppTabApplication extends Application {
    public static final String TAG = "SampleAppTabApplication";

    private static String sAccessToken;
    private static String sCurrentMacId;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i(TAG, "onCreate");

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
