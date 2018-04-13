package qsbk.app.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.ad.feedsad.qhad.QhAdItemData;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;

public class QHAdFragment extends BaseFragment {
    private QhAdItemData a;

    public QHAdFragment(QhAdItemData qhAdItemData) {
        this.a = qhAdItemData;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.a == null) {
            return null;
        }
        FeedsAd.getInstance().setAdShowed(this.a, AD_PROVIDER.QH);
        return this.a.getAdView().getView(null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
