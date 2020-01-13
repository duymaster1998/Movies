package edu.xda.abc.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.xda.abc.R;
import edu.xda.abc.activities.MovieDetailActivity;
import edu.xda.abc.adapters.MovieAdapter;
import edu.xda.abc.adapters.MovieItemClickListener;
import edu.xda.abc.adapters.SliderPagerAdapter;
import edu.xda.abc.dialogs.AlertDialog;
import edu.xda.abc.models.MovieModel;
import edu.xda.abc.presenters.MoviePresenter;
import edu.xda.abc.presenters.impl.PSMoviePresenter;
import edu.xda.abc.views.MovieView;

public class HomeFragment extends Fragment implements MovieView, MovieItemClickListener {
    @BindView(R.id.slider_pager)
    ViewPager sliderpager;
    @BindView(R.id.indicator)
    TabLayout indicator;
    @BindView(R.id.Rv_hanhdong)
    RecyclerView rvMoviesHd ;
    @BindView(R.id.Rv_tinhcam)
    RecyclerView rvMoviesTc ;
    @BindView(R.id.Rv_Other)
    RecyclerView rvMoviesOther;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MoviePresenter moviePresenter = new PSMoviePresenter(this);
        moviePresenter.getAllMoviesHD();
        moviePresenter.getAllMoviesTC();
        moviePresenter.getAllMoviesOther();
        moviePresenter.getTop4MovieNews();

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
        Toast.makeText(getContext(), movie.getMoviename(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setListMovies(List<MovieModel> movieList) {

    }

    @Override
    public void setListMoviesHD(List<MovieModel> movieList) {
        MovieAdapter adapter = new MovieAdapter(getContext(),movieList,this);
        rvMoviesHd.setAdapter(adapter);
        rvMoviesHd.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void setListMoviesTC(List<MovieModel> movieList) {
        MovieAdapter adapter = new MovieAdapter(getContext(),movieList,this);
        rvMoviesTc.setAdapter(adapter);
        rvMoviesTc.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void setListMoviesOther(List<MovieModel> movieList) {
        MovieAdapter adapter = new MovieAdapter(getContext(),movieList,this);
        rvMoviesOther.setAdapter(adapter);
        rvMoviesOther.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void setListTop4MovieNews(List<MovieModel> movieNews) {
        SliderPagerAdapter adapter = new SliderPagerAdapter(getContext(),movieNews,this);
        sliderpager.setAdapter(adapter);
        // setup timer
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(movieNews),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);
    }

    @Override
    public void setResultsList(List<MovieModel> models) {

    }

    @Override
    public void onLoadError(String message) {
        if (message!=null)
        new AlertDialog(getContext())
                .alertDialogError("Error!", message);
    }

    @Override
    public void loadingData() {

    }

    @Override
    public void hideLoadingData() {

    }


    class SliderTimer extends TimerTask {
        List<MovieModel> list;

        public SliderTimer(List<MovieModel> list) {
            this.list = list;
        }

        @Override
        public void run() {
            assert getActivity() != null;
           getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem() < list.size() - 1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem() + 1);
                    } else
                        sliderpager.setCurrentItem(0);
                }
            });
        }
    }
}
