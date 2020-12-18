package api;

import java.util.List;

import model.QuestionsTests;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuestionsTestsService {
    String ENDPOINT = "questions_tests";

    @GET(ENDPOINT + "/bytest/{id}")
    Call<List<QuestionsTests>> getByTest(@Path("id") int id);

    @DELETE(ENDPOINT + "/bytest/{id}")
    Call<Void> deleteByTest(@Path("id") int id);

    @POST(ENDPOINT)
    Call<QuestionsTests> post(@Body QuestionsTests questionsTests);

}
