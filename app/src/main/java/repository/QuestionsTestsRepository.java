package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import api.ApiClient;
import api.QuestionsCompletService;
import api.QuestionsTestsService;
import model.QuestionsTests;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsTestsRepository {

    public QuestionsTestsRepository(){ }

    private QuestionsTestsService getQuestionsTestsService(){
        return ApiClient.getClient().create(QuestionsTestsService.class);
    }

    public void deleteAll(int idTest){
        getQuestionsTestsService().deleteByTest(idTest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("Working", "It's working");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("Failed", "Failed " +t);
            }
        });
    }

    public LiveData<QuestionsTests> post(final QuestionsTests questionsTests){
        final MutableLiveData<QuestionsTests> mutableLiveData = new MutableLiveData<>();
        getQuestionsTestsService().post(questionsTests).enqueue(new Callback<QuestionsTests>() {
            @Override
            public void onResponse(Call<QuestionsTests> call, Response<QuestionsTests> response) {
                Log.i("Working", "It's working");
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<QuestionsTests> call, Throwable t) {
                Log.i("Failed", "Failed " +t);
            }
        });
        return mutableLiveData;
    }
}
