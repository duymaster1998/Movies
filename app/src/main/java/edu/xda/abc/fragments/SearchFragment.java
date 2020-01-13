package edu.xda.abc.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.xda.abc.R;
import edu.xda.abc.activities.MovieDetailActivity;
import edu.xda.abc.adapters.MovieAdapterAll;
import edu.xda.abc.adapters.MovieItemClickListener;
import edu.xda.abc.dialogs.AlertDialog;
import edu.xda.abc.models.MovieModel;
import edu.xda.abc.presenters.MoviePresenter;
import edu.xda.abc.presenters.impl.PSMoviePresenter;
import edu.xda.abc.views.MovieView;


public class SearchFragment extends Fragment implements MovieItemClickListener, MovieView {
    @BindView(R.id.rv_allmovie)
    RecyclerView rvAllMovie;
    @BindView(R.id.searchview)
    EditText searchview;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MoviePresenter moviePresenter = new PSMoviePresenter(this);
        moviePresenter.getAllMovies();

        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final Runnable setImageRunnable = new Runnable() {
                    public void run() {
                        moviePresenter.getResultsMovies(s.toString());
                    }
                };

                TimerTask task = new TimerTask(){
                    public void run() {
                        assert getActivity() != null;
                        getActivity().runOnUiThread(setImageRunnable);
                    }
                };

                Timer timer = new Timer();
                timer.schedule(task, 300);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMovieClick(MovieModel movie, ImageView movieImageView) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("idmovie",movie.getMovieid());
        intent.putExtra("title", movie.getMoviename());
        intent.putExtra("imgURL", movie.getMovieimage());
        intent.putExtra("imgCover", movie.getBackgroundimage());
        intent.putExtra("daodien",movie.getDirector());
        intent.putExtra("quocgia",movie.getNation());
        intent.putExtra("ngaychieu",movie.getReleasedate());
        intent.putExtra("noidung",movie.getContent());
        intent.putExtra("uri",movie.getTrailer());
        // send content

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                movieImageView, "sharedName");

        startActivity(intent, options.toBundle());
//        startActivity(intent1);
        Toast.makeText(getContext(), movie.getMoviename(), Toast.LENGTH_LONG).show();
    }
    @Override
    public void setListMovies(List<MovieModel> movieList) {
        MovieAdapterAll adapterAll = new MovieAdapterAll(getContext(),movieList,this);
        rvAllMovie.setAdapter(adapterAll);
        rvAllMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setListMoviesHD(List<MovieModel> movieList) {

    }

    @Override
    public void setListMoviesTC(List<MovieModel> movieList) {

    }

    @Override
    public void setListMoviesOther(List<MovieModel> movieList) {

    }

    @Override
    public void setListTop4MovieNews(List<MovieModel> movieNews) {

    }

    @Override
    public void setResultsList(List<MovieModel> models) {
        MovieAdapterAll adapterAll = new MovieAdapterAll(getContext(),models,this);
        rvAllMovie.setAdapter(adapterAll);
        rvAllMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapterAll.notifyDataSetChanged();
    }

    @Override
    public void onLoadError(String message) {
        if(message!=null)
        new AlertDialog(getContext())
                .alertDialogError("Error!", message);
    }

    @Override
    public void loadingData() {
    }

    @Override
    public void hideLoadingData() {

    }

}
