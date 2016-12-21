
package example.com.sampleapptab.framework.network.creator;

import framework.helper.BaseHelper;
import framework.model.IRetrofitResponseCallback;
import framework.network.ICallbackCreator;

/**
 * Created by aniruddhatr on 10/24/2016. This class helps in creating the request bodies
 */

public class CallbackCreator implements ICallbackCreator {

    @Override
    public BaseHelper createCallback(IRetrofitResponseCallback iRetrofitResponseCallback,
            int requestType) {
        BaseHelper callBack;
        switch (requestType) {
            default:
                callBack = new BaseHelper(iRetrofitResponseCallback);
                break;
        }

        return callBack;
    }
}
