package edu.xda.abc.adapters;

import android.widget.ImageView;

import edu.xda.abc.models.MovieModel;

public interface MovieItemClickListener {
    void onMovieClick(MovieModel movie, ImageView movieImageView);
}
