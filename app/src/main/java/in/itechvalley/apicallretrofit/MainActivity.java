package in.itechvalley.apicallretrofit;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import in.itechvalley.apicallretrofit.client.ApiRequestGenerator;
import in.itechvalley.apicallretrofit.model.ResponseModel;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Fetching Data", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                fetchData();
            }
        });
    }

    private void fetchData()
    {
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

                Toast.makeText(MainActivity.this, responseBody.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable throwable)
            {
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();

                Log.e("TAG", "Failed to get response", throwable);
            }
        });
    }
}
