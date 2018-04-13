package com.zhihu.matisse.internal.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.ui.PreviewImageItemFragment;
import com.zhihu.matisse.internal.ui.PreviewVideoItemFragment;
import java.util.ArrayList;
import java.util.List;

public class PreviewPagerAdapter extends FragmentPagerAdapter {
    private Fragment mCurrentFragment;
    private ArrayList<Item> mItems = new ArrayList();
    private OnPrimaryItemSetListener mListener;

    interface OnPrimaryItemSetListener {
        void onPrimaryItemSet(int i);
    }

    public PreviewPagerAdapter(FragmentManager fragmentManager, OnPrimaryItemSetListener onPrimaryItemSetListener) {
        super(fragmentManager);
        this.mListener = onPrimaryItemSetListener;
    }

    public Fragment getItem(int i) {
        if (((Item) this.mItems.get(i)).isVideo()) {
            return PreviewVideoItemFragment.newInstance((Item) this.mItems.get(i));
        }
        return PreviewImageItemFragment.newInstance((Item) this.mItems.get(i));
    }

    public int getCount() {
        return this.mItems.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        if (fragment instanceof PreviewImageItemFragment) {
            if (getMediaItem(i).isVideo()) {
                return getItem(i);
            }
            return fragment;
        } else if (!(fragment instanceof PreviewVideoItemFragment) || getMediaItem(i).isVideo()) {
            return fragment;
        } else {
            return getItem(i);
        }
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        super.setPrimaryItem(viewGroup, i, obj);
        this.mCurrentFragment = (Fragment) obj;
        if (this.mListener != null) {
            this.mListener.onPrimaryItemSet(i);
        }
    }

    public Item getMediaItem(int i) {
        return (Item) this.mItems.get(i);
    }

    public Fragment getCurrentFragment() {
        return this.mCurrentFragment;
    }

    public void addAll(List<Item> list) {
        this.mItems.addAll(list);
    }
}
