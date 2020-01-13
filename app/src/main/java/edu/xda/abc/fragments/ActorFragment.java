package edu.xda.abc.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.xda.abc.R;
import edu.xda.abc.adapters.ActorAdapter;
import edu.xda.abc.adapters.ActorItemOnclickListener;
import edu.xda.abc.dialogs.AlertDialog;
import edu.xda.abc.models.ActorModel;
import edu.xda.abc.presenters.impl.PSActorPresenter;
import edu.xda.abc.views.ActorView;


public class ActorFragment extends Fragment implements ActorView, ActorItemOnclickListener {
    private View actorFragment;
    @BindView(R.id.rv_DienVien)
    RecyclerView rvDienVien;
    @BindView(R.id.tv_info)
    TextView tvInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        actorFragment =inflater.inflate(R.layout.fragment_actor,container,false);
        ButterKnife.bind(this,actorFragment);
        return actorFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PSActorPresenter actorPresenter = new PSActorPresenter(this);
        actorPresenter.getAllActorMovie(getArguments().getInt("idmoviea"));
    }


    @Override
    public void setResultsListActor(List<ActorModel> models) {
        if (models.size() != 0) {
            tvInfo.setText(models.get(0).getInfomation());
            ActorAdapter adapterAll = new ActorAdapter(getContext(), models, this);
            rvDienVien.setAdapter(adapterAll);
            rvDienVien.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
        else
            Log.d("bua","false");
    }

    @Override
    public void onLoadError(String message) {
        if(message!=null)
        new AlertDialog(getContext())
                .alertDialogError("Error!", message);
    }

    @Override
    public void loadingData() {

    }

    @Override
    public void hideLoadingData() {

    }


    @Override
    public void onClickActor(ActorModel actorModel) {
        tvInfo.setText(actorModel.getInfomation());
    }
}
