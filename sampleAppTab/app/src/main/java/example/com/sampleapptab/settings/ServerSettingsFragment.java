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
public class ServerSettingsFragment extends Fragment {

    public ServerSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_server_settings, container, false);
        final TextView profileNameTV = (TextView) rootView.findViewById(R.id.profileNameTV);

        Profile profile;
        Bundle bundle = getArguments();
        if(bundle != null) {
            String profileName = bundle.getString("profileName", "");
            SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(ConstantsApp.PROFILES_PREFERENCES, Context.MODE_APPEND);
            String profileJsonString = sharedPreferences.getString(profileName, "");
            Gson gson = new Gson();
            profile = gson.fromJson(profileJsonString, Profile.class);
            if(profile == null) {
                profile = createProfile();
            }
        } else {
            profile = createProfile();
        }

        profileNameTV.setText(profile.getProfileName());
        TextView serverNameTV = (TextView) rootView.findViewById(R.id.serverNameTV);
        serverNameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Server Name";
                final EditText editText = new EditText(getActivity());

                editText.setText(profileNameTV.getText().toString());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                editText.setLayoutParams(layoutParams);
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String oldProfileName = profileNameTV.getText().toString();

                        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(ConstantsApp.PROFILES_PREFERENCES, Context.MODE_APPEND);
                        String profileJsonString = sharedPreferences.getString(oldProfileName, "");
                        Gson gson = new Gson();
                        Profile newProfile = gson.fromJson(profileJsonString, Profile.class);
                        if(newProfile != null) {
                            newProfile.setProfileName(editText.getText().toString());
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            profileJsonString = gson.toJson(newProfile, Profile.class);
                            editor.remove(oldProfileName);
                            editor.putString(newProfile.getProfileName(), profileJsonString);
                            editor.apply();
                            profileNameTV.setText(newProfile.getProfileName());
                        }
                    }
                };

                showDialog(title, editText, onClickListener);
            }
        });

        final TextView urlTV = (TextView) rootView.findViewById(R.id.urlTV);
        urlTV.setText(profile.getUrl());

        final TextView urlTitleTV = (TextView) rootView.findViewById(R.id.urlTitleTV);
        urlTitleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "URL";
                final EditText editText = new EditText(getActivity());
                editText.setText(urlTV.getText().toString());

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                editText.setLayoutParams(layoutParams);
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String oldProfileName = profileNameTV.getText().toString();

                        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(ConstantsApp.PROFILES_PREFERENCES, Context.MODE_APPEND);
                        String profileJsonString = sharedPreferences.getString(oldProfileName, "");
                        Gson gson = new Gson();
                        Profile newProfile = gson.fromJson(profileJsonString, Profile.class);
                        if(newProfile != null) {
                            newProfile.setUrl(editText.getText().toString());
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            profileJsonString = gson.toJson(newProfile, Profile.class);
                            editor.remove(oldProfileName);
                            editor.putString(newProfile.getProfileName(), profileJsonString);
                            editor.apply();
                            urlTV.setText(newProfile.getUrl());
                        }
                    }
                };

                showDialog(title, editText, onClickListener);
            }
        });

        TextView otherSettingsTV = (TextView) rootView.findViewById(R.id.otherSettingsTV);
        otherSettingsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtherSettingsFragment otherSettingsFragment = new OtherSettingsFragment();

                Bundle bundle = new Bundle();
                bundle.putString("profileName", profileNameTV.getText().toString());
                otherSettingsFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, otherSettingsFragment)
                        .commit();
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
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(ConstantsApp.PROFILES_PREFERENCES, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String profileJsonString = gson.toJson(profile, Profile.class);
        editor.putString(profileName, profileJsonString);
        editor.apply();
        return profile;
    }

}
