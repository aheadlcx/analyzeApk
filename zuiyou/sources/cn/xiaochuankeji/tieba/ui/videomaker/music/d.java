package cn.xiaochuankeji.tieba.ui.videomaker.music;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicJson;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicSearchJson;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.j;
import cn.xiaochuankeji.tieba.ui.utils.f;
import cn.xiaochuankeji.tieba.ui.videomaker.music.b.a;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.b;
import java.util.ArrayList;
import rx.k;

public class d extends FrameLayout implements a {
    private Context a;
    private EditText b;
    private View c;
    private View d;
    private UltimateRecyclerView e;
    private f f;
    private b g;
    private FrameLayout h;
    private ArrayList<UgcVideoMusicJson> i = new ArrayList();
    private boolean j;
    private long k;
    private String l;
    private cn.xiaochuankeji.tieba.api.ugcvideo.a m = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
    private k n;

    public d(@NonNull Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.a = context;
        LayoutInflater.from(this.a).inflate(R.layout.view_panel_select_music, this);
        this.c = findViewById(R.id.vCancel);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b();
                cn.htjyb.c.a.a((Activity) this.a.getContext());
            }
        });
        this.d = findViewById(R.id.ivClear);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b.setText(null);
            }
        });
        this.b = (EditText) findViewById(R.id.etInput);
        this.b.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                CharSequence obj = this.a.b.getText().toString();
                this.a.l = obj.trim();
                if (TextUtils.isEmpty(obj)) {
                    this.a.e();
                    this.a.d.setVisibility(4);
                    return;
                }
                this.a.a(true);
                this.a.d.setVisibility(0);
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.e = (UltimateRecyclerView) findViewById(R.id.ultimateRecyclerView);
        this.h = (FrameLayout) findViewById(R.id.flEmptyContainer);
        d();
        setVisibility(8);
    }

    private void d() {
        this.g = new b(getContext(), this.i, this, -1, null);
        this.e.setLayoutManager(new LinearLayoutManager(getContext()));
        this.e.setOnLoadMoreListener(new b(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                this.a.a(false);
            }
        });
        this.e.a(R.layout.common_empty_view, UltimateRecyclerView.a, UltimateRecyclerView.a);
        this.e.setLoadMoreView(new j(getContext()));
        this.e.setAdapter(this.g);
        if (this.i.size() == 0 || !this.j) {
            this.e.h();
        }
        this.f = new f(getContext(), new f.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a(true);
            }
        });
        this.h.addView(this.f, new LayoutParams(-1, -1));
        this.e.a(new OnScrollListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 1 && ((SelectVideoMusicActivity) this.a.getContext()).q()) {
                    cn.htjyb.c.a.a((SelectVideoMusicActivity) this.a.getContext());
                }
            }
        });
    }

    protected void a() {
        setVisibility(0);
        cn.htjyb.c.a.a(this.b, getContext());
    }

    protected void b() {
        setVisibility(8);
        e();
        this.l = null;
        this.b.setText(null);
        ((SelectVideoMusicActivity) getContext()).j();
    }

    private void e() {
        this.k = 0;
        this.j = false;
        this.i.clear();
        this.g.a(-1, false);
        ((SelectVideoMusicActivity) getContext()).k();
        this.f.setVisibility(8);
        if (this.n != null) {
            this.n.unsubscribe();
        }
        this.e.h();
    }

    private void a(boolean z) {
        if (z) {
            e();
        }
        if (!TextUtils.isEmpty(this.l)) {
            this.n = this.m.a(this.l, this.k).a(rx.a.b.a.a()).b(new rx.j<UgcVideoMusicSearchJson>(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcVideoMusicSearchJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    g.a(th.getMessage());
                    if (this.a.i.size() == 0) {
                        this.a.f.a(R.drawable.img_exception_network_error_in_musicselect, "小右加载不出列表啦");
                    }
                }

                public void a(UgcVideoMusicSearchJson ugcVideoMusicSearchJson) {
                    boolean z = true;
                    if (ugcVideoMusicSearchJson.musicList != null && ugcVideoMusicSearchJson.musicList.size() > 0) {
                        this.a.i.addAll(ugcVideoMusicSearchJson.musicList);
                    }
                    d dVar = this.a;
                    if (ugcVideoMusicSearchJson.more != 1) {
                        z = false;
                    }
                    dVar.j = z;
                    this.a.k = ugcVideoMusicSearchJson.offset;
                    if (this.a.j) {
                        this.a.e.f();
                    } else {
                        this.a.e.h();
                    }
                    this.a.g.notifyDataSetChanged();
                    if (this.a.i.size() == 0) {
                        this.a.f.b(R.drawable.img_search_music_empty, "没有搜到对应歌曲，小右正在添加哦");
                    } else {
                        this.a.f.setVisibility(8);
                    }
                }
            });
        }
    }

    public void a(long j, boolean z) {
        this.g.a(j, z);
    }

    public boolean c() {
        return getVisibility() == 0;
    }

    public void a(String str, long j) {
        ((SelectVideoMusicActivity) getContext()).a(str, j);
    }

    public void a(UgcVideoMusicJson ugcVideoMusicJson) {
        ((SelectVideoMusicActivity) getContext()).a(ugcVideoMusicJson);
    }
}
