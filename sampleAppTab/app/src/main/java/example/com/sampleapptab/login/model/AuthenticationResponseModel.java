
package example.com.sampleapptab.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import framework.model.IResponseBody;

public class AuthenticationResponseModel implements IResponseBody{

    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;
    @SerializedName("portal_url")
    @Expose
    private String portalUrl;
    @SerializedName("debug")
    @Expose
    private String debug;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getPortalUrl() {
        return portalUrl;
    }

    public void setPortalUrl(String portalUrl) {
        this.portalUrl = portalUrl;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    @Override
    public String toString() {
        return "AuthenticationResponseModel{" +
                "tokenType='" + tokenType + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", userId=" + userId +
                ", expiresIn=" + expiresIn +
                ", portalUrl='" + portalUrl + '\'' +
                ", debug='" + debug + '\'' +
                '}';
    }
}
