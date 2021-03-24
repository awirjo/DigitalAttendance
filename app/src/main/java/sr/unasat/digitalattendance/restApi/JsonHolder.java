package sr.unasat.digitalattendance.restApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonHolder {
    @GET("users")
    Call<List<RestUsers>> getUsers();
}
