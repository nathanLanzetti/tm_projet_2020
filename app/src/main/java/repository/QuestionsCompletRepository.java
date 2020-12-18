package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.QuestionsCompletService;
import api.TestsCompletService;
import model.QuestionsComplet;
import model.TestsComplet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsCompletRepository {
    public QuestionsCompletRepository(){ }

    private QuestionsCompletService getQuestionsCompletService(){
        return ApiClient.getClient().create(QuestionsCompletService.class);
    }

    // Query all
    public LiveData<List<QuestionsComplet>> query() {
        final MutableLiveData<List<QuestionsComplet>> mutableLiveData = new MutableLiveData<>();
        getQuestionsCompletService().query().enqueue(new Callback<List<QuestionsComplet>>() {
            @Override
            public void onResponse(Call<List<QuestionsComplet>> call, Response<List<QuestionsComplet>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<QuestionsComplet>> call, Throwable t) {
                Log.wtf("Tests Repo QUERY", "Here we go again ... " + t);
            }
        });
        return mutableLiveData;
    }

    // Query all
    public LiveData<QuestionsComplet> get(final int id) {
        final MutableLiveData<QuestionsComplet> mutableLiveData = new MutableLiveData<>();
        getQuestionsCompletService().get(id).enqueue(new Callback<QuestionsComplet>() {
            @Override
            public void onResponse(Call<QuestionsComplet> call, Response<QuestionsComplet> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<QuestionsComplet> call, Throwable t) {
                Log.wtf("Tests Repo QUERY", "Here we go again ... " + t);
            }
        });
        return mutableLiveData;
    }

}
