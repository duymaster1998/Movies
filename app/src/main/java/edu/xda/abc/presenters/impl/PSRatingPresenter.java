package edu.xda.abc.presenters.impl;

import java.util.List;

import edu.xda.abc.models.RatingModel;
import edu.xda.abc.models.ResponseUserRegisterModel;
import edu.xda.abc.presenters.RatingPresenter;
import edu.xda.abc.utils.Utils;
import edu.xda.abc.views.RatingView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PSRatingPresenter implements RatingPresenter {
    RatingView ratingView;

    public PSRatingPresenter(RatingView ratingView) {
        this.ratingView = ratingView;
    }

    @Override
    public void addRating(RatingModel ratingModel) {
        Utils.getInstance()
                .saveRating(ratingModel.getUserid(),ratingModel.getMovieid(),ratingModel.getContent(),ratingModel.getStar())
                .enqueue(new Callback<ResponseUserRegisterModel>() {
                    @Override
                    public void onResponse(Call<ResponseUserRegisterModel> call, Response<ResponseUserRegisterModel> response) {
                        if(response.body()!=null && response.isSuccessful()){
                            if(response.body().getStatus()!=null)
                            ratingView.ratingSuccess(response.body().getStatus());
                            else
                                updateRating(ratingModel);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUserRegisterModel> call, Throwable t) {
                        ratingView.ratingError("False");
                    }
                });
    }

    @Override
    public void updateRating(RatingModel ratingModel) {
        Utils.getInstance()
                .updateRating(ratingModel.getContent(),ratingModel.getStar(),ratingModel.getUserid(),ratingModel.getMovieid())
                .enqueue(new Callback<ResponseUserRegisterModel>() {
                    @Override
                    public void onResponse(Call<ResponseUserRegisterModel> call, Response<ResponseUserRegisterModel> response) {
                        if(response.body()!=null && response.isSuccessful()){
                            if(response.body().getStatus()!=null)
                                ratingView.ratingSuccess(response.body().getStatus());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUserRegisterModel> call, Throwable t) {
                            ratingView.ratingError("False");
                    }
                });

    }

    @Override
    public void getAllRating(int id) {
        Utils.getInstance()
                .findAllRatingMovies(id)
                .enqueue(new Callback<List<RatingModel>>() {
                    @Override
                    public void onResponse(Call<List<RatingModel>> call, Response<List<RatingModel>> response) {
                        if(response.isSuccessful() && response.body()!= null){
                            ratingView.setListRating(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<RatingModel>> call, Throwable t) {
                        ratingView.ratingError(t.getLocalizedMessage());
                    }
                });
    }
}
