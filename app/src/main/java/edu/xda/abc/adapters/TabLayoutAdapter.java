package edu.xda.abc.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.xda.abc.fragments.ActorFragment;
import edu.xda.abc.fragments.ContentDetailFragment;
import edu.xda.abc.fragments.RatingFragment;


public class TabLayoutAdapter extends FragmentPagerAdapter {
    private ContentDetailFragment contentDetailFragment = new ContentDetailFragment();
    private ActorFragment actorFragment = new ActorFragment();
    private RatingFragment ratingFragment= new RatingFragment();
    private FragmentAdapterData fragmentAdapterData;
    String listTab[] = {"Chi tiết","Diễn viên","Đánh giá"};
    public TabLayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabLayoutAdapter(FragmentManager fm, FragmentAdapterData fragmentAdapterData) {
        super(fm);
        this.fragmentAdapterData = fragmentAdapterData;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentAdapterData.getItemFragment(position);
    }


    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
