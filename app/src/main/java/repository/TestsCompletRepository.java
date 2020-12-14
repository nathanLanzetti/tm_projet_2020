package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.TestsCompletService;
import model.TestsComplet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestsCompletRepository {

    public TestsCompletRepository(){ }

    private TestsCompletService getTestsCompletService(){
        return ApiClient.getClient().create(TestsCompletService.class);
    }

    // Query all
    public LiveData<List<TestsComplet>> query(){
        final MutableLiveData<List<TestsComplet>> mutableLiveData = new MutableLiveData<>();
        getTestsCompletService().query().enqueue(new Callback<List<TestsComplet>>() {
            @Override
            public void onResponse(Call<List<TestsComplet>> call, Response<List<TestsComplet>> response) {
                if (response.isSuccessful()){
                    mutableLiveData.postValue(response.body());
                    return;
                }
                Log.wtf("Tests Repo QUERY", "Fucking damn it");
            }

            @Override
            public void onFailure(Call<List<TestsComplet>> call, Throwable t) {
                Log.wtf("Tests Repo QUERY", "Here we go again ... " + t);
            }
        });

        return mutableLiveData;
    }

    public LiveData<TestsComplet> get(int id){
        final MutableLiveData<TestsComplet> mutableLiveData = new MutableLiveData<>();
        getTestsCompletService().get(id).enqueue(new Callback<TestsComplet>() {
            @Override
            public void onResponse(Call<TestsComplet> call, Response<TestsComplet> response) {
                if (response.isSuccessful()){
                    mutableLiveData.postValue(response.body());
                    return;
                }
                Log.wtf("Tests Repo GET", "Fucking damn it");
            }

            @Override
            public void onFailure(Call<TestsComplet> call, Throwable t) {
                Log.wtf("Tests Repo GET", "Here we go again ... " + t);
            }
        });
        return mutableLiveData;
    }

}
