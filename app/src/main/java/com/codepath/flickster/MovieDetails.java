package com.codepath.flickster;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static com.codepath.flickster.R.string.MovieId;

public class MovieDetails extends AppCompatActivity {

    @BindView(R.id.MDHeaderId) TextView MovieHeader;
    @BindView(R.id.MDReleaseDateId) TextView MovieReleaseDate;
    @BindView(R.id.MDDescId) TextView MovieDesc;
    @BindView(R.id.MDRateBarId) RatingBar MovieRating;
    @BindView(R.id.MDImageId) ImageView MovieBackDropImage;
    @BindView(R.id.MDIconId) ImageView MovieIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        MovieHeader.setText(getIntent().getStringExtra(getResources().getString(R.string.MovieDetailTitle)));
        MovieReleaseDate.setText(getIntent().getStringExtra(getResources().getString(R.string.MovieDetailRelDate)));
        MovieDesc.setText(getIntent().getStringExtra(getResources().getString(R.string.MovieDetailOverView)));
        String BackdropUrl = getIntent().getStringExtra(getResources().getString(R.string.MovieDetailBackdropUrl));

        Picasso.with(MovieDetails.this).load(BackdropUrl).transform(new RoundedCornersTransformation(10, 10))
                .placeholder(R.drawable.poster).fit().centerCrop().into(MovieBackDropImage);



        MovieIcon.setImageResource(R.mipmap.play);
        double movieRating = getIntent().getDoubleExtra(getResources().getString(R.string.MovieDetailVoteAvg), 5)/2;
        MovieRating.setRating((float)movieRating);


        MovieIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MovTrail = new Intent(MovieDetails.this, MovieTrailerActivity.class);
                MovTrail.putExtra(getResources().getString(MovieId), getIntent().getIntExtra(getResources().getString(R.string.MovieId), 0));
                startActivity(MovTrail);
            }
        });
    }
}
