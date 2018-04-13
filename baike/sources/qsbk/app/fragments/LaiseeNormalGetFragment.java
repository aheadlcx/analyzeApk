package qsbk.app.fragments;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.Laisee;

public class LaiseeNormalGetFragment extends BaseFragment {
    private ImageView a;
    private TextView b;
    private TextView c;
    private View d;
    private ImageView e;
    private AnimationDrawable f;
    private Laisee g;
    private EncryptHttpTask h;
    private boolean i;
    private View j;
    private Button k;
    private TextView l;

    public static LaiseeNormalGetFragment newInstance(Laisee laisee) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("laisee", laisee);
        LaiseeNormalGetFragment laiseeNormalGetFragment = new LaiseeNormalGetFragment();
        laiseeNormalGetFragment.setArguments(bundle);
        return laiseeNormalGetFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return layoutInflater.inflate(R.layout.fragment_laisee_get, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view, bundle);
    }

    public void onResume() {
        super.onResume();
        c();
    }

    protected void a(View view, Bundle bundle) {
        if (getArguments() != null) {
            this.g = (Laisee) getArguments().getSerializable("laisee");
        }
        if (this.g == null) {
            getActivity().finish();
            return;
        }
        this.e = (ImageView) view.findViewById(R.id.avatar);
        this.l = (TextView) view.findViewById(R.id.name);
        this.j = view.findViewById(R.id.bind_container);
        this.k = (Button) view.findViewById(R.id.bind_phone);
        this.c = (TextView) view.findViewById(R.id.content);
        this.b = (TextView) view.findViewById(R.id.bottom);
        this.a = (ImageView) view.findViewById(R.id.red_envelope_open);
        this.d = view.findViewById(R.id.close);
        this.d.setOnClickListener(new dl(this));
        c();
    }

    void a() {
        Drawable drawable = this.a.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            this.f = (AnimationDrawable) drawable;
            this.f.stop();
        }
        this.a.setImageResource(R.drawable.red_envelope_open);
    }

    void b() {
        this.a.setImageResource(R.drawable.red_envelope_spin_rotate);
        Drawable drawable = this.a.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            this.f = (AnimationDrawable) drawable;
            this.f.start();
        }
    }

    void c() {
        int i = 8;
        this.a.setOnClickListener(new dm(this));
        this.c.setText(this.g.content);
        this.j.setVisibility(QsbkApp.currentUser.hasPhone() ? 8 : 0);
        this.k.setOnClickListener(new dn(this));
        ImageView imageView = this.a;
        if (QsbkApp.currentUser.hasPhone()) {
            i = 0;
        }
        imageView.setVisibility(i);
        a(this.g.sendUser.getUid() + "", this.g.sendUser.getIcon(), this.e);
        this.l.setText(this.g.sendUser.getLogin());
        if (!QsbkApp.currentUser.hasPhone()) {
            this.b.setText("");
            this.b.setBackgroundResource(R.drawable.ic_laisee_logo);
        } else if (this.g.isP2P()) {
            this.b.setText(this.g.bottom);
            this.b.setBackgroundResource(0);
            this.b.setOnClickListener(new dp(this));
        } else {
            this.b.setText("查看领取详情 >");
            this.b.setBackgroundResource(0);
            this.b.setOnClickListener(new do(this));
        }
    }

    protected void a(String str, String str2, ImageView imageView) {
        FrescoImageloader.displayAvatar(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str2, str));
    }

    private void d() {
        if (!this.i) {
            this.h = new EncryptHttpTask(null, String.format(Constants.LAISEE_FETCH, new Object[]{this.g.id}), new dq(this));
            this.i = true;
            Map hashMap = new HashMap();
            hashMap.put("secret", this.g.secret);
            this.h.setMapParams(hashMap);
            this.h.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.h != null) {
            this.h.cancel(true);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        c();
    }
}
