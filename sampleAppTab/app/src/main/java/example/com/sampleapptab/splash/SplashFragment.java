
package example.com.sampleapptab.splash;

import java.io.IOException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import example.com.sampleapptab.R;
import example.com.sampleapptab.appframework.global.ConstantsApp;
import example.com.sampleapptab.appframework.ui.BaseFragment;
import example.com.sampleapptab.tv.channels.view.ChannelActivity;
import framework.global.Logger;
import framework.global.Utils;
import framework.network.RequestBody;
import framework.network.Response;
import framework.network.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends BaseFragment {

    private static final String TAG = "SplashFragment";
    private static final int LOGIN_REQUEST_CODE = 1;
    private ProgressDialog mProgressDialog;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RequestBody requestBody = getRequestBodyCreator().createAuthenticationRequestBody(this,
                "11:00:99:00:99:99");
        requestBody.getCallback().sendRequest(requestBody);

        mProgressDialog = Utils.showProgressDialog(getActivity(), "Message", "Please wait.. !!");
        mProgressDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onResponse(RequestBody requestBody, ResponseBody responseBody) {
        Utils.cancelProgressDialog(mProgressDialog);
        Logger.i(TAG, "onResponse ");
        if (!isResumed()) {
            return;
        }
        switch (responseBody.getRequestType()) {
            case ConstantsApp.AUTHENTICATION_DETAILS: {

                Response retrofitResponse = responseBody.getResponse();
                Logger.i(TAG, "onSuccess AUTHENTICATION_DETAILS :" + retrofitResponse.body()
                        + ", Message:" + retrofitResponse.getRawResponse().message());

                if (retrofitResponse.body() == null) {
                    Toast.makeText(getActivity(),
                            "unauthorized" + ", Message :"
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

                Intent intentToHome = new Intent(getActivity(), ChannelActivity.class);
                startActivityForResult(intentToHome, LOGIN_REQUEST_CODE);
            }
                break;
        }
    }

    @Override
    public void onFailure(RequestBody requestBody, ResponseBody responseBody) {

    }
}
