
package example.com.sampleapptab.demochannel;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

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
public class DemoChannelFragment extends BaseFragment {

    private static final String TAG = "DemoChannelFragment";
    private ProgressDialog mProgressDialog;
    private VideoView mChannelPreviewVV;

    public DemoChannelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequestBody requestBody = getRequestBodyCreator().createAuthenticationRequestBody(this,
                "11:00:99:00:99:99");
        requestBody.getCallback().sendRequest(requestBody);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View demoChannelRootView = inflater.inflate(R.layout.fragment_demo_channel, container,
                false);
        mChannelPreviewVV = (VideoView) demoChannelRootView.findViewById(R.id.channelPreviewVV);
        Uri uri = Uri.parse("http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8"); // Declare
                                                                                                // your
                                                                                                // url
                                                                                                // here.

        mChannelPreviewVV.setMediaController(new MediaController(getActivity()));
        mChannelPreviewVV.setVideoURI(uri);
        mChannelPreviewVV.requestFocus();
        mChannelPreviewVV.start();
        return demoChannelRootView;
    }

    @Override
    public void onResponse(RequestBody requestBody, ResponseBody responseBody) {
        Logger.i(TAG, "onResponse ");
        if (!isResumed()) {
            return;
        }
        switch (responseBody.getRequestType()) {
            case ConstantsApp.AUTHENTICATION_DETAILS: {
                Utils.cancelProgressDialog(mProgressDialog);
                Response retrofitResponse = responseBody.getResponse();
                Logger.i(TAG, "onSuccess AUTHENTICATION_DETAILS :" + retrofitResponse.body()
                        + ", Message:" + retrofitResponse.getRawResponse().message());

                if (retrofitResponse.body() == null) {
                    Toast.makeText(getActivity(),
                            R.string.unauthorized + ", Message :"
                                    + retrofitResponse.getRawResponse().message() + " !!",
                            Toast.LENGTH_SHORT).show();

                    try {
                        Logger.i(TAG, "Error " + retrofitResponse.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                getResponseHandler().handleAuthenticationResponse(ConstantsApp.STATUS_OK,
                        requestBody, retrofitResponse);
                /*
                 * Intent intentToHome = new Intent(getActivity(), HomeActivity.class);
                 * startActivityForResult(intentToHome, LOGIN_REQUEST_CODE);
                 */
            }
                break;
        }
    }

    @Override
    public void onFailure(RequestBody requestBody, ResponseBody responseBody) {

    }
}
