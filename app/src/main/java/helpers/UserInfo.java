package helpers;

import android.util.Log;

public class UserInfo {
    private String token;
    private int userId;

    public UserInfo(String token, int userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        token = token == null ? "fakeToken" : token;
        Log.i("Interceptor Token", token);
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
