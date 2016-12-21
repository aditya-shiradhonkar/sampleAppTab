
package framework.network;

import framework.model.IResponseBody;

/**
 * Created by aniruddhatr on 9/30/2016. This class will be filled with error or response body
 */

public class Response {
    private final okhttp3.Response rawResponse;
    private final IResponseBody body;
    private final okhttp3.ResponseBody errorBody;
    private final int code;

    public Response(okhttp3.Response rawResponse, IResponseBody body,
            okhttp3.ResponseBody errorBody, int code) {
        this.rawResponse = rawResponse;
        this.body = body;
        this.errorBody = errorBody;
        this.code = code;
    }

    public okhttp3.Response getRawResponse() {
        return rawResponse;
    }

    public IResponseBody body() {
        return body;
    }

    public okhttp3.ResponseBody errorBody() {
        return errorBody;
    }

    public int code() {
        return code;
    }
}
