package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.CoursService;
import model.Cours;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursRepository {

    public CoursRepository(){}

    public CoursService getCoursService(){
        return ApiClient.getClient().create(CoursService.class);
    }

    public LiveData<List<Cours>> query(){
        final MutableLiveData<List<Cours>> mutableLiveData = new MutableLiveData<>();
        getCoursService().query().enqueue(new Callback<List<Cours>>() {
            @Override
            public void onResponse(Call<List<Cours>> call, Response<List<Cours>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Cours>> call, Throwable t) {
                Log.i("Cours Repo", "Shit ... "+t);
            }
        });
        return mutableLiveData;
    }
}
