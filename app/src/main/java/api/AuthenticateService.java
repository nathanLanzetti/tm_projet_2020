package api;

import model.LoginInfo;
import model.Professeurs;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticateService {

    String ENDPOINT = "professeurs/authenticate";

    //@Headers("Content-type: application/json")
    @POST("professeurs/authenticate")
    Call<Professeurs> login(@Body LoginInfo loginInfo);
}
