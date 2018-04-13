package cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.danmaku.DanmakuItem;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuJson;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.e;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.f.a;
import cn.xiaochuankeji.tieba.ui.videomaker.d;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.c;
import cn.xiaochuankeji.tieba.ui.widget.g;
import com.alibaba.fastjson.JSON;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends f {
    private EditText a;
    private View b;
    private View c;
    private TextView d;
    private DanmakuStickyFrameLayout e;
    private View f;
    private View g;
    private UltimateRecyclerView h;
    private TextView i;
    private e j;
    private FrameLayout k;
    private d l;
    private cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.d m;
    private cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b.b n;
    private a o;
    private DanmakuItem p;
    private boolean q;
    private a r;
    private int s;
    private long t = 0;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.d) {
            this.m = (cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.d) activity;
            return;
        }
        try {
            throw new Exception("class cast to IDanmakuContract error!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ugcvideo_danmaku, null);
        this.k = (FrameLayout) inflate.findViewById(R.id.rootView);
        this.a = (EditText) inflate.findViewById(R.id.etInput);
        this.b = inflate.findViewById(R.id.vSend);
        this.f = inflate.findViewById(R.id.vInputContainer);
        this.e = (DanmakuStickyFrameLayout) inflate.findViewById(R.id.vContainerWrap);
        this.c = inflate.findViewById(R.id.ivClose);
        this.h = (UltimateRecyclerView) inflate.findViewById(R.id.ulRecyclerView);
        this.e.setRecyclerView(this.h.g);
        this.d = (TextView) inflate.findViewById(R.id.tvEmpty);
        this.i = (TextView) inflate.findViewById(R.id.tvTitle);
        this.g = inflate.findViewById(R.id.vSoftCover);
        this.j = new e(getContext(), new e(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(UgcVideoDanmakuJson ugcVideoDanmakuJson) {
                long a = this.a.n.a();
                String nickName = ugcVideoDanmakuJson.member.getNickName();
                if (a != ugcVideoDanmakuJson.id) {
                    this.a.a.setText(null);
                    this.a.n.a(ugcVideoDanmakuJson.id);
                }
                this.a.a.setHint("回复 " + nickName + ":");
                cn.htjyb.c.a.a(this.a.a, this.a.getContext());
            }

            public void b(UgcVideoDanmakuJson ugcVideoDanmakuJson) {
                if (ugcVideoDanmakuJson.member.getId() != cn.xiaochuankeji.tieba.background.a.g().c()) {
                    this.a.a(ugcVideoDanmakuJson);
                }
            }

            public void a(long j) {
                this.a.t = j;
                this.a.n.a(j);
            }
        });
        return inflate;
    }

    private void a(final UgcVideoDanmakuJson ugcVideoDanmakuJson) {
        SDBottomSheet sDBottomSheet = new SDBottomSheet(getActivity(), new cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b(this) {
            final /* synthetic */ b b;

            public void a(int i) {
                if (i == 12) {
                    this.b.r.a(ugcVideoDanmakuJson.id);
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c(R.drawable.icon_option_report, "举报", 12));
        sDBottomSheet.a(arrayList, null);
        sDBottomSheet.b();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        c();
        this.n = new cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b.b(new cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.b.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a() {
                g.a(this.a.getActivity());
            }

            public void a(boolean z, UgcVideoDanmakuJson ugcVideoDanmakuJson) {
                g.c(this.a.getActivity());
                if (z) {
                    this.a.a.setText(null);
                    this.a.a.setHint("填装弹幕内容...");
                    cn.htjyb.c.a.a(this.a.getContext(), this.a.a);
                    cn.xiaochuankeji.tieba.background.utils.g.a("弹幕发送成功");
                    if (this.a.j.c()) {
                        this.a.j.a(ugcVideoDanmakuJson);
                        this.a.n.a(this.a.t);
                        return;
                    }
                    try {
                        this.a.p = DanmakuItem.fromJson(new JSONObject(JSON.toJSONString(ugcVideoDanmakuJson)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    this.a.o.a(ugcVideoDanmakuJson);
                    this.a.s = this.a.s + 1;
                    this.a.a(false, this.a.s);
                    this.a.n.a(0);
                }
            }
        });
        this.o = new cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b.c(new cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.f.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    g.a(this.a.getActivity());
                } else {
                    g.c(this.a.getActivity());
                }
            }

            public void a(boolean z, boolean z2) {
                if (!this.a.isAdded()) {
                    return;
                }
                if (z) {
                    this.a.d.setVisibility(0);
                    Drawable drawable;
                    if (z2) {
                        this.a.d.setText("呀！网络开小差了");
                        drawable = this.a.getActivity().getResources().getDrawable(R.drawable.img_danmaku_network_error);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        this.a.d.setCompoundDrawables(null, drawable, null, null);
                        return;
                    }
                    this.a.d.setText("暂无弹幕，发个弹幕吧");
                    drawable = this.a.getActivity().getResources().getDrawable(R.drawable.img_empty_danmaku);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    this.a.d.setCompoundDrawables(null, drawable, null, null);
                    return;
                }
                this.a.d.setVisibility(8);
            }

            public void a(List<UgcVideoDanmakuJson> list, boolean z, boolean z2) {
                if (this.a.h.getVisibility() != 0) {
                    this.a.h.setVisibility(0);
                }
                this.a.r.a((List) list, z2);
                if (!z) {
                    this.a.h.h();
                } else if (!this.a.h.g()) {
                    this.a.h.f();
                }
            }

            public void a(long j, int i) {
                this.a.a(j, i);
            }

            public void a() {
                this.a.h.h();
                this.a.r.b(false);
                this.a.r.f();
                this.a.r.a(new ArrayList(), false);
            }

            public void a(int i) {
                this.a.s = i;
                this.a.a(false, i);
            }

            public void b() {
                this.a.h.a(0);
            }
        });
        e();
    }

    private void c() {
        this.l = new d();
        this.l.a(new d.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(boolean z, int i, int i2) {
                this.a.q = z;
                if (!(this.a.f == null || this.a.f.getLayoutParams() == null)) {
                    LayoutParams layoutParams = (LayoutParams) this.a.f.getLayoutParams();
                    layoutParams.bottomMargin = i;
                    this.a.f.setLayoutParams(layoutParams);
                }
                if (!z && this.a.j.c() && this.a.a.getText().toString().trim().equals("")) {
                    this.a.n.a(this.a.t);
                    this.a.a.setHint("填装弹幕内容...");
                }
                if (z) {
                    this.a.g.setVisibility(0);
                } else {
                    this.a.g.setVisibility(4);
                }
            }
        });
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.j.c()) {
                    this.a.d();
                }
                this.a.g();
            }
        });
        this.e.setClickable(true);
        this.e.a(new DanmakuStickyFrameLayout.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a() {
                if (this.a.j.c()) {
                    this.a.d();
                }
                this.a.g();
            }
        });
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                cn.htjyb.c.a.a(this.a.getContext(), this.a.a);
            }
        });
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.n.a(this.a.a.getText().toString().trim());
            }
        });
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.j.c()) {
                    this.a.b(false);
                }
            }
        });
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.j.c()) {
                    this.a.d();
                    this.a.o.c();
                }
            }
        });
        this.a.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0 || cn.xiaochuankeji.tieba.ui.auth.a.a(this.a.getActivity(), "media_browser", 2)) {
                    return false;
                }
                return true;
            }
        });
    }

    private void d() {
        this.j.e();
        this.e.setRecyclerView(this.h.g);
        this.h.setVisibility(0);
        this.c.setVisibility(0);
        a(false, this.s);
        this.n.a(0);
        this.a.setHint("填装弹幕内容...");
        this.a.setText(null);
        this.t = 0;
    }

    private void e() {
        this.r = new a(getContext(), 1, new cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.c(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(UgcVideoDanmakuJson ugcVideoDanmakuJson) {
                this.a.a(ugcVideoDanmakuJson.id, 0);
            }

            public void b(UgcVideoDanmakuJson ugcVideoDanmakuJson) {
                if (ugcVideoDanmakuJson.member.getId() != cn.xiaochuankeji.tieba.background.a.g().c()) {
                    this.a.a(ugcVideoDanmakuJson);
                }
            }

            public void a() {
                this.a.o.b();
            }
        });
        this.h.setLayoutManager(new LinearLayoutManager(getContext()));
        this.h.setOnLoadMoreListener(new com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                this.a.o.a();
            }
        });
        this.h.a(R.layout.common_empty_view, UltimateRecyclerView.a, UltimateRecyclerView.a);
        this.h.setLoadMoreView(new c(getContext()));
        this.h.setAdapter(this.r);
        this.h.h();
    }

    private void a(long j, int i) {
        this.j.a(j, i);
        this.e.setRecyclerView(this.j.d());
        this.t = j;
        this.n.a(j);
        a(true, 0);
        this.c.setVisibility(8);
        this.h.setVisibility(8);
        this.a.setText("");
    }

    private void a(boolean z, int i) {
        if (z) {
            this.i.setText("全部弹幕列表");
            Drawable drawable = getResources().getDrawable(R.drawable.iv_danmaku_back);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.i.setCompoundDrawables(drawable, null, null, null);
            return;
        }
        if (i == 0) {
            this.i.setText("弹幕列表");
        } else {
            this.i.setText("弹幕列表（" + cn.xiaochuankeji.tieba.ui.utils.d.b(i) + "）");
        }
        this.i.setCompoundDrawables(null, null, null, null);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        f();
    }

    private void f() {
        this.e.post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                int a = cn.xiaochuankeji.tieba.ui.utils.e.a(58.0f);
                int c = (int) (((double) cn.xiaochuankeji.tieba.ui.utils.e.c()) * 0.72d);
                LayoutParams layoutParams = (LayoutParams) this.a.e.getLayoutParams();
                layoutParams.height = c - a;
                this.a.e.setLayoutParams(layoutParams);
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, (c - a) - cn.xiaochuankeji.tieba.ui.utils.e.a(40.0f));
                layoutParams2.gravity = 80;
                this.a.e.addView(this.a.j.f_(), layoutParams2);
                this.a.f.bringToFront();
            }
        });
    }

    public void onPause() {
        super.onPause();
        b(true);
    }

    public boolean b() {
        if (this.j.c()) {
            d();
            this.o.c();
            return true;
        } else if (this.k.getVisibility() != 0) {
            return false;
        } else {
            b(false);
            return true;
        }
    }

    public void a(UgcVideoInfoBean ugcVideoInfoBean, long j, long j2, int i, String str) {
        this.k.setVisibility(0);
        this.n.a(ugcVideoInfoBean, j, str);
        this.o.a(ugcVideoInfoBean, j2, i);
        this.l.a(getActivity());
        this.k.post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.e.getScrollY() != 0) {
                    this.a.e.scrollTo(0, 0);
                }
            }
        });
    }

    public void a(UgcVideoInfoBean ugcVideoInfoBean, long j, String str) {
        a(ugcVideoInfoBean, j, 0, 0, str);
    }

    private void b(boolean z) {
        if (getActivity() != null) {
            if (((h) getActivity()).q() || this.q) {
                cn.htjyb.c.a.a(getActivity(), this.a);
                if (this.j.c() && this.a.getText().toString().trim().equals("")) {
                    this.n.a(this.t);
                    this.a.setHint("填装弹幕内容...");
                }
            } else if (!z) {
                if (this.j.c()) {
                    d();
                    this.o.c();
                }
                g();
            }
        }
    }

    private void g() {
        if (this.p != null) {
            this.m.a(this.p);
        } else {
            this.m.k();
        }
        this.p = null;
        this.o.d();
        this.k.setVisibility(8);
        this.d.setVisibility(8);
        this.l.a();
    }
}
