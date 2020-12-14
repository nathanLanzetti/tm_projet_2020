package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.ProfesseursCompletService;
import api.ProfesseursService;
import model.Professeurs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfesseursRepository {

    public ProfesseursRepository(){}

    public ProfesseursService getProfesseursService(){
        return ApiClient.getClient().create(ProfesseursService.class);
    }

    // Query all
    public LiveData<List<Professeurs>> query(){
        final MutableLiveData<List<Professeurs>> mutableLiveData = new MutableLiveData<>();
        getProfesseursService().query().enqueue(new Callback<List<Professeurs>>() {
            @Override
            public void onResponse(Call<List<Professeurs>> call, Response<List<Professeurs>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Professeurs>> call, Throwable t) {
                Log.i("Prof Repo", "SHit .. "+t);
            }
        });
        return mutableLiveData;
    }
}
