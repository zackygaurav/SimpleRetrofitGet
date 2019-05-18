package in.itechvalley.apicallretrofit.client;

import in.itechvalley.apicallretrofit.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestGenerator
{
    /*
    * https://api.myjson.com/bins/169b6y
    *
    * Base URL - https://api.myjson.com/
    * Endpoint - bins/169b6y
    * */

    @GET("bins/1bo7h2")
    Call<ResponseModel> fetchData();

    @FormUrlEncoded
    @POST("endpoint")
    Call<ResponseModel> postExample(
            @Field("example") String example,
            @Field("user_name") String username,
            @Field("password") String password
    );
}
