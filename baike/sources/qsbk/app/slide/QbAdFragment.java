package qsbk.app.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.ad.feedsad.qbad.QbAdItem;
import qsbk.app.fragments.BaseFragment;

public class QbAdFragment extends BaseFragment {
    private QbAdItem a;
    private int b;

    public QbAdFragment(QbAdItem qbAdItem, int i) {
        this.a = qbAdItem;
        this.b = i;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.a != null) {
            return this.a.getView(layoutInflater, null, viewGroup, this.b);
        }
        return null;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
