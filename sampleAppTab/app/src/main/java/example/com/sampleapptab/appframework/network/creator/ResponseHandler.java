
package example.com.sampleapptab.appframework.network.creator;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import example.com.sampleapptab.app.SampleAppTabApplication;
import example.com.sampleapptab.appframework.global.ConstantsApp;
import example.com.sampleapptab.appframework.ui.BaseFragment;
import example.com.sampleapptab.login.model.AuthenticationResponseModel;
import example.com.sampleapptab.settings.ItemListActivity;
import example.com.sampleapptab.splash.SplashFragment;
import example.com.sampleapptab.tv.channels.model.GetChannelsListResponseModel;
import example.com.sampleapptab.tv.channels.model.Result;
import example.com.sampleapptab.tv.channels.view.Channel;
import example.com.sampleapptab.tv.channels.view.ChannelActivity;
import framework.global.Logger;
import framework.network.RequestBody;
import framework.network.Response;

/**
 * Created by aniruddhatr on 11/28/2016.
 */

public class ResponseHandler extends AbstractResponseHandler {
    private static final String TAG = "ResponseHandler";

    public void handleAuthenticationResponse(BaseFragment baseFragment, int status, RequestBody requestBody,
                                             Response retrofitResponse) {
        AuthenticationResponseModel authenticationResponseModel = (AuthenticationResponseModel) retrofitResponse
                .body();

        Logger.i(TAG, "handleAuthenticationResponse : " + authenticationResponseModel);
        if(authenticationResponseModel.getAccessToken() == null) {
            Intent intentToHome = new Intent(baseFragment.getActivity(), ItemListActivity.class);
            baseFragment.startActivityForResult(intentToHome, ConstantsApp.LOGIN_REQUEST_CODE);
            baseFragment.getActivity().finish();
            return;
        }

        Intent intentToHome = new Intent(baseFragment.getActivity(), ChannelActivity.class);
        baseFragment.startActivityForResult(intentToHome, ConstantsApp.LOGIN_REQUEST_CODE);
        baseFragment.getActivity().finish();
        SampleAppTabApplication.setAccessToken(authenticationResponseModel.getAccessToken());
    }

    @Override
    public void handleGetChannelsResponse(RequestBody requestBody, Response retrofitResponse, ArrayList<Channel> channelList) {
        GetChannelsListResponseModel getChannelsListResponseModel = (GetChannelsListResponseModel) retrofitResponse
                .body();

        String status = getChannelsListResponseModel.getStatus();
        if(status.equals("OK")) {
            List<Result> resultList = getChannelsListResponseModel.getResults();
            for(Result result :resultList) {
                Logger.i(TAG, "result :" + result);
                Channel channel = new Channel();
                channel.setChannelNumber(String.valueOf(result.getNumber()));
                channel.setChannelName(result.getName());
                channel.setChannelName(result.getUrl());
                channelList.add(channel);
            }
        }

        Logger.i(TAG, "handleGetChannelsResponse : " + getChannelsListResponseModel);
    }
}
