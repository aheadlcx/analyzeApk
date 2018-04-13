package qsbk.app.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.model.Qsjx;
import qsbk.app.widget.QsjxCell;

public class QsjxFragment extends BaseFragment {
    private Qsjx a;
    private int b;

    public static QsjxFragment newInstance(Qsjx qsjx, int i) {
        QsjxFragment qsjxFragment = new QsjxFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(bo.QSJX.getTypeValue(), qsjx);
        bundle.putInt("position", i);
        qsjxFragment.setArguments(bundle);
        return qsjxFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = (Qsjx) getArguments().getSerializable(bo.QSJX.getTypeValue());
        this.b = getArguments().getInt("position");
        if (this.a == null) {
            return null;
        }
        QsjxCell qsjxCell = new QsjxCell();
        qsjxCell.performCreate(this.b, viewGroup, this.a);
        qsjxCell.performUpdate(this.b, viewGroup, this.a);
        return qsjxCell.getCellView();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
