package com.codepath.flickster;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.flickster.Adapters.MovieArrayAdapter;
import com.codepath.flickster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.os.Build.VERSION_CODES.M;
import static android.view.View.Y;
import static com.codepath.flickster.R.string.MovieId;
import static com.loopj.android.http.AsyncHttpClient.log;

public class MovieActivity extends YouTubeBaseActivity {

    ArrayList<Movie>  movies;
    MovieArrayAdapter movieAdapter;
    @BindView(R.id.lvMovies) ListView lvItems;

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    final String MovieDetailsUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);


        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);

        Request request = new Request.Builder().url(MovieDetailsUrl).build();
        //Reading from okhttp and feeding the movie information to the arraylist
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                try {
                    JSONObject json = new JSONObject(result);
                    JSONArray movieJsonResults = json.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));

                    MovieActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            movieAdapter.notifyDataSetChanged();
                        }
                    });
                    log.d("DEBUG", "PASSED" +movieJsonResults);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //final int MovieId = movies.get(position).getID();
                double MovieRating = movies.get(position).getVoteAverage();

                if(MovieRating > 5) {
                    Intent MovTrail = new Intent(MovieActivity.this, MovieTrailerActivity.class);
                    MovTrail.putExtra(getResources().getString(R.string.MovieId), movies.get(position).getID());
                    startActivity(MovTrail);
                } else {

                    Intent movieDetails = new Intent(MovieActivity.this, MovieDetails.class);
                    movieDetails.putExtra(getResources().getString(R.string.MovieDetailTitle), movies.get(position).getOriginalTitle());
                    movieDetails.putExtra(getResources().getString(R.string.MovieDetailRelDate), movies.get(position).getReleaseDate());
                    movieDetails.putExtra(getResources().getString(R.string.MovieDetailOverView), movies.get(position).getOverview());
                    movieDetails.putExtra(getResources().getString(R.string.MovieDetailVoteAvg), movies.get(position).getVoteAverage());
                    movieDetails.putExtra(getResources().getString(R.string.MovieDetailBackdropUrl), movies.get(position).getBackdropPath());
                    movieDetails.putExtra(getResources().getString(R.string.MovieId), movies.get(position).getID());
                    startActivity(movieDetails);
                }


            }
        });

    }

}
