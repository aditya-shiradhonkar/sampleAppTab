
package example.com.sampleapptab.login.model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by aniruddhatr on 12/22/2016.
 */
public interface IAuthenticationRequests {

    @Headers({
            "Content-Type : application/x-www-form-urlencoded",
            "Accept : application/json"
    })

    @FormUrlEncoded
    @POST("auth/token.php")
    Call<AuthenticationResponseModel> sendAuthenticationDetails(
            @Field("grant_type") String grantType,
            @Field("mac") String mac);
}
