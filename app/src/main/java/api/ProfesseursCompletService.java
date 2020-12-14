package api;

import java.util.List;

import model.Professeurs;
import model.ProfesseursComplet;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfesseursCompletService {

    String ENDPOINT = "professeurs";
    String ENDPOINT_COMPLET = "professeurs";

    @GET(ENDPOINT)
    Call<List<Professeurs>> getProfesseurs();

    @GET(ENDPOINT_COMPLET)
    Call<List<ProfesseursComplet>> query();
}
