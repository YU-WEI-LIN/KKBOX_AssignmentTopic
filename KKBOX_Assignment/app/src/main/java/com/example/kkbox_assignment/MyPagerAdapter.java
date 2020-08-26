package com.example.kkbox_assignment;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter {
    private List<View> mPager;

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (childCount>0){
            childCount --;
            return POSITION_NONE;
        }
        return  super.getItemPosition(object);
    }

    private int childCount = 0;

    public MyPagerAdapter(List<View> mPager) {
        this.mPager = mPager;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mPager.get(position).setTag(position);
        ((ViewPager) container).addView(mPager.get(position));
        return mPager.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void notifyDataSetChanged() {
        childCount = getCount();
        super.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "新發行";
        }else {
            return "排行榜";
        }
    }

    @Override
    public int getCount() {
        return mPager.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }
}
