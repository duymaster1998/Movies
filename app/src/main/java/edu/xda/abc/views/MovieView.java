package edu.xda.abc.views;

import java.util.List;

import edu.xda.abc.models.MovieModel;

public interface MovieView {
    void setListMovies(List<MovieModel> movieList);
    void setListMoviesHD(List<MovieModel> movieList);
    void setListMoviesTC(List<MovieModel> movieList);
    void setListMoviesOther(List<MovieModel> movieList);
    void setListTop4MovieNews(List<MovieModel> movieNews);
    void setResultsList(List<MovieModel> models);
    void onLoadError(String message);
    void loadingData();
    void hideLoadingData();

}
