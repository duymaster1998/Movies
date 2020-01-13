package edu.xda.abc.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import edu.xda.abc.R;
import edu.xda.abc.models.RatingModel;


public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.MyViewHolderAll> {

    Context context ;
    List<RatingModel> ratingModels;


    public RatingAdapter(Context context, List<RatingModel> ratingModelList) {
        this.context = context;
        this.ratingModels = ratingModelList;
    }


    @NonNull
    @Override
    public MyViewHolderAll onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_rating,viewGroup,false);
        return new MyViewHolderAll(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderAll myViewHolder, int i) {


        myViewHolder.tvname.setText(ratingModels.get(i).getUsername());
        myViewHolder.ratingBar.setRating(ratingModels.get(i).getStar());
        myViewHolder.tvCmt.setText(ratingModels.get(i).getContent());


    }

    @Override
    public int getItemCount() {
        return ratingModels.size();
    }

    public class MyViewHolderAll extends RecyclerView.ViewHolder {


        private TextView tvname;
        private RatingBar ratingBar;
        private TextView   tvCmt;


        public MyViewHolderAll(@NonNull View itemView) {

            super(itemView);
            tvname = itemView.findViewById(R.id.ratingnameuser);
            ratingBar = itemView.findViewById(R.id.ratingstar);
            tvCmt = itemView.findViewById(R.id.usercmt);


        }
    }
}
