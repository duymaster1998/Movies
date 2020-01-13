package edu.xda.abc.views;


import java.util.List;

import edu.xda.abc.models.ActorModel;


public interface ActorView {
    void setResultsListActor(List<ActorModel> actor);
    void onLoadError(String message);
    void loadingData();
    void hideLoadingData();
}
