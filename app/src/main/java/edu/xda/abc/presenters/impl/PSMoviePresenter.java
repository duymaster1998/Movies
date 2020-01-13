package edu.xda.abc.presenters.impl;

import java.util.List;

import edu.xda.abc.models.MovieModel;
import edu.xda.abc.presenters.MoviePresenter;
import edu.xda.abc.utils.Utils;
import edu.xda.abc.views.MovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PSMoviePresenter implements MoviePresenter {
    private MovieView movieView;

    public PSMoviePresenter(MovieView movieView) {
        this.movieView = movieView;
    }

    @Override
    public void getAllMovies() {
        movieView.loadingData();
        Utils.getInstance()
                .findAllMovies()
                .enqueue(new Callback<List<MovieModel>>() {
                    @Override
                    public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                        if(response.body()!= null && response.isSuccessful()){
                            movieView.hideLoadingData();
                            movieView.setListMovies(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                        movieView.hideLoadingData();
                        movieView.onLoadError(t.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void getAllMoviesHD() {
        movieView.loadingData();
        Utils.getInstance()
                .findAllMoviesHD()
                .enqueue(new Callback<List<MovieModel>>() {
                    @Override
                    public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                        if(response.body()!= null && response.isSuccessful()) {
                            movieView.hideLoadingData();
                            movieView.setListMoviesHD(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                        movieView.hideLoadingData();
                        movieView.onLoadError(t.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void getAllMoviesTC() {
        movieView.loadingData();
        Utils.getInstance()
                .findAllMoviesTC()
                .enqueue(new Callback<List<MovieModel>>() {
                    @Override
                    public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                        if(response.body()!= null && response.isSuccessful()) {
                            movieView.hideLoadingData();
                            movieView.setListMoviesTC(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                        movieView.hideLoadingData();
                        movieView.onLoadError(t.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void getAllMoviesOther() {
        movieView.loadingData();
        Utils.getInstance()
                .findAllMoviesOther()
                .enqueue(new Callback<List<MovieModel>>() {
                    @Override
                    public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                        if(response.body()!= null && response.isSuccessful()) {
                            movieView.hideLoadingData();
                            movieView.setListMoviesOther(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                        movieView.hideLoadingData();
                        movieView.onLoadError(t.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void getTop4MovieNews() {
        movieView.loadingData();
        Utils.getInstance()
                .findTop4MovieNews()
                .enqueue(new Callback<List<MovieModel>>() {
                    @Override
                    public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                        if(response.body()!= null && response.isSuccessful()) {
                            movieView.hideLoadingData();
                            movieView.setListTop4MovieNews(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                        movieView.hideLoadingData();
                        movieView.onLoadError(t.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void getResultsMovies(String name) {
        movieView.loadingData();
        Utils.getInstance()
                .filterMoviesByName(name)
                .enqueue(new Callback<List<MovieModel>>() {
                    @Override
                    public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            movieView.hideLoadingData();
                            movieView.setResultsList(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                        movieView.hideLoadingData();
                        movieView.onLoadError(t.getLocalizedMessage());
                    }
                });
    }
}
