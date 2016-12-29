
package example.com.sampleapptab.tv.channels.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by aniruddhatr on 12/29/2016.
 */
public interface ITVRequests {
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"
    })

    @GET("api/api_v2.php")
    Call<GetChannelsListResponseModel> getChannelsListRequest(
            @Header("Authorization") String authorizationToken,
            @Query("_resource") String resource);
}
