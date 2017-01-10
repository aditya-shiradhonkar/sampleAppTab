package example.com.sampleapptab.settings;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import example.com.sampleapptab.R;
import example.com.sampleapptab.appframework.global.ConstantsApp;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilesFragment extends Fragment {


    public ProfilesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(ConstantsApp.PROFILES_PREFERNCES, Context.MODE_APPEND);

        Map<String, ?> allProfiles = sharedPreferences.getAll();
        Set<String> keySet = allProfiles.keySet();
        Iterator<String> iterator = keySet.iterator();

        LinearLayout linearLayout = new LinearLayout(getActivity());
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
                View profileRow = inflater.inflate(R.layout.profile_row, container, false);
                Button selectProfileButton = (Button) profileRow.findViewById(R.id.selectProfileButton);
                int selectProfileButtonId = View.generateViewId();
                selectProfileButton.setId(selectProfileButtonId);
                selectProfileButton.setText(profile.getProfileName());
                selectProfileButton.setOnClickListener(new View.OnClickListener() {
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

                linearLayout.addView(profileRow);
            }
        }
        rootView.addView(linearLayout);
        return rootView;
    }

}
