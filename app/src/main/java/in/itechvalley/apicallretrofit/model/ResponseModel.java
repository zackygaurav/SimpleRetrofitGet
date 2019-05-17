package in.itechvalley.apicallretrofit.model;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseModel implements Serializable
{
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("status_code")
    private int statusCode;

    /*
    * Constructor
    * */
    public ResponseModel(boolean success, String message, int statusCode)
    {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
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

    public int getStatusCode()
    {
        return statusCode;
    }
}
