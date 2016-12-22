
package example.com.sampleapptab.appframework.network;

import framework.network.RequestBody;
import framework.network.Response;

/**
 * Created by aniruddhatr on 11/30/2016. All the project specific response handling methods to be
 * declared here
 */

public interface IResponseHandlerApp extends framework.network.creator.IResponseHandler {

    void handleAuthenticationResponse(int status, RequestBody requestBody,
            Response retrofitResponse);
}
