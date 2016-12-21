
package example.com.sampleapptab.framework.network;

import framework.model.IRetrofitResponseCallback;
import framework.network.RequestBody;

/**
 * Created by aniruddhatr on 11/30/2016. All project specific request creator methods should be kept
 * here
 */

public interface IRequestBodyCreator extends framework.network.creator.IRequestBodyCreator {
    /*
     * Request's required in Login Fragment
     */
    RequestBody createAuthenticationRequestBody(IRetrofitResponseCallback iRetrofitResponseCallback,
            String userName, String password);
}
