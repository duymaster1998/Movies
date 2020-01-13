package edu.xda.abc.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import edu.xda.abc.R;
import edu.xda.abc.adapters.FragmentAdapterData;
import edu.xda.abc.adapters.TabLayoutAdapter;
import edu.xda.abc.fragments.ActorFragment;
import edu.xda.abc.fragments.ContentDetailFragment;
import edu.xda.abc.fragments.RatingFragment;
import edu.xda.abc.utils.Utils;


public class MovieDetailActivity extends AppCompatActivity implements FragmentAdapterData {
    private ImageView MovieThumbnailImg, MovieCoverImg;
    private TextView tv_title, tv_description;
    private FloatingActionButton play_fab;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        iniViews();
    }

    void iniViews() {
        play_fab = findViewById(R.id.play_fab);
        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MoviePlayActivity.class);
                intent.putExtra("urimovie",getIntent().getExtras().getString("uri"));
                startActivity(intent);
            }
        });
        String movieTitle = getIntent().getExtras().getString("title");
        String imageResourceId = getIntent().getExtras().getString("imgURL");
        String imagecover = getIntent().getExtras().getString("imgCover");
        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(Utils.URI_IMAGE + imageResourceId).into(MovieThumbnailImg);
        Glide.with(this).load(Utils.URI_IMAGE + imageResourceId).into(MovieThumbnailImg);
        MovieCoverImg = findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(Utils.URI_IMAGE + imagecover).into(MovieCoverImg);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
//        tv_description = findViewById(R.id.detail_movie_desc);
        // setup animation
        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
//        tablayout
        viewPager = findViewById(R.id.view_page);
        viewPager.setCurrentItem(3);
        viewPager.setAdapter(new TabLayoutAdapter(getSupportFragmentManager(),this));
        tabLayout = findViewById(R.id.tab_layout1);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public Fragment getItemFragment(int position) {
        switch (position) {
            case 0:
                ContentDetailFragment contentDetailFragment = new ContentDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("bddaodien", getIntent().getExtras().getString("daodien"));
                bundle.putString("bdquocgia", getIntent().getExtras().getString("quocgia"));
                bundle.putString("bdngaychieu", getIntent().getExtras().getString("ngaychieu"));
                bundle.putString("bdnoidung", getIntent().getExtras().getString("noidung"));
                contentDetailFragment.setArguments(bundle);
                return contentDetailFragment;
            case 1:
                ActorFragment actorFragment = new ActorFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("idmoviea",getIntent().getExtras().getInt("idmovie"));
                actorFragment.setArguments(bundle1);
                return actorFragment;
            case 2:
                RatingFragment ratingFragment = new RatingFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("idmoviea",getIntent().getExtras().getInt("idmovie"));
                ratingFragment.setArguments(bundle2);
                return ratingFragment;
            default:
                return null;
        }
    }
}
