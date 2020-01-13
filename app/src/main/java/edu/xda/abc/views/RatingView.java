package edu.xda.abc.views;

import java.util.List;

import edu.xda.abc.models.RatingModel;

public interface RatingView {
    void ratingSuccess(String mess);
    void ratingError(String message);
    void setListRating(List<RatingModel> listRating);
}
