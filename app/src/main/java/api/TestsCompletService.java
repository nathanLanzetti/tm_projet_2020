package api;

import java.util.List;

import model.TestsComplet;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TestsCompletService {

    String ENDPOINT = "testscomplet";

    @GET(ENDPOINT)
    Call<List<TestsComplet>> query();
    @GET(ENDPOINT + "/{id}")
    Call<TestsComplet> get(@Path("id") int id);
}
