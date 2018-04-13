package cn.v6.sixrooms.base;

import android.view.View;
import android.view.ViewGroup;

public interface VLPagerView$VLPagerDelegate {
    View onCreatePage(ViewGroup viewGroup, int i);

    void onDestroyPage(ViewGroup viewGroup, int i, View view);

    int onGetPageCount();
}
