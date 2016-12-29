
package example.com.sampleapptab.splash;

import java.io.IOException;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import example.com.sampleapptab.R;
import example.com.sampleapptab.appframework.global.ConstantsApp;
import example.com.sampleapptab.appframework.ui.BaseFragment;
import example.com.sampleapptab.settings.ItemListActivity;
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
    private ProgressDialog mProgressDialog;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View splashScreenRootView = inflater.inflate(R.layout.fragment_splash, container, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final EditText editText = new EditText(getActivity());
        editText.setHint("Please enter your mac id here !!");
        builder.setView(editText);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String mac = editText.getText().toString();
                RequestBody requestBody = getRequestBodyCreator().createAuthenticationRequestBody(SplashFragment.this,
                        mac);
                requestBody.getCallback().sendRequest(requestBody);

                mProgressDialog = Utils.showProgressDialog(getActivity(), "Message", "Please wait.. !!");
                mProgressDialog.show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return splashScreenRootView;
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
                    Intent intentToHome = new Intent(getActivity(), ItemListActivity.class);
                    startActivityForResult(intentToHome, ConstantsApp.LOGIN_REQUEST_CODE);
                    getActivity().finish();
                    try {
                        Logger.i(TAG, "Error " + retrofitResponse.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                getResponseHandler().handleAuthenticationResponse(this, ConstantsApp.STATUS_OK,
                        requestBody, retrofitResponse);
            }
                break;
        }
    }

    @Override
    public void onFailure(RequestBody requestBody, ResponseBody responseBody) {
        Utils.cancelProgressDialog(mProgressDialog);
        Logger.i(TAG, "onFailure ");
        Intent intentToHome = new Intent(getActivity(), ItemListActivity.class);
        startActivityForResult(intentToHome, ConstantsApp.LOGIN_REQUEST_CODE);
        getActivity().finish();
    }
}
