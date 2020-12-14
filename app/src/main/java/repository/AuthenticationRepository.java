package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import api.ApiClient;
import api.AuthenticateService;
import model.LoginInfo;
import model.Professeurs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class AuthenticationRepository {
    public AuthenticationRepository() {
    }

    private AuthenticateService getAuthenticateService(){
        return ApiClient.getClient().create(AuthenticateService.class);
    }

    public LiveData<Professeurs> login(final LoginInfo loginInfo){
        final MutableLiveData<Professeurs> mutableLiveData = new MutableLiveData<>();
        Log.i("LoginInfo", loginInfo.toString());
        getAuthenticateService().login(loginInfo).enqueue(new Callback<Professeurs>() {
            @Override
            public void onResponse(Call<Professeurs> call, Response<Professeurs> response) {
                mutableLiveData.postValue(response.body());
                Log.i("Login Authenticate", mutableLiveData.toString());
            }

            @Override
            public void onFailure(Call<Professeurs> call, Throwable t) {
                Log.i("Login Authenticate","Oh shit, here we go again... " +t);
            }
        });
        return mutableLiveData;
    }
}
