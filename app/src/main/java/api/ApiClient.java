package api;

import android.util.Log;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import helpers.HeaderInterceptor;
import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL_API = "http://192.168.1.45:5000/api/";
    private static final String BASE_URL_API_PHONE = "https://10.0.2.2:5001/api/";
    private static final String BASE_URL_API_PHONE_2 = "http://10.0.2.2:5000/api/";

    public static Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_API_PHONE_2)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
    }


    private static OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        Log.i("Token I", HeaderInterceptor.getInstance().getToken());


        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(HeaderInterceptor.getInstance())
                .build();
    }
    // Fix issues with ssl handshake
    /*
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            // Logging Interceptor => gives request and response log
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            Log.i("DI Interceptor", HeaderInterceptor.getInstance().getToken());

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(logging);
            // Interceptor
            builder.addInterceptor(HeaderInterceptor.getInstance());
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    */
}
