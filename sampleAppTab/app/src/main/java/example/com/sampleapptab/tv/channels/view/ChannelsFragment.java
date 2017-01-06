
package example.com.sampleapptab.tv.channels.view;

import java.io.IOException;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import example.com.sampleapptab.R;
import example.com.sampleapptab.appframework.global.ConstantsApp;
import example.com.sampleapptab.appframework.ui.BaseFragment;
import framework.global.Logger;
import framework.global.Utils;
import framework.network.RequestBody;
import framework.network.Response;
import framework.network.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelsFragment extends BaseFragment {

    private static final String TAG = "ChannelsFragment";
    private ProgressDialog mProgressDialog;
    private ChannelListRecyclerAdapter mChannelListRecyclerAdapter;
    private ArrayList<Channel> mChannelList;
    private VideoView mChannelPreviewVV;
    private String currentUrl;

    public ChannelsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(TAG, "onCreate");
        mChannelList = new ArrayList<>();

        RequestBody requestBody = getRequestBodyCreator().createGetChannelsRequestBody(this);
        requestBody.getCallback().sendRequest(requestBody);

        mProgressDialog = Utils.showProgressDialog(getActivity(), "Message", "Please wait.. !!");
        mProgressDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Logger.i(TAG, "onCreateView");
        // Inflate the layout for this fragment
        View channelsFragmentView = inflater.inflate(R.layout.fragment_channels, container, false);
        RecyclerView channelListRV = (RecyclerView) channelsFragmentView
                .findViewById(R.id.channelListRV);

        channelListRV.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        channelListRV.setLayoutManager(linearLayoutManager);
        mChannelListRecyclerAdapter = new ChannelListRecyclerAdapter(this, mChannelList);
        channelListRV.setAdapter(mChannelListRecyclerAdapter);

        mChannelPreviewVV = (VideoView) channelsFragmentView.findViewById(R.id.channelPreviewVV);

        if (currentUrl != null) {
            Uri uri = Uri.parse(currentUrl);
            mChannelPreviewVV.setMediaController(null);
            mChannelPreviewVV.setVideoURI(uri);
            mChannelPreviewVV.setTag(currentUrl);
            mChannelPreviewVV.requestFocus();
            mChannelPreviewVV.start();
        }
        return channelsFragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.i(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.i(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.i(TAG, "onStop");
    }

    @Override
    public void onResponse(RequestBody requestBody, ResponseBody responseBody) {
        Utils.cancelProgressDialog(mProgressDialog);
        Logger.i(TAG, "onResponse ");
        if (!isResumed()) {
            return;
        }
        switch (responseBody.getRequestType()) {
            case ConstantsApp.GET_CHANNELS_LIST: {
                Response retrofitResponse = responseBody.getResponse();
                Logger.i(TAG, "onSuccess GET_CHANNELS_LIST :" + retrofitResponse.body()
                        + ", Message:" + retrofitResponse.getRawResponse().message());

                if (retrofitResponse.body() == null) {
                    Toast.makeText(getActivity(),
                            "Something went wrong" + ", Message :"
                                    + retrofitResponse.getRawResponse().message() + " !!",
                            Toast.LENGTH_SHORT).show();

                    try {
                        Logger.i(TAG, "Error " + retrofitResponse.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                getResponseHandler().handleGetChannelsResponse(requestBody, retrofitResponse,
                        mChannelList);
                Logger.i(TAG, "mChannelList :" + mChannelList);
                mChannelListRecyclerAdapter.notifyDataSetChanged();
            }
                break;
            default:
                break;
        }
    }

    @Override
    public void onFailure(RequestBody requestBody, ResponseBody responseBody) {
        Utils.cancelProgressDialog(mProgressDialog);
        Logger.i(TAG, "onFailure");
    }

    public void playChannel(String url) {
        String tagUrl = (String) mChannelPreviewVV.getTag();

        if (tagUrl != null && tagUrl.equals(url)) {
            currentUrl = tagUrl;
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack("ChannelsFragment");

            FullScreenChannelFragment fullScreenChannelFragment = new FullScreenChannelFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", tagUrl);
            fullScreenChannelFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.activity_channel, fullScreenChannelFragment,
                    "FullScreenChannelFragment");
            fragmentTransaction.commit();
            return;
        }
        Uri uri = Uri.parse(url);

        mChannelPreviewVV.setMediaController(new MediaController(getActivity()));
        mChannelPreviewVV.setVideoURI(uri);
        mChannelPreviewVV.setTag(url);
        mChannelPreviewVV.requestFocus();
        mChannelPreviewVV.start();
    }
}
