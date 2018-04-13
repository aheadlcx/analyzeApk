package qsbk.app.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.ad.feedsad.baiduad.BaiduAdItemData;
import qsbk.app.fragments.BaseFragment;

public class BdAdFragment extends BaseFragment {
    private BaiduAdItemData a;

    public BdAdFragment(BaiduAdItemData baiduAdItemData) {
        this.a = baiduAdItemData;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.a != null) {
            return this.a.getView().getView(null);
        }
        return null;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
