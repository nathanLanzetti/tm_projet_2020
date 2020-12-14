package api;

import java.util.List;

import model.Cours;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CoursService {
    String ENDPOINT = "cours";

    @GET(ENDPOINT)
    Call<List<Cours>> query();
}
