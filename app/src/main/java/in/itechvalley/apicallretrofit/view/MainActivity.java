package in.itechvalley.apicallretrofit.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.itechvalley.apicallretrofit.R;
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

public class MainActivity extends AppCompatActivity
{
    /*
     * https://api.github.com/users/zackygaurav
     * */

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Init ButterKnife to this Activity
        * */
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        /*
        * Init RecyclerView
        * */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick({R.id.btnFetch, R.id.fab})
    void fetchData()
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
                    Toast.makeText(MainActivity.this, "Response Body is null", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (responseBody.isSuccess())
                {
                    Toast.makeText(MainActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                    /*
                    * Get the Movies List
                    * */
                    List<SingleMovieModel> movieList = responseBody.getMovieList();
                    /*
                    * TODO Attach this movieList to a RecyclerView
                    * */
                    // recyclerView.setAdapter();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Failed: " + responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable throwable)
            {
                if (throwable instanceof UnknownHostException)
                {
                    Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_LONG).show();
                }
                else if (throwable instanceof SocketTimeoutException)
                {
                    Toast.makeText(MainActivity.this, "Timeout. Try again.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                }

                Log.e("TAG", "Failed to get response", throwable);
            }
        });
    }
}
