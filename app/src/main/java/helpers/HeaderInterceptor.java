package helpers;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private static HeaderInterceptor instance = null;
    private String token;
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("Authorization", "Bearer " + token);

        return chain.proceed(builder.build());
    }

    private HeaderInterceptor() { }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        token = token == null ? "fakeToken" : token;
        Log.i("Interceptor Token", token);
        this.token = token;
    }

    public static HeaderInterceptor getInstance(){
        if (instance == null){
                instance = new HeaderInterceptor();
        }
        return instance;
    }
}
