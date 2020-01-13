package edu.xda.abc.presenters.impl;

import java.util.List;

import edu.xda.abc.models.ActorModel;
import edu.xda.abc.presenters.ActorPresenter;
import edu.xda.abc.utils.Utils;
import edu.xda.abc.views.ActorView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PSActorPresenter implements ActorPresenter {
    ActorView actorView;

    public PSActorPresenter(ActorView actorView) {
        this.actorView = actorView;
    }

    @Override
    public void getAllActorMovie(int id) {
        actorView.loadingData();
        Utils.getInstance()
                .findAllActorMovie(id)
                .enqueue(new Callback<List<ActorModel>>() {
                    @Override
                    public void onResponse(Call<List<ActorModel>> call, Response<List<ActorModel>> response) {
                        if(response.body()!= null && response.isSuccessful()) {
                            actorView.hideLoadingData();
                            actorView.setResultsListActor(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ActorModel>> call, Throwable t) {
                        actorView.hideLoadingData();
                        actorView.onLoadError(t.getLocalizedMessage());
                    }
                });
    }
}
