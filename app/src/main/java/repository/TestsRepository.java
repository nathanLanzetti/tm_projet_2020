package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import api.ApiClient;
import api.TestsService;
import model.Tests;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestsRepository {

    public TestsService getTestsService(){
        return ApiClient.getClient().create(TestsService.class);
    }

    public LiveData<Tests> get(final int id){
        final MutableLiveData<Tests> mutableLiveData = new MutableLiveData<>();
        getTestsService().get(id).enqueue(new Callback<Tests>() {
            @Override
            public void onResponse(Call<Tests> call, Response<Tests> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Tests> call, Throwable t) {
                Log.i("Fail TEST GET", "Fuck " + t);
            }
        });
        return mutableLiveData;
    }

    public LiveData<Tests> post(final Tests tests){
        final MutableLiveData<Tests> mutableLiveData = new MutableLiveData<>();
        getTestsService().post(tests).enqueue(new Callback<Tests>() {
            @Override
            public void onResponse(Call<Tests> call, Response<Tests> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Tests> call, Throwable t) {
                Log.i("Fail TEST SERVICE", "Fuck " + t);
            }
        });

        return mutableLiveData;
    }

    public void put(final Tests tests){
        getTestsService().put(tests).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("TEST PUT", "YEAAAAAh");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("Fail TEST PUT", "Fuck " + t);
            }
        });
    }

    public void delete(int id){
        getTestsService().delete(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("TEST SERVICE DELETE", "DELETED");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("Fail TEST DELETE", "Fuck " + t);
            }
        });
    }
}
