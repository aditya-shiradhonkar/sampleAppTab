package example.com.sampleapptab.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.sampleapptab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        rootView.findViewById(R.id.serverSettingsTV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfilesFragment profilesFragment = new ProfilesFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, profilesFragment)
                        .commit();
            }
        });


        return rootView;
    }

}
