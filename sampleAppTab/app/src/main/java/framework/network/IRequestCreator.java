
package framework.network;

import retrofit2.Retrofit;

/**
 * Created by aniruddhatr on 10/4/2016. Interface need to be implemented by application specific
 * RequestCreator
 */

public interface IRequestCreator {
    void createRequest(Retrofit retrofit, RequestBody requestBody);
    /*
     * Example : IAuthenticationRequests iAuthenticationDetails = (IAuthenticationRequests)
     * retrofit.create(requestBody.getiRetrofitRequest()); Call<AuthenticationResponseModel> call =
     * iAuthenticationDetails.sendAuthenticationDetails(requestBody.getQueryParameters().get(
     * "client_id"), requestBody.getQueryParameters().get("client_secret"),
     * requestBody.getQueryParameters().get("grant_type"),
     * requestBody.getQueryParameters().get("username"),
     * requestBody.getQueryParameters().get("password")); call.enqueue(new
     * GenericCallBack<AuthenticationResponseModel>(requestBody));
     */
}
