package api;

import java.util.List;

import model.Professeurs;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfesseursService {
    String ENDPOINT = "professeurs";

    @GET(ENDPOINT)
    Call<List<Professeurs>> query();
}
