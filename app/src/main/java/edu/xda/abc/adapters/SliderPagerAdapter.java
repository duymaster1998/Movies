package edu.xda.abc.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import edu.xda.abc.R;
import edu.xda.abc.models.MovieModel;
import edu.xda.abc.utils.Utils;


public class SliderPagerAdapter extends PagerAdapter {
    private Context mContext ;
    private List<MovieModel> mList ;
    MovieItemClickListener movieItemClickListener;
    @BindView(R.id.slide_img)
    ImageView slide_img;

    public SliderPagerAdapter(Context mContext, List<MovieModel> mList,MovieItemClickListener movieItemClickListener) {
        this.mContext = mContext;
        this.mList = mList;
        this.movieItemClickListener=movieItemClickListener;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.item_slide,null);

        ImageView slideImg = slideLayout.findViewById(R.id.slide_img);
        TextView slideText = slideLayout.findViewById(R.id.slide_title);
        Glide.with(container)
                .load(Utils.URI_IMAGE+mList.get(position).getMovieimage())
                .into(slideImg);
        slideText.setText(mList.get(position).getMoviename());
        container.addView(slideLayout);
        slideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieItemClickListener.onMovieClick(mList.get(position),slideImg);
            }
        });
        return slideLayout;






    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
