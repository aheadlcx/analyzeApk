package qsbk.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.fragments.BrowseBaseFragment;
import qsbk.app.fragments.BrowseGIFVideoFragment;
import qsbk.app.fragments.BrowseImgFragment;
import qsbk.app.fragments.BrowseLongImgFragment;
import qsbk.app.fragments.BrowseUltraFragment;
import qsbk.app.model.ImageInfo;

public class ImageFragmentPagerAdapter extends FragmentStatePagerAdapter {
    SparseArray<Fragment> a = new SparseArray();
    List<ImageInfo> b = new ArrayList();
    private BrowseBaseFragment c;

    public ImageFragmentPagerAdapter(FragmentManager fragmentManager, List<? extends ImageInfo> list) {
        super(fragmentManager);
        this.b.addAll(list);
    }

    public int getCount() {
        return this.b.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        this.a.put(i, fragment);
        return fragment;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyItem(viewGroup, i, obj);
        this.a.remove(i);
    }

    public Fragment getItem(int i) {
        ImageInfo imageInfo = (ImageInfo) this.b.get(i);
        switch (bu.a[imageInfo.mediaFormat.ordinal()]) {
            case 1:
            case 2:
                return BrowseUltraFragment.newInstance(imageInfo);
            case 3:
                return BrowseLongImgFragment.newInstance(imageInfo);
            case 4:
                return BrowseGIFVideoFragment.newInstance(imageInfo);
            default:
                return BrowseImgFragment.newInstance(imageInfo);
        }
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        if (getCurrentFragment() != obj) {
            this.c = (BrowseBaseFragment) obj;
        }
        super.setPrimaryItem(viewGroup, i, obj);
    }

    public BrowseBaseFragment getCurrentFragment() {
        return this.c;
    }

    public Fragment getFragmentAt(int i) {
        return (Fragment) this.a.get(i);
    }
}
