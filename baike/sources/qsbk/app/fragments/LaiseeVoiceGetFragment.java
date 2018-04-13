package qsbk.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.HttpFileTask;
import qsbk.app.http.HttpTask;
import qsbk.app.im.VoiceHelper;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.LaiseeVoice;
import qsbk.app.widget.RecordButton;

public class LaiseeVoiceGetFragment extends BaseFragment {
    int[] a = new int[]{R.drawable.ic_voice_volume_0, R.drawable.ic_voice_volume_1, R.drawable.ic_voice_volume_2, R.drawable.ic_voice_volume_3, R.drawable.ic_voice_volume_4, R.drawable.ic_voice_volume_5};
    private RecordButton b;
    private View c;
    private TextView d;
    private View e;
    private ImageView f;
    private ImageView g;
    private ProgressBar h;
    private TextView i;
    private HttpTask j;
    private View k;
    private Button l;
    private TextView m;
    private VoiceHelper n;
    private LaiseeVoice o;
    private String p;
    private boolean q;

    public static Fragment newInstance(LaiseeVoice laiseeVoice, String str) {
        Fragment laiseeVoiceGetFragment = new LaiseeVoiceGetFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("laisee", laiseeVoice);
        bundle.putSerializable("conversationId", str);
        laiseeVoiceGetFragment.setArguments(bundle);
        return laiseeVoiceGetFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return layoutInflater.inflate(R.layout.fragment_laisee_voice_get, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view, bundle);
    }

    public void onResume() {
        super.onResume();
        a();
    }

    protected void a(View view, Bundle bundle) {
        if (getArguments() != null) {
            this.o = (LaiseeVoice) getArguments().getSerializable("laisee");
            this.p = getArguments().getString("conversationId");
        }
        if (this.o == null) {
            getActivity().finish();
            return;
        }
        this.c = view.findViewById(R.id.open_container);
        this.f = (ImageView) view.findViewById(R.id.avatar);
        this.g = (ImageView) view.findViewById(R.id.mic_status);
        this.m = (TextView) view.findViewById(R.id.name);
        this.k = view.findViewById(R.id.bind_container);
        this.l = (Button) view.findViewById(R.id.bind_phone);
        this.d = (TextView) view.findViewById(R.id.content);
        this.b = (RecordButton) view.findViewById(R.id.red_envelope_open);
        this.e = view.findViewById(R.id.close);
        this.e.setOnClickListener(new dz(this));
        this.h = (ProgressBar) view.findViewById(R.id.record_progress);
        this.h.setVisibility(8);
        this.i = (TextView) view.findViewById(R.id.record_tip);
        a();
    }

    void a() {
        int i = 0;
        this.n = new VoiceHelper(getActivity(), new ea(this));
        this.b.setOnClickListener(new eb(this));
        this.b.setOnRecordListener(new ec(this));
        this.d.setText(this.o.content);
        this.k.setVisibility(QsbkApp.currentUser.hasPhone() ? 8 : 0);
        this.l.setOnClickListener(new ed(this));
        View view = this.c;
        if (!QsbkApp.currentUser.hasPhone()) {
            i = 8;
        }
        view.setVisibility(i);
        a(this.o.sendUser.getUid() + "", this.o.sendUser.getIcon(), this.f);
        this.m.setText(this.o.sendUser.getLogin());
    }

    protected void a(String str, String str2, ImageView imageView) {
        FrescoImageloader.displayAvatar(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str2, str));
    }

    private void a(File file, long j) {
        if (!this.q) {
            this.i.setText("拼命抢红包中...");
            this.h.setVisibility(0);
            this.b.setVisibility(8);
            this.j = new HttpFileTask(String.format(Constants.LAISEE_VOICE_FETCH, new Object[]{this.o.id}), new ee(this, file, j));
            this.q = true;
            Map hashMap = new HashMap();
            hashMap.put("secret", this.o.secret);
            hashMap.put("voice_duration", Long.valueOf(j));
            hashMap.put("voice_data", file);
            this.j.setMapParams(hashMap);
            this.j.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.j != null) {
            this.j.cancel(true);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        a();
    }
}
