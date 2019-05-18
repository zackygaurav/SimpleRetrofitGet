package in.itechvalley.apicallretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SingleMovieModel implements Serializable
{
    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("Rated")
    private String rated;

    @SerializedName("Released")
    private String released;

    @SerializedName(value = "Poster", alternate = "Poster ")
    private String poster;

    /*
    * Constructor
    * */
    public SingleMovieModel(String title, String year, String rated, String released, String poster)
    {
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.poster = poster;
    }

    /*
    * Getters
    * */
    public String getTitle()
    {
        return title;
    }

    public String getYear()
    {
        return year;
    }

    public String getRated()
    {
        return rated;
    }

    public String getReleased()
    {
        return released;
    }

    public String getPoster()
    {
        return poster;
    }
}
