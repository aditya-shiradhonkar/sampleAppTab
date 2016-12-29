
package example.com.sampleapptab.appframework.network.creator;

import java.util.ArrayList;
import java.util.List;

import example.com.sampleapptab.app.SampleAppTabApplication;
import example.com.sampleapptab.login.model.AuthenticationResponseModel;
import example.com.sampleapptab.tv.channels.model.GetChannelsListResponseModel;
import example.com.sampleapptab.tv.channels.model.Result;
import example.com.sampleapptab.tv.channels.view.Channel;
import framework.global.Logger;
import framework.network.RequestBody;
import framework.network.Response;

/**
 * Created by aniruddhatr on 11/28/2016.
 */

public class ResponseHandler extends AbstractResponseHandler {
    private static final String TAG = "ResponseHandler";

    public void handleAuthenticationResponse(int status, RequestBody requestBody,
            Response retrofitResponse) {
        AuthenticationResponseModel authenticationResponseModel = (AuthenticationResponseModel) retrofitResponse
                .body();

        Logger.i(TAG, "handleAuthenticationResponse : " + authenticationResponseModel);
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
                channelList.add(channel);
            }
        }

        Logger.i(TAG, "handleGetChannelsResponse : " + getChannelsListResponseModel);
    }
}
