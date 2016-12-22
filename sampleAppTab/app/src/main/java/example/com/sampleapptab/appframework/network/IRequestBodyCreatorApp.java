
package example.com.sampleapptab.appframework.network;

import framework.model.IRetrofitResponseCallback;
import framework.network.RequestBody;

/**
 * Created by aniruddhatr on 11/30/2016. All project specific request creator methods should be kept
 * here
 */

public interface IRequestBodyCreatorApp extends framework.network.creator.IRequestBodyCreator {
    /*
     * Request's required in Login Fragment
     */
    RequestBody createAuthenticationRequestBody(IRetrofitResponseCallback iRetrofitResponseCallback,
            String userName, String password);
}
