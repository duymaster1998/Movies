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
import edu.xda.abc.models.ActorModel;
import edu.xda.abc.utils.Utils;


public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {

    Context context ;
    List<ActorModel> mData;
    ActorItemOnclickListener actorItemOnclickListener;


    public ActorAdapter(Context context, List<ActorModel> mData,ActorItemOnclickListener actorItemOnclickListener) {
        this.context = context;
        this.mData = mData;
        this.actorItemOnclickListener = actorItemOnclickListener;
    }


    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,viewGroup,false);
        return new ActorViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder myViewHolder, int i) {


        myViewHolder.TvTitle.setText(mData.get(i).getActorname());
        Glide.with(context).load(Utils.URI_IMAGE_ACTOR + mData.get(i).getActorimage()).into(myViewHolder.ImgMovie);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ActorViewHolder extends RecyclerView.ViewHolder {


        private TextView TvTitle;
        private ImageView ImgMovie;


        public ActorViewHolder(@NonNull View itemView) {

            super(itemView);
            TvTitle = itemView.findViewById(R.id.item_movie_title);
            ImgMovie = itemView.findViewById(R.id.item_movie_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    actorItemOnclickListener.onClickActor(mData.get(getAdapterPosition()));
                }
            });

        }
    }
}
