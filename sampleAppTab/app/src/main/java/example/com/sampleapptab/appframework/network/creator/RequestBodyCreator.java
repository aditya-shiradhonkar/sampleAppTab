
package example.com.sampleapptab.appframework.network.creator;

import java.util.HashMap;
import java.util.Map;

import example.com.sampleapptab.appframework.global.ConstantsApp;
import example.com.sampleapptab.login.model.IAuthenticationRequests;
import framework.model.IRetrofitResponseCallback;
import framework.network.RequestBody;

/**
 * Created by aniruddhatr on 11/28/2016.
 */

public class RequestBodyCreator extends AbstractRequestBodyCreator {
    private static final String TAG = "RequestBodyCreator";

    public RequestBody createAuthenticationRequestBody(
            IRetrofitResponseCallback iRetrofitResponseCallback, String macId) {

        RequestBody requestBody = new RequestBody(iRetrofitResponseCallback,
                ConstantsApp.AUTHENTICATION_DETAILS);
        requestBody.setiRetrofitRequest(IAuthenticationRequests.class);

        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("grant_type", "password");
        fieldMap.put("mac", macId);

        requestBody.setFieldMap(fieldMap);
        return requestBody;
    }

}
