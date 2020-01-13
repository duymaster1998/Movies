package edu.xda.abc.presenters;

import edu.xda.abc.models.RatingModel;

public interface RatingPresenter {
    void addRating(RatingModel ratingModel);
    void updateRating(RatingModel ratingModel);
    void getAllRating(int id);
}
