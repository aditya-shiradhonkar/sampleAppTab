package example.com.sampleapptab.settings;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import example.com.sampleapptab.R;
import example.com.sampleapptab.app.SampleAppTabApplication;
import example.com.sampleapptab.appframework.global.ConstantsApp;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilesFragment extends Fragment {


    public ProfilesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout rootView = (RelativeLayout) inflater.inflate(R.layout.fragment_profiles, container, false);
        Button addProfileButton = (Button) rootView.findViewById(R.id.addProfileButton);
        addProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerSettingsFragment serverSettingsFragment = new ServerSettingsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, serverSettingsFragment)
                        .commit();
            }
        });

        Button selectProfileButton = (Button) rootView.findViewById(R.id.selectProfileButton);
        selectProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Select Profile";
                SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(ConstantsApp.PROFILES_PREFERENCES, Context.MODE_APPEND);

                Map<String, ?> allProfiles = sharedPreferences.getAll();
                Set<String> keySet = allProfiles.keySet();
                Iterator<String> iterator = keySet.iterator();

                final LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.BELOW, R.id.selectProfileButton);
                linearLayout.setLayoutParams(layoutParams);

                final List<RadioButton> radioButtons = new ArrayList<>();
                while (iterator.hasNext()) {

                    String key = iterator.next();
                    String profileJsonString = sharedPreferences.getString(key, "");
                    Gson gson = new Gson();
                    final Profile profile = gson.fromJson(profileJsonString, Profile.class);

                    if (profile != null) {
                        final View profileRow = inflater.inflate(R.layout.dialog_profile_row, container, false);
                        Button selectProfile = (Button) profileRow.findViewById(R.id.selectProfileButton);
                        int selectProfileButtonId = View.generateViewId();
                        selectProfile.setId(selectProfileButtonId);
                        selectProfile.setText(profile.getProfileName());
                        selectProfile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ServerSettingsFragment serverSettingsFragment = new ServerSettingsFragment();

                                Bundle bundle = new Bundle();
                                bundle.putString("profileName", profile.getProfileName());
                                serverSettingsFragment.setArguments(bundle);
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.item_detail_container, serverSettingsFragment)
                                        .commit();
                            }
                        });

                        RadioButton selectionRB = (RadioButton) profileRow.findViewById(R.id.selectionRB);
                        selectionRB.setTag(profile);
                        selectionRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                for(RadioButton radioButton :radioButtons){
                                    radioButton.setChecked(false);
                                }
                                if(b) {
                                    Profile selectedProfile = (Profile)compoundButton.getTag();
                                    ((SampleAppTabApplication)getActivity().getApplicationContext()).setSelectedProfileName(selectedProfile.getProfileName());
                                    compoundButton.setChecked(true);
                                }
                            }
                        });

                        radioButtons.add(selectionRB);
                        linearLayout.addView(profileRow);
                    }
                }

                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                };

                showDialog(title, linearLayout, onClickListener);
            }
        });

        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(ConstantsApp.PROFILES_PREFERENCES, Context.MODE_APPEND);

        Map<String, ?> allProfiles = sharedPreferences.getAll();
        Set<String> keySet = allProfiles.keySet();
        Iterator<String> iterator = keySet.iterator();

        final LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.selectProfileButton);
        linearLayout.setLayoutParams(layoutParams);
        while (iterator.hasNext()) {
            String key = iterator.next();
            String profileJsonString = sharedPreferences.getString(key, "");
            Gson gson = new Gson();
            final Profile profile = gson.fromJson(profileJsonString, Profile.class);

            if (profile != null) {
                final View profileRow = inflater.inflate(R.layout.profile_row, container, false);
                Button selectProfile = (Button) profileRow.findViewById(R.id.selectProfileButton);
                int selectProfileButtonId = View.generateViewId();
                selectProfile.setId(selectProfileButtonId);
                selectProfile.setText(profile.getProfileName());
                selectProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ServerSettingsFragment serverSettingsFragment = new ServerSettingsFragment();

                        Bundle bundle = new Bundle();
                        bundle.putString("profileName", profile.getProfileName());
                        serverSettingsFragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, serverSettingsFragment)
                                .commit();
                    }
                });

                ImageView deleteButtonIV = (ImageView) profileRow.findViewById(R.id.deleteButton);
                deleteButtonIV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(ConstantsApp.PROFILES_PREFERENCES, Context.MODE_APPEND);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(profile.getProfileName());
                        editor.apply();

                        linearLayout.removeView(profileRow);
                    }
                });
                linearLayout.addView(profileRow);
            }
        }
        rootView.addView(linearLayout);
        return rootView;
    }

    private void showDialog(String title, LinearLayout linearLayout, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setView(linearLayout);
        builder.setPositiveButton("Ok", onClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
