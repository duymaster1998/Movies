package edu.xda.abc.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import edu.xda.abc.R;
import edu.xda.abc.adapters.RatingAdapter;
import edu.xda.abc.db.ShPreferences;
import edu.xda.abc.dialogs.AlertDialog;
import edu.xda.abc.models.RatingModel;
import edu.xda.abc.presenters.RatingPresenter;
import edu.xda.abc.presenters.impl.PSRatingPresenter;
import edu.xda.abc.views.RatingView;

public class RatingFragment extends Fragment implements RatingView {
    private SweetAlertDialog alertDialog;
    private View rating;
    @BindView(R.id.rv_rating1)
    RecyclerView rvRating;
    @BindView(R.id.edt_content)
    EditText content;
    @BindView(R.id.ratingshow)
    RatingBar ratingBar;
    @BindView(R.id.btnSend)
    ImageView btnsend;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tvcount)
    TextView tvcount;
    @BindView(R.id.tbratingstar)
    RatingBar bar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rating = inflater.inflate(R.layout.fragment_rating,container,false);
        ButterKnife.bind(this,rating);
        return rating;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RatingPresenter ratingPresenter = new PSRatingPresenter(this);
//        init(ratingPresenter);
        ratingPresenter.getAllRating(getArguments().getInt("idmoviea"));
        saveRating(ratingPresenter);

    }
    public void init(RatingPresenter ratingPresenter){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ratingPresenter.getAllRating(getArguments().getInt("idmoviea"));
            }
        });
    }
    public  void saveRating(RatingPresenter ratingPresenter){
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShPreferences preferences = new ShPreferences(getContext());
                RatingModel ratingModel = new RatingModel();
                ratingModel.setMovieid(getArguments().getInt("idmoviea"));
                ratingModel.setUserid(preferences.getModel().getUser().getUserid());
                ratingModel.setContent(content.getText().toString());
                ratingModel.setStar((int)ratingBar.getRating());
                ratingPresenter.addRating(ratingModel);
                content.setText("");
            }
        });

    }
    @Override
    public void ratingSuccess(String mess) {
        new AlertDialog(getContext()).alertDialogSuccess("Success", mess);
        RatingPresenter ratingPresenter = new PSRatingPresenter(this);
        ratingPresenter.getAllRating(getArguments().getInt("idmoviea"));
    }

    @Override
    public void ratingError(String message) {
        new AlertDialog(getContext()).alertDialogError("Error", message);

    }

    @Override
    public void setListRating(List<RatingModel> listRating) {

        if (listRating.size()!=0) {
            tvcount.setText(""+listRating.size()+"");
            bar.setRating(star(listRating));
            RatingAdapter adapterAll = new RatingAdapter(getContext(), listRating);
            rvRating.setAdapter(adapterAll);
            rvRating.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }else {
            tvcount.setText("0");
            bar.setRating(0);
        }

    }
     float star(List<RatingModel> listRating){
        float tong=0;
        for (int i=0;i<listRating.size();i++){
            tong+=listRating.get(i).getStar();
        }
        return tong/listRating.size();
     }

}
