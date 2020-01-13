package edu.xda.abc.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.xda.abc.R;
import edu.xda.abc.models.MovieModel;
import edu.xda.abc.utils.Utils;


public class MovieAdapterAll extends RecyclerView.Adapter<MovieAdapterAll.MyViewHolderAll> {

    Context context ;
    List<MovieModel> movieList;
    MovieItemClickListener movieItemClickListener;


    public MovieAdapterAll(Context context, List<MovieModel> movieList, MovieItemClickListener listener) {
        this.context = context;
        this.movieList = movieList;
        movieItemClickListener = listener;
    }


    @NonNull
    @Override
    public MyViewHolderAll onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_all,viewGroup,false);
        return new MyViewHolderAll(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderAll myViewHolder, int i) {


        myViewHolder.TvTitle.setText(movieList.get(i).getMoviename());
        Glide.with(context)
                .load(Utils.URI_IMAGE + movieList.get(i).getMovieimage())
                .into(myViewHolder.ImgMovie);
        myViewHolder.tvDate.setText(movieList.get(i).getReleasedate());


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolderAll extends RecyclerView.ViewHolder {


        private TextView TvTitle;
        private ImageView ImgMovie;
        private TextView   tvDate;


        public MyViewHolderAll(@NonNull View itemView) {

            super(itemView);
            TvTitle = itemView.findViewById(R.id.item_movie_name);
            ImgMovie = itemView.findViewById(R.id.item_movie_img_all);
            tvDate = itemView.findViewById(R.id.item_movie_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    movieItemClickListener.onMovieClick(movieList.get(getAdapterPosition()),ImgMovie);
                }
            });

        }
    }
}
