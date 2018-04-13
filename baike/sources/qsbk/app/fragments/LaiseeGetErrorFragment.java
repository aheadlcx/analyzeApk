package qsbk.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.model.Laisee;

public class LaiseeGetErrorFragment extends BaseFragment {
    private View a;
    private Laisee b;
    private TextView c;
    private TextView d;

    public static LaiseeGetErrorFragment newInstance(Laisee laisee) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("laisee", laisee);
        LaiseeGetErrorFragment laiseeGetErrorFragment = new LaiseeGetErrorFragment();
        laiseeGetErrorFragment.setArguments(bundle);
        return laiseeGetErrorFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return layoutInflater.inflate(R.layout.fragment_laisee_get_error, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view, bundle);
    }

    protected void a(View view, Bundle bundle) {
        if (getArguments() != null) {
            this.b = (Laisee) getArguments().getSerializable("laisee");
        }
        if (this.b == null) {
            getActivity().finish();
            return;
        }
        this.c = (TextView) view.findViewById(R.id.invlidate);
        this.d = (TextView) view.findViewById(R.id.bottom);
        this.a = view.findViewById(R.id.close);
        this.a.setOnClickListener(new dh(this));
        a();
    }

    void a() {
        if (this.b.isEmpty()) {
            this.c.setText("手速不够快，红包派完了");
            this.d.setText("查看领取详情 >");
            this.d.setOnClickListener(new di(this));
        } else if (this.b.isExpired()) {
            if (this.b.isActivityLaisee()) {
                this.d.setText("查看糗百钱袋 >");
                this.d.setOnClickListener(new dj(this));
            } else {
                this.d.setText("查看领取详情 >");
                this.d.setOnClickListener(new dk(this));
            }
            this.c.setText("该红包已过期");
        }
    }
}
