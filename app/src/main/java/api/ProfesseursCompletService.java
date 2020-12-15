package api;

import java.util.List;

import model.Professeurs;
import model.ProfesseursComplet;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfesseursCompletService {

    String ENDPOINT = "professeurs";
    String ENDPOINT_COMPLET = "professeursComplet";

    @GET(ENDPOINT)
    Call<List<Professeurs>> getProfesseurs();

    @GET(ENDPOINT_COMPLET)
    Call<List<ProfesseursComplet>> query();

    @GET(ENDPOINT_COMPLET + "/{id}")
    Call<ProfesseursComplet> get(@Path("id") int id);
}
