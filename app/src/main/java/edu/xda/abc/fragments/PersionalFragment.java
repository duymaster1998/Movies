package edu.xda.abc.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.xda.abc.R;
import edu.xda.abc.activities.LoginActivity;
import edu.xda.abc.db.ShPreferences;
import edu.xda.abc.models.ResponseLoginModel;
import edu.xda.abc.utils.Utils;


public class PersionalFragment extends Fragment {
    @BindView(R.id.btnDangXuat)
    TextView tvDangXuat;
    @BindView(R.id.fullName)
    TextView fullName;
    @BindView(R.id.email)
    TextView email;
//    @BindView(R.id.userImage)
//    CircleImageView userImage;
    @BindView(R.id.userImage)
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View persionalFragment = inflater.inflate(R.layout.fragment_persional, container, false);
        ButterKnife.bind(this, persionalFragment);
        return persionalFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        ShPreferences shPreferences = new ShPreferences(getContext());
        ResponseLoginModel model = shPreferences.getModel();
        Glide.with(getContext())
                .load(Utils.URI_IMAGE_USER+model.getUser().getUserimage())
                .into(imageView);
        fullName.setText(model.getUser().getFullname());
        email.setText(model.getUser().getGmail());
        tvDangXuat.setOnClickListener   (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shPreferences.clearAccount();
                changeActivity();
            }
        });
    }

    public void changeActivity(){
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
