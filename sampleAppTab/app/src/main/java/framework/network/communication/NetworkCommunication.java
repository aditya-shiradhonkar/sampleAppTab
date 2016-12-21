
package framework.network.communication;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.squareup.okhttp.Protocol;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.content.Context;

import example.com.sampleapptab.R;
import framework.global.Constants;
import framework.global.Logger;
import framework.model.IRetrofitResponseCallback;
import framework.network.IRequestCreator;
import framework.network.RequestBody;
import framework.ui.BaseFragment;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aniruddhatr on 6/10/2016. Class is for doing network communication and help the
 * helpers
 */
public class NetworkCommunication {

    private static final String TAG = "SampleAppNetworkCommunication";
    protected static NetworkCommunication sNetworkCommunication;
    private final Retrofit mBackendRetrofit;

    private Picasso mPicasso;
    protected IRequestCreator mIRequestCreator;

    protected NetworkCommunication(IRetrofitResponseCallback iRetrofitResponseCallback) {
        mBackendRetrofit = getRetrofit(iRetrofitResponseCallback,
                iRetrofitResponseCallback.getBaseUrl());
        mPicasso = new PicassoClient(iRetrofitResponseCallback.getApplicationContext())
                .getPicasso();
    }

    protected Retrofit getRetrofit(IRetrofitResponseCallback iRetrofitResponseCallback,
            String url) {
        return new RetrofitClient(iRetrofitResponseCallback.getApplicationContext(), url)
                .getRetrofit();
    }

    protected Retrofit getBackendRetrofit() {
        return mBackendRetrofit;
    }

    public Picasso getPicasso() {
        return mPicasso;
    }

    public static NetworkCommunication getInstance(
            IRetrofitResponseCallback iRetrofitResponseCallback) {
        if (sNetworkCommunication == null) {
            sNetworkCommunication = new NetworkCommunication(iRetrofitResponseCallback);
        }

        return sNetworkCommunication;
    }

    protected void checkForChangesInBaseUrl(BaseFragment uiHandler, HttpUrl httpUrl,
            String httpUrlFromSharedPrefs) {
        String httpProtocol = "http";
        int port = httpUrl.port();
        Logger.d(TAG, "Port :" + port);
        boolean isHttps = httpUrl.isHttps();
        Logger.d(TAG, "isHTTPS :" + isHttps);
        if (isHttps) {
            httpProtocol = "https";
        }

        String host = httpUrl.host();
        Logger.i(TAG, "host :" + host);

        String portString = Integer.toString(port);

        Logger.d(TAG, "New URL :" + httpUrlFromSharedPrefs);
        httpUrlFromSharedPrefs = httpUrlFromSharedPrefs.replace("/", "");
        String[] hostUrlParametersArray = httpUrlFromSharedPrefs.split(":");

        String httpProtocolFromSharedPrefs;
        if (hostUrlParametersArray.length > 0) {
            httpProtocolFromSharedPrefs = hostUrlParametersArray[0];
        } else {
            httpProtocolFromSharedPrefs = httpProtocol;
        }
        String hostFromSharedPrefs;
        if (hostUrlParametersArray.length > 1) {
            hostFromSharedPrefs = hostUrlParametersArray[1];
            Logger.d(TAG, "New Host :" + hostFromSharedPrefs);
        } else {
            hostFromSharedPrefs = host;
        }
        String portFromSharedPrefs;
        if (hostUrlParametersArray.length > 2) {
            portFromSharedPrefs = hostUrlParametersArray[2];
            Logger.d(TAG, "New Port :" + portFromSharedPrefs);
        } else {
            portFromSharedPrefs = portString;
        }
        if (!httpProtocolFromSharedPrefs.equals(httpProtocol) || !host.equals(hostFromSharedPrefs)
                || !portString.equals(portFromSharedPrefs)) {
            Logger.i(TAG, "Reinitialized SampleAppNetworkCommunication");
            createInstance(uiHandler);
        }
    }

    protected void createInstance(BaseFragment uiHandler) {
        sNetworkCommunication = new NetworkCommunication(uiHandler);
    }

    public void createRequest(final RequestBody requestBody) {
        Logger.i(TAG, "Request body :" + requestBody);
        mIRequestCreator.createRequest(mBackendRetrofit, requestBody);
    }

    public void setIRequestCreator(IRequestCreator iRequestCreator) {
        if (this.mIRequestCreator == null) {
            this.mIRequestCreator = iRequestCreator;
        }
    }

    public void updateBaseUrlPreferences(BaseFragment baseFragment) {
        HttpUrl httpUrl = sNetworkCommunication.getBackendRetrofit().baseUrl();
        String httpUrlFromSharedPrefs = baseFragment.getBaseUrl();
        checkForChangesInBaseUrl(baseFragment, httpUrl, httpUrlFromSharedPrefs);
    }
}

class RetrofitClient {
    private static final String TAG = "SampleAppNetworkCommunication$RetrofitClient";
    private Retrofit mRetrofit;

    RetrofitClient(Context context, String baseUrl) {
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient.Builder okHttpClientBuilder = okHttpClient.newBuilder();
        okHttpClientBuilder.connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.TIMEOUT, TimeUnit.SECONDS);
        CertificateFactory cf;
        InputStream cert = null;
        try {
            cf = CertificateFactory.getInstance("X.509");

            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);

            cert = context.getResources().openRawResource(R.raw.testcertificate);

            Certificate ca = cf.generateCertificate(cert);
            keyStore.setCertificateEntry("mcoe", ca);
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            SSLContext sslContext = SSLContext.getInstance("TLS");

            sslContext.init(null, tmf.getTrustManagers(), new SecureRandom());

            okHttpClientBuilder.sslSocketFactory(sslContext.getSocketFactory());
            okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {

                @SuppressLint("BadHostnameVerifier")
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }

            });
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();

        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException
                | IOException | KeyManagementException e) {
            Logger.e(TAG, "Exception :" + e.getMessage());
        } finally {
            try {
                if (cert != null) {
                    cert.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    Retrofit getRetrofit() {
        return mRetrofit;
    }
}

class PicassoClient {
    private static final String TAG = "PicassoClient";
    private final Picasso mPicasso;

    PicassoClient(Context context) {
        Picasso.Builder picassoBuilder = new Picasso.Builder(context);

        picassoBuilder.downloader(
                new OkHttpDownloader(
                        getUnsafeOkHttpClient()// for https requests
                ));
        mPicasso = picassoBuilder.build();
    }

    /**
     * Method used to get https client
     *
     * @return OkHttpClient
     */
    private static com.squareup.okhttp.OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                String authType) throws CertificateException {
                            Logger.i(TAG, "checkClientTrusted, authType :" + authType);
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                String authType) throws CertificateException {
                            Logger.i(TAG, "checkServerTrusted, authType :" + authType);
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            com.squareup.okhttp.OkHttpClient okHttpClient = new com.squareup.okhttp.OkHttpClient();
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            List<Protocol> protocolList = new ArrayList<>();
            protocolList.add(Protocol.HTTP_1_1);
            okHttpClient.setProtocols(protocolList);
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return hostname != null && !hostname.isEmpty();
                }
            });

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    Picasso getPicasso() {
        return mPicasso;
    }
}
