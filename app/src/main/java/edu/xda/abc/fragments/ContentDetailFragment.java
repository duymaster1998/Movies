package edu.xda.abc.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.xda.abc.R;

public class ContentDetailFragment extends Fragment {
    private View contenDetail;
    @BindView(R.id.tv_NDDaoDien)
    TextView tvDaoDien;
    @BindView(R.id.tv_QuocGia)
    TextView tvQuocGia;
    @BindView(R.id.tv_NDNgayChieu)
    TextView tvNgayChieu;
    @BindView(R.id.detail_movie_desc)
    TextView tvNoiDung;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contenDetail =inflater.inflate(R.layout.fragment_content,container,false);
        ButterKnife.bind(this,contenDetail);
        return contenDetail;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null){
            String daodien = getArguments().getString("bddaodien");
            String quocGia = getArguments().getString("bdquocgia");
            String ngaychieu = getArguments().getString("bdngaychieu");
            String noidung = getArguments().getString("bdnoidung");
            tvDaoDien.setText(daodien);
            tvQuocGia.setText(quocGia);
            tvNgayChieu.setText(ngaychieu);
            tvNoiDung.setText(noidung);

        }

    }
}
