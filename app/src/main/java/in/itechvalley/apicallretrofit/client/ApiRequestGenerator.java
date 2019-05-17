package in.itechvalley.apicallretrofit.client;

import in.itechvalley.apicallretrofit.model.ResponseModel;
import retrofit2.Call;
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

    @GET("bins/169b6y")
    Call<ResponseModel> fetchData();
}
