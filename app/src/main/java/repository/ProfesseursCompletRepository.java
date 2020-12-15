package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.ProfesseursCompletService;
import model.Professeurs;
import model.ProfesseursComplet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfesseursCompletRepository {

    public ProfesseursCompletRepository(){

    }

    private ProfesseursCompletService getProfesseursCompletService(){
        return ApiClient.getClient().create(ProfesseursCompletService.class);
    }

    // Query All
    public LiveData<List<Professeurs>> query(){
        final MutableLiveData<List<Professeurs>> mutableLiveData = new MutableLiveData<>();
        getProfesseursCompletService().getProfesseurs().enqueue(new Callback<List<Professeurs>>() {
            @Override
            public void onResponse(Call<List<Professeurs>> call, Response<List<Professeurs>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Professeurs>> call, Throwable t) {
                Log.i("Professeurs Fetch", "Something went wrong" + t);
            }
        });

        return mutableLiveData;
    }

    // Query All Complet
    public LiveData<List<ProfesseursComplet>> queryComplet(){
        final MutableLiveData<List<ProfesseursComplet>> mutableLiveData = new MutableLiveData<>();
        getProfesseursCompletService().query().enqueue(new Callback<List<ProfesseursComplet>>() {
            @Override
            public void onResponse(Call<List<ProfesseursComplet>> call, Response<List<ProfesseursComplet>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProfesseursComplet>> call, Throwable t) {
                Log.i("Professeurs Fetch", "Something went wrong" + t);
            }
        });

        return mutableLiveData;
    }

    // Query All
    public LiveData<ProfesseursComplet> get(int id){
        final MutableLiveData<ProfesseursComplet> mutableLiveData = new MutableLiveData<>();
        getProfesseursCompletService().get(id).enqueue(new Callback<ProfesseursComplet>() {
            @Override
            public void onResponse(Call<ProfesseursComplet> call, Response<ProfesseursComplet> response) {
                mutableLiveData.postValue(response.body());
                Log.i("GET CALLBACK", mutableLiveData.toString());
            }

            @Override
            public void onFailure(Call<ProfesseursComplet> call, Throwable t) {
                Log.i("Professeurs Fetch", "Something went wrong" + t);
            }
        });

        return mutableLiveData;
    }
}
