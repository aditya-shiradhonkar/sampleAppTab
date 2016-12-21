
package framework.network;

import framework.helper.BaseHelper;
import framework.model.IRetrofitResponseCallback;

/**
 * Created by aniruddhatr on 10/24/2016. Interface need to be implemented by application's
 * CallbackCreator
 */

public interface ICallbackCreator {
    BaseHelper createCallback(IRetrofitResponseCallback iRetrofitResponseCallback, int requestType);
}
