package api;

import java.util.List;

import model.QuestionsComplet;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuestionsCompletService {

    String ENDPOINT = "questionsComplet";

    @GET(ENDPOINT)
    Call<List<QuestionsComplet>> query();

    @GET(ENDPOINT + "/{id}")
    Call<QuestionsComplet> get(@Path("id") int id);

}
