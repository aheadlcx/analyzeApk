package cn.v6.sixrooms.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.v6.sixrooms.ui.fragment.BaseFragment;

@SuppressLint({"ValidFragment"})
public class VLPagerView$VLFragmentPage extends BaseFragment {
    private int a;
    private ViewGroup b;
    private View c;
    private VLPagerView$VLPagerDelegate d;

    public VLPagerView$VLFragmentPage(int i, VLPagerView$VLPagerDelegate vLPagerView$VLPagerDelegate) {
        this.a = i;
        this.d = vLPagerView$VLPagerDelegate;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.b = viewGroup;
        this.c = this.d.onCreatePage(this.b, this.a);
        return this.c;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.d.onDestroyPage(this.b, this.a, this.c);
    }
}
