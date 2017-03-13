package com.codepath.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import static android.R.attr.x;
import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by jsaluja on 3/6/2017.
 */

public class Movie implements Serializable {

    String PosterPath;
    String BackdropPath;
    String OriginalTitle;
    String Overview;
    String ReleaseDate;
    double VoteAverage;
    int ID;

    public int getID() {return ID;}

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",BackdropPath);
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",PosterPath);
    }

    public String getOriginalTitle() {
        return OriginalTitle;
    }
    public String getOverview() {
        return Overview;
    }
    public double getVoteAverage() {return VoteAverage;}
    public String getReleaseDate() { return ReleaseDate;}

    public Movie(JSONObject jsonObject) throws JSONException {
        this.OriginalTitle = jsonObject.getString("original_title");
        //log.d("DEBUG", "Original Title Got"+OriginalTitle);
        this.PosterPath = jsonObject.getString("poster_path");
        this.Overview = jsonObject.getString("overview");
        this.BackdropPath = jsonObject.getString("backdrop_path");
        this.VoteAverage = jsonObject.getDouble("vote_average");
        this.ReleaseDate = jsonObject.getString("release_date");
        this.ID = jsonObject.getInt("id");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for(int x = 0; x < array.length(); x++) {
            try {

                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}
