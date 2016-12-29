package example.com.sampleapptab.tv.channels.view;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import example.com.sampleapptab.R;
import example.com.sampleapptab.appframework.ui.BaseFragment;
import framework.network.RequestBody;
import framework.network.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullScreenChannelFragment extends BaseFragment {


    public FullScreenChannelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fullScreenChannelRootView = inflater.inflate(R.layout.fragment_full_screen_channel, container, false);
        VideoView fullScreenVV = (VideoView)fullScreenChannelRootView.findViewById(R.id.fullScreenVV);

        String url = getArguments().getString("url");
        Uri uri = Uri.parse(url);

        fullScreenVV.setMediaController(new MediaController(getActivity()));
        fullScreenVV.setVideoURI(uri);
        fullScreenVV.requestFocus();
        fullScreenVV.start();
        return fullScreenChannelRootView;
    }

    @Override
    public void onResponse(RequestBody requestBody, ResponseBody responseBody) {

    }

    @Override
    public void onFailure(RequestBody requestBody, ResponseBody responseBody) {

    }
}
