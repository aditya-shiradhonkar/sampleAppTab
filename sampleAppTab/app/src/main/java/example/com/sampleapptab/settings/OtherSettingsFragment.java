package example.com.sampleapptab.settings;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Random;

import example.com.sampleapptab.R;
import example.com.sampleapptab.app.SampleAppTabApplication;
import example.com.sampleapptab.appframework.global.ConstantsApp;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherSettingsFragment extends Fragment {

    private String mProfileName;

    public OtherSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_other_server_settings, container, false);
        final TextView macIdTV = (TextView) rootView.findViewById(R.id.macIdTV);

        Profile profile;
        Bundle bundle = getArguments();
        if(bundle != null) {
            mProfileName = bundle.getString("profileName", "");
            SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(ConstantsApp.PROFILES_PREFERNCES, Context.MODE_APPEND);
            String profileJsonString = sharedPreferences.getString(mProfileName, "");
            Gson gson = new Gson();
            profile = gson.fromJson(profileJsonString, Profile.class);
        } else {
            return rootView;
        }

        macIdTV.setText(profile.getMacAddress());
        TextView macIdTitleTV = (TextView) rootView.findViewById(R.id.macIdTitleTV);
        macIdTitleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Mac ID";
                final EditText editText = new EditText(getActivity());

                editText.setText(macIdTV.getText().toString());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                editText.setLayoutParams(layoutParams);
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(ConstantsApp.PROFILES_PREFERNCES, Context.MODE_APPEND);
                        String profileJsonString = sharedPreferences.getString(mProfileName, "");
                        Gson gson = new Gson();
                        Profile newProfile = gson.fromJson(profileJsonString, Profile.class);
                        if(newProfile != null) {
                            newProfile.setMacAddress(editText.getText().toString());
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            profileJsonString = gson.toJson(newProfile, Profile.class);
                            editor.putString(newProfile.getProfileName(), profileJsonString);
                            editor.apply();
                            macIdTV.setText(newProfile.getMacAddress());
                            SampleAppTabApplication.setCurrentMacId(newProfile.getMacAddress());
                        }
                    }
                };

                showDialog(title, editText, onClickListener);
            }
        });

        return rootView;
    }

    private void showDialog(String title, EditText editText, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setView(editText);
        builder.setPositiveButton("Ok", onClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private Profile createProfile() {
        Profile profile = new Profile();
        Random random = new Random();
        int profileSuffix = random.nextInt(9999);

        String profileName = "Profile " + String.valueOf(profileSuffix);
        profile.setProfileName(profileName);

        WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();
        profile.setMacAddress(macAddress);
        profile.setUrl("http://159.203.133.86/stalker_portal/");
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(ConstantsApp.PROFILES_PREFERNCES, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String profileJsonString = gson.toJson(profile, Profile.class);
        editor.putString(profileName, profileJsonString);
        editor.apply();
        return profile;
    }

}
