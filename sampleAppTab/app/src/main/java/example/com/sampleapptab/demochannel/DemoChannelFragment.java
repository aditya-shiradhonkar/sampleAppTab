package example.com.sampleapptab.demochannel;


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
public class DemoChannelFragment extends BaseFragment {


    public DemoChannelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View demoChannelRootView = inflater.inflate(R.layout.fragment_demo_channel, container, false);
        VideoView channelPreviewVV = (VideoView) demoChannelRootView.findViewById(R.id.channelPreviewVV);
        Uri uri = Uri.parse("http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8"); //Declare your url here.

        channelPreviewVV.setMediaController(new MediaController(getActivity()));
        channelPreviewVV.setVideoURI(uri);
        channelPreviewVV.requestFocus();
        channelPreviewVV.start();
        return demoChannelRootView;
    }

    @Override
    public void onResponse(RequestBody requestBody, ResponseBody responseBody) {

    }

    @Override
    public void onFailure(RequestBody requestBody, ResponseBody responseBody) {

    }
}
