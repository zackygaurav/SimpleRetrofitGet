package in.itechvalley.apicallretrofit.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import in.itechvalley.apicallretrofit.client.ApiRequestGenerator;
import in.itechvalley.apicallretrofit.model.ResponseModel;
import in.itechvalley.apicallretrofit.model.SingleMovieModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends AndroidViewModel
{
    /*
    * Init MutableLiveData
    * */
    private MutableLiveData<String> apiObserver = new MutableLiveData<>();

    private MutableLiveData<List<SingleMovieModel>> listObserver = new MutableLiveData<>();

    /*
    * Constructor
    * */
    public MainViewModel(@NonNull Application application)
    {
        super(application);
    }

    public void fetchMoviesList()
    {
        /*
         * OkHttp Logging Interceptor
         * */
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        /*
         * Connect HttpLoggingInterceptor to OkHttp first
         * */
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        /*
         * This is how you create Retrofit's Buildet
         * */
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        /*
         * 1. Convertor - Gson, Jackson, Moshi
         * 2. Base URL - https://api.myjson.com/
         * */
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        retrofitBuilder.baseUrl("https://api.myjson.com/");
        retrofitBuilder.client(okHttpClient);

        /*
         * This is how you create Retrofit with the help of it's Builder
         * */
        Retrofit retrofit = retrofitBuilder.build();

        /*
         * Create the Instance of ApiRequestGenerator with the help of Retrofit's object
         * */
        ApiRequestGenerator apiRequestGenerator = retrofit.create(ApiRequestGenerator.class);

        /*
         * Call the fetchData(...) inside of ApiRequestGenerator
         * */
        Call<ResponseModel> requestCall = apiRequestGenerator.fetchData();

        /*
         * Perform Actual Call
         *
         * new<space>Ctrl+Space
         * */
        requestCall.enqueue(new Callback<ResponseModel>()
        {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response)
            {
                ResponseModel responseBody = response.body();

                /*
                 * Null Check
                 * */
                if (responseBody == null)
                {
                    apiObserver.postValue("Response Body is null");
                    // Toast.makeText(context, "Response Body is null", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (responseBody.isSuccess())
                {
                    apiObserver.postValue(responseBody.getMessage());
                    // Toast.makeText(context, responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                    /*
                     * Get the Movies List
                     * */
                    List<SingleMovieModel> movieList = responseBody.getMovieList();
                    /*
                    * Post the list
                    * */
                    listObserver.postValue(movieList);
                }
                else
                {
                    apiObserver.postValue("Failed: " + responseBody.getMessage());
                    // Toast.makeText(context, "Failed: " + responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable throwable)
            {
                if (throwable instanceof UnknownHostException)
                {
                    apiObserver.postValue("No Internet");
                    // Toast.makeText(context, "No Internet", Toast.LENGTH_LONG).show();
                }
                else if (throwable instanceof SocketTimeoutException)
                {
                    apiObserver.postValue("Timeout. Try again.");
                    // Toast.makeText(context, "Timeout. Try again.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    apiObserver.postValue(throwable.getMessage());
                    // Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
                }

                Log.e("TAG", "Failed to get response", throwable);
            }
        });
    }

    public MutableLiveData<String> getApiObserver()
    {
        return apiObserver;
    }

    public MutableLiveData<List<SingleMovieModel>> getListObserver()
    {
        return listObserver;
    }
}
