package in.itechvalley.apicallretrofit.model;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseModel implements Serializable
{
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("movies")
    private List<SingleMovieModel> movieList;

    /*
    * Constructor
    * */
    public ResponseModel(boolean success, String message, List<SingleMovieModel> movieList)
    {
        this.success = success;
        this.message = message;
        this.movieList = movieList;
    }

    /*
    * Getters
    * */
    public boolean isSuccess()
    {
        return success;
    }

    public String getMessage()
    {
        return message;
    }

    public List<SingleMovieModel> getMovieList()
    {
        return movieList;
    }
}
