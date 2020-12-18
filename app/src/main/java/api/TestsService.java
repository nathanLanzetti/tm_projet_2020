package api;

import java.util.List;

import model.Tests;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TestsService {
    String ENDPOINT = "tests";

    @GET(ENDPOINT + "/{id}")
    Call<Tests> get(@Path("id") int id);

    @POST(ENDPOINT)
    Call<Tests> post(@Body Tests tests);

    @PUT(ENDPOINT)
    Call<Void> put(@Body Tests tests);

    @DELETE(ENDPOINT + "/{id}")
    Call<Void> delete(@Path("id") int id);

}
