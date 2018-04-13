package cn.xiaochuankeji.tieba.ui.post.b;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import c.a.i.u;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.post.postdetail.InnerCommentDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import com.izuiyou.a.a.b;
import java.util.ArrayList;

public class d extends j implements OnClickListener, u {
    private FrameLayout a;
    private View b;
    private LinearLayout c;
    private LinearLayout d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private EditText h;
    private Button i;
    private int j = 1;
    private boolean k = false;
    private a l;
    private a m;
    private b n;
    private long o;
    private long p;

    public interface a {
        void a(long j, String str, String str2, cn.xiaochuankeji.tieba.background.post.a aVar, ArrayList<LocalMedia> arrayList);

        void a(ArrayList<LocalMedia> arrayList);
    }

    public d(Context context, a aVar) {
        super(context);
        this.l = aVar;
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.new_view_input_with_image, null);
    }

    protected void a(View view) {
        this.a = (FrameLayout) view.findViewById(R.id.flInputCommentRootView);
        this.c = (LinearLayout) view.findViewById(R.id.llViewContent);
        this.c.setTag("input_comment_view_container");
        this.d = (LinearLayout) view.findViewById(R.id.llInputEditTextContainer);
        this.e = (ImageView) view.findViewById(R.id.ivChangeTextOrVoice);
        this.f = (ImageView) view.findViewById(R.id.add_image);
        this.h = (EditText) view.findViewById(R.id.etInput);
        this.i = (Button) view.findViewById(R.id.send);
        this.b = view.findViewById(R.id.voice_container);
        this.g = (ImageView) view.findViewById(R.id.ivSoundFlag);
        j();
        l();
        m();
        d();
    }

    private void j() {
        this.m = new a(e_(), new a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a() {
                if (this.a.j == 1 && !this.a.n()) {
                    this.a.b(false);
                }
            }

            public void b() {
            }
        });
        this.c.addView(this.m.f_(), 0, new LayoutParams(-1, -2));
    }

    private void k() {
        this.o = System.currentTimeMillis();
        b.e("最后更改时间:" + this.o);
    }

    private void l() {
        this.n = new b(e_(), new cn.xiaochuankeji.tieba.ui.post.b.b.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(String str) {
                Object trim = this.a.h.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    this.a.h.setText(str);
                } else {
                    str = trim + str;
                }
                this.a.h.setText(str);
                this.a.h.setSelection(str != null ? str.length() : 0);
            }

            public void a() {
                this.a.h.setText(null);
            }

            public void b() {
                this.a.k();
            }

            public void c() {
                this.a.k = false;
            }
        });
        int childCount = this.c.getChildCount() - 2;
        this.c.addView(this.n.f_(), new LayoutParams(-1, -2));
    }

    private void m() {
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.n()) {
                    if (this.a.j == 2 || this.a.j == 3) {
                        this.a.o();
                    }
                }
            }
        });
        this.a.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    Rect rect = new Rect();
                    this.a.c.getGlobalVisibleRect(rect);
                    if (rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                        return true;
                    }
                    if (this.a.j == 2 || this.a.j == 3) {
                        this.a.o();
                        this.a.b(false);
                        return true;
                    } else if (this.a.n()) {
                        cn.htjyb.c.a.a(this.a.e_(), this.a.h);
                        this.a.b(false);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void b(boolean z) {
    }

    private boolean n() {
        Context e_ = e_();
        if (e_ instanceof PostDetailActivity) {
            return ((PostDetailActivity) e_).w();
        }
        return e_ instanceof InnerCommentDetailActivity ? ((InnerCommentDetailActivity) e_).k() : false;
    }

    private void o() {
        this.g.setVisibility(this.n.d() ? 0 : 8);
        this.e.setImageResource(R.drawable.ic_record_voice);
        this.n.e();
        this.j = 1;
    }

    public void onClick(View view) {
        boolean z = true;
        PermissionItem permissionItem;
        switch (view.getId()) {
            case R.id.send:
                boolean z2;
                String trim = this.h.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    trim = null;
                    z2 = false;
                } else {
                    z2 = true;
                }
                k();
                ArrayList c = this.m.c();
                boolean z3;
                if (c.size() > 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                cn.xiaochuankeji.tieba.background.post.a g = this.n.g();
                if (g == null) {
                    z = false;
                }
                if (z2 || r3 || r2) {
                    this.l.a(this.p, trim, String.valueOf(this.o), g, c);
                    return;
                } else {
                    g.a("还没有输入评论");
                    return;
                }
            case R.id.ivChangeTextOrVoice:
                if (this.j == 1) {
                    if (this.k) {
                        q();
                    } else {
                        permissionItem = new PermissionItem("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO");
                        permissionItem.deniedMessage = "开启以下权限才能正常发布语音内容";
                        permissionItem.deniedButton = "仍然拒绝";
                        permissionItem.needGotoSetting = true;
                        permissionItem.rationalButton = "确认";
                        permissionItem.rationalMessage = "打开存储和录音权限后才可以正常录音";
                        permissionItem.runIgnorePermission = false;
                        permissionItem.settingText = "前往设置";
                        permissionItem.runIgnorePermission = false;
                        cn.xiaochuankeji.aop.permission.a.a(view.getContext()).a(permissionItem, new e(this) {
                            final /* synthetic */ d a;

                            {
                                this.a = r1;
                            }

                            public void permissionGranted() {
                                this.a.p();
                            }

                            public void permissionDenied() {
                                g.a("开启以下权限才能正常发布语音内容");
                            }
                        });
                    }
                    r();
                    return;
                }
                o();
                b(false);
                return;
            case R.id.add_image:
                permissionItem = new PermissionItem("android.permission.READ_EXTERNAL_STORAGE");
                permissionItem.deniedMessage = "拒绝该权限后无法正常选择大图和视频";
                permissionItem.deniedButton = "仍然拒绝";
                permissionItem.needGotoSetting = true;
                permissionItem.rationalButton = "确认";
                permissionItem.rationalMessage = "打开存储权限后才可以正常选择大图和视频";
                permissionItem.runIgnorePermission = false;
                permissionItem.settingText = "前往设置";
                permissionItem.runIgnorePermission = false;
                cn.xiaochuankeji.aop.permission.a.a(view.getContext()).a(permissionItem, new e(this) {
                    final /* synthetic */ d a;

                    {
                        this.a = r1;
                    }

                    public void permissionGranted() {
                        this.a.l.a(this.a.m.c());
                    }

                    public void permissionDenied() {
                        g.a("拒绝该权限后无法正常选择大图和视频");
                    }
                });
                return;
            default:
                return;
        }
    }

    private void p() {
        this.j = 2;
        this.g.setVisibility(8);
        this.n.f_().postDelayed(new Runnable(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b(true);
                this.a.e.setImageResource(R.drawable.ic_keyboard);
                this.a.n.f();
            }
        }, 200);
    }

    private void q() {
        this.j = 3;
        this.g.setVisibility(8);
        this.n.f_().postDelayed(new Runnable(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b(true);
                this.a.e.setImageResource(R.drawable.ic_keyboard);
                this.a.n.f();
                this.a.k = true;
            }
        }, 200);
    }

    private void r() {
        if (n()) {
            cn.htjyb.c.a.a(e_(), this.h);
        }
    }

    public void a(boolean z) {
        if (z) {
            b(true);
        } else if (this.j == 1) {
            b(false);
        }
    }

    public boolean c() {
        if (this.j == 2 || this.j == 3) {
            o();
            b(false);
            return true;
        } else if (this.j != 1 || !n()) {
            return false;
        } else {
            b(false);
            return false;
        }
    }

    public void e() {
        this.e.setClickable(false);
        this.f.setClickable(false);
        this.i.setClickable(false);
        this.h.setEnabled(false);
        this.h.setHint("已禁止评论...");
    }

    public void f() {
        this.e.setClickable(true);
        this.f.setClickable(true);
        this.i.setClickable(true);
        this.h.setEnabled(true);
        this.h.setHint("评论...");
    }

    public void g() {
        if (this.m != null) {
            this.m.d();
        }
    }

    public void h() {
        cn.htjyb.c.a.a((Activity) e_());
        s();
    }

    private void s() {
        this.h.setText("");
        this.p = 0;
        this.h.setHint("发表评论…");
        this.m.a(new ArrayList());
        this.n.c();
        this.n.e();
        cn.xiaochuankeji.tieba.background.j.d.a().b();
        b(false);
        this.g.setVisibility(8);
        this.e.setImageResource(R.drawable.ic_record_voice);
    }

    public void a(long j, String str) {
        s();
        this.p = j;
        this.h.setHint(str);
        cn.htjyb.c.a.a(this.h, e_());
    }

    public void a(ArrayList<LocalMedia> arrayList) {
        this.m.a((ArrayList) arrayList);
        if (arrayList.size() > 0) {
            b(true);
        }
    }

    public void d() {
        if (this.d != null) {
            this.d.setBackgroundColor(c.a.d.a.a.a().a((int) R.color.CB));
        }
    }

    public void i() {
        if (this.b != null) {
            this.b.setVisibility(8);
        }
    }
}
