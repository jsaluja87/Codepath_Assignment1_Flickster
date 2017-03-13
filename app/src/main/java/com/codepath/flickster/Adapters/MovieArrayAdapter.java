package com.codepath.flickster.Adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by jsaluja on 3/6/2017.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    static int NUM_LAYOUTS=2;
    static int NORMAL_LAYOUT=0;
    static int BACKDROP_LAYOUT=1;

    static class ViewHolderBasic {
        @BindView(R.id.MovieImageid) ImageView lvimage;
        @BindView(R.id.MovieTitleId) TextView tvtitle;
        @BindView(R.id.MovieDescId) TextView tvbody;

        public ViewHolderBasic(View view) {
            ButterKnife.bind(this,view);
        }
    }
    static class ViewHolderBackDrop {
        @BindView(R.id.ItemMovieBDropImageId) ImageView lvimage;
        @BindView(R.id.ItemMovieBDropIconId) ImageView lvicon;

        public ViewHolderBackDrop(View view) {
            ButterKnife.bind(this,view);
        }
    }
    public MovieArrayAdapter(Context context, List<Movie>movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public int getItemViewType(int position) {
        Movie movie = getItem(position);
        if(movie.getVoteAverage() > 5) {
            return BACKDROP_LAYOUT;
        } else {
            return NORMAL_LAYOUT;
        }
    }

    @Override
    public int getViewTypeCount() {
        return NUM_LAYOUTS;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        ViewHolderBasic VHBasic=null;
        ViewHolderBackDrop VHBackDrop=null;

        if(getItemViewType(position) == 0) {

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_movie, parent, false);

                VHBasic = new ViewHolderBasic(convertView);
                convertView.setTag(VHBasic);
            } else {
                VHBasic = (ViewHolderBasic) convertView.getTag();
            }

            VHBasic.tvtitle.setText(movie.getOriginalTitle());
            VHBasic.tvbody.setText(movie.getOverview());

            if (convertView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(10, 10))
                        .placeholder(R.drawable.poster).into(VHBasic.lvimage);
            } else if (convertView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Picasso.with(getContext()).load(movie.getBackdropPath()).transform(new RoundedCornersTransformation(10, 10))
                        .placeholder(R.drawable.poster).into(VHBasic.lvimage);
            }

        } else if(getItemViewType(position) == 1) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_movie_backdrop, parent, false);

                VHBackDrop = new ViewHolderBackDrop(convertView);
                convertView.setTag(VHBackDrop);
            } else {
                VHBackDrop = (ViewHolderBackDrop) convertView.getTag();
            }

            VHBackDrop.lvicon.setImageResource(R.mipmap.play);
            Picasso.with(getContext()).load(movie.getBackdropPath()).transform(new RoundedCornersTransformation(10, 10))
                    .placeholder(R.drawable.poster).fit().centerCrop().into(VHBackDrop.lvimage);

        }

        return convertView;
    }
}
