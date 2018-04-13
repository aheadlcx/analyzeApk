package qsbk.app.fragments;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
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
import qsbk.app.model.Laisee;
import qsbk.app.widget.ObservableTextView;

public class LaiseeEventGetFragment extends BaseFragment {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private ObservableTextView g;
    private View h;
    private AnimationDrawable i;
    private Laisee j;
    private EncryptHttpTask k;
    private boolean l;
    private View m;
    private Button n;

    public static LaiseeEventGetFragment newInstance(Laisee laisee) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("laisee", laisee);
        LaiseeEventGetFragment laiseeEventGetFragment = new LaiseeEventGetFragment();
        laiseeEventGetFragment.setArguments(bundle);
        return laiseeEventGetFragment;
    }

    public void onResume() {
        super.onResume();
        c();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return layoutInflater.inflate(R.layout.fragment_laisee_event_get, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view, bundle);
    }

    protected void a(View view, Bundle bundle) {
        if (getArguments() != null) {
            this.j = (Laisee) getArguments().getSerializable("laisee");
        }
        if (this.j == null) {
            getActivity().finish();
            return;
        }
        this.m = view.findViewById(R.id.bind_container);
        this.n = (Button) view.findViewById(R.id.bind_phone);
        this.c = (TextView) view.findViewById(R.id.desc);
        this.g = (ObservableTextView) view.findViewById(R.id.detail);
        this.b = (TextView) view.findViewById(R.id.title);
        this.e = (TextView) view.findViewById(R.id.content);
        this.f = (TextView) view.findViewById(R.id.last_desc);
        this.d = (TextView) view.findViewById(R.id.bottom);
        this.a = (ImageView) view.findViewById(R.id.red_envelope_open);
        this.h = view.findViewById(R.id.close);
        this.h.setOnClickListener(new da(this));
        c();
    }

    void a() {
        Drawable drawable = this.a.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            this.i = (AnimationDrawable) drawable;
            this.i.stop();
        }
        this.a.setImageResource(R.drawable.red_envelope_open);
    }

    void b() {
        this.a.setImageResource(R.drawable.red_envelope_spin_rotate);
        Drawable drawable = this.a.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            this.i = (AnimationDrawable) drawable;
            this.i.start();
        }
    }

    void c() {
        int i = 8;
        if (QsbkApp.currentUser == null) {
            getActivity().finish();
            return;
        }
        int i2;
        this.a.setOnClickListener(new db(this));
        this.c.setText(this.j.desc);
        this.b.setText(this.j.title);
        this.g.setMaxLines(2);
        String str = "...查看全部";
        StringBuilder stringBuilder = new StringBuilder();
        if (this.j.detail.length() > 34) {
            stringBuilder.append(this.j.detail.substring(0, 34));
            stringBuilder.append(str);
        } else {
            stringBuilder.append(this.j.detail);
            stringBuilder.append(str);
        }
        CharSequence spannableStringBuilder = new SpannableStringBuilder(stringBuilder.toString());
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.yellow_laisee)), spannableStringBuilder.length() - str.length(), spannableStringBuilder.length(), 34);
        this.g.setText(spannableStringBuilder);
        this.g.setOnClickListener(new dc(this));
        this.g.setVisibility(TextUtils.isEmpty(this.j.detail) ? 8 : 0);
        this.e.setText(this.j.content);
        this.f.setText(this.j.lastDesc);
        View view = this.m;
        if (QsbkApp.currentUser.hasPhone()) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        view.setVisibility(i2);
        this.n.setOnClickListener(new dd(this));
        ImageView imageView = this.a;
        if (QsbkApp.currentUser.hasPhone()) {
            i = 0;
        }
        imageView.setVisibility(i);
        if (!TextUtils.isEmpty(this.j.circleTopicId) && !TextUtils.isEmpty(this.j.circleTopicDesc)) {
            this.d.setText(this.j.circleTopicDesc);
            this.d.setBackgroundResource(0);
            this.d.setOnClickListener(new de(this));
        } else if (!TextUtils.isEmpty(this.j.webUrl) && !TextUtils.isEmpty(this.j.webDesc)) {
            this.d.setText(this.j.webDesc);
            this.d.setBackgroundResource(0);
            this.d.setOnClickListener(new df(this));
        }
    }

    private void d() {
        if (!this.l) {
            this.k = new EncryptHttpTask(null, String.format(Constants.LAISEE_FETCH, new Object[]{this.j.id}), new dg(this));
            this.l = true;
            Map hashMap = new HashMap();
            hashMap.put("secret", this.j.secret);
            this.k.setMapParams(hashMap);
            this.k.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }
}
