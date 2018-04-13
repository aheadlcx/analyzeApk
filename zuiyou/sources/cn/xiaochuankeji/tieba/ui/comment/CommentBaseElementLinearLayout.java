package cn.xiaochuankeji.tieba.ui.comment;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.CommentSound;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.ui.comment.soundnewvisual.SoundNewVisualView;
import cn.xiaochuankeji.tieba.ui.post.postitem.MultipleImgFrameLayoutContainer;
import cn.xiaochuankeji.tieba.ui.voice.b.d;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.c;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import java.util.ArrayList;

public class CommentBaseElementLinearLayout extends LinearLayout implements OnLongClickListener, cn.xiaochuankeji.tieba.background.j.c.a, c {
    private Context a;
    private cn.xiaochuankeji.tieba.background.j.b b;
    private cn.xiaochuankeji.tieba.background.j.a c;
    private CommentSound d;
    private b e;
    private a f;
    private boolean g;
    private SoundNewVisualView h;
    private ExpandableTextView i;
    private MultipleImgFrameLayoutContainer j;
    private boolean k;

    public interface a {
        void a();

        void a(View view, int i);

        void b();

        void d();
    }

    public interface b {
        void b(View view);
    }

    public CommentBaseElementLinearLayout(Context context) {
        this(context, null);
    }

    public CommentBaseElementLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = cn.xiaochuankeji.tieba.background.j.b.a();
        this.a = context;
        this.c = cn.xiaochuankeji.tieba.background.j.a.a();
        c();
    }

    private void c() {
        LayoutInflater.from(this.a).inflate(R.layout.view_base_comment_layout, this);
        d();
        getViews();
        e();
    }

    private void d() {
        setOrientation(1);
    }

    private void getViews() {
        this.h = (SoundNewVisualView) findViewById(R.id.newSoundView);
        this.i = (ExpandableTextView) findViewById(R.id.expandTextView);
        this.j = (MultipleImgFrameLayoutContainer) findViewById(R.id.flPicContainer);
        this.j.a();
    }

    private void e() {
        this.i.setOnSingleClickListener(this);
        this.i.setOnLongClickListener(this);
    }

    public void setEditMode(boolean z) {
        this.k = z;
    }

    public void a(String str, String str2, ArrayList<ServerImage> arrayList, CommentSound commentSound, f fVar) {
        boolean z;
        boolean z2 = true;
        this.g = false;
        boolean z3 = (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) ? false : true;
        if (arrayList == null || arrayList.size() <= 0) {
            z = false;
        } else {
            z = true;
        }
        if (z3) {
            this.i.a(str, str2, fVar);
            this.i.setVisibility(0);
            ExpandableTextView expandableTextView = this.i;
            if (this.k) {
                z3 = false;
            } else {
                z3 = true;
            }
            expandableTextView.b = z3;
        } else {
            this.i.setVisibility(8);
        }
        if (z) {
            this.j.setVisibility(0);
            this.j.setData(arrayList);
            this.j.setOnContainerClickListener(new cn.xiaochuankeji.tieba.ui.post.postitem.MultipleImgFrameLayoutContainer.a(this) {
                final /* synthetic */ CommentBaseElementLinearLayout a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    this.a.f.a(this.a, i);
                }
            });
        } else {
            this.j.setVisibility(8);
        }
        if (commentSound != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            this.h.setVisibility(0);
            this.h.setOnPlayOrPauseListener(this);
            this.h.setSoundTime(commentSound.getDur());
            this.d = commentSound;
            String url = commentSound.getUrl();
            if (TextUtils.isEmpty(url) || !this.b.b(url)) {
                z2 = false;
            }
            if (z2) {
                this.h.a(this.b.c(), (long) this.b.e(), (long) this.b.f());
                this.c.a(this.h);
                return;
            }
            this.h.a();
            return;
        }
        this.h.setVisibility(8);
    }

    public void setCommonLongClickAction(b bVar) {
        this.e = bVar;
    }

    public void setCommonClickAction(a aVar) {
        this.f = aVar;
    }

    public void a() {
        if (!this.k) {
            this.h.c();
            cn.xiaochuankeji.tieba.background.j.b a = cn.xiaochuankeji.tieba.background.j.b.a();
            if (!a.b(this.d.getUrl())) {
                a.i();
                if (d.a().h()) {
                    d.a().f();
                }
                this.c.a(this.h);
                cn.xiaochuankeji.tieba.background.j.b.a().a(this.d.getUrl());
            } else if (!a.d()) {
                if (a.c()) {
                    a.g();
                } else {
                    if (d.a().h()) {
                        d.a().f();
                    }
                    a.h();
                }
            } else {
                return;
            }
            if (!this.g) {
                this.g = true;
                if (this.f != null) {
                    this.f.d();
                }
            }
        } else if (this.f != null) {
            this.f.d();
        }
    }

    public void a(boolean z) {
        if (z) {
            if (this.f != null) {
                this.f.b();
            }
        } else if (this.f != null) {
            this.f.a();
        }
    }

    public boolean onLongClick(View view) {
        if (this.e != null) {
            this.e.b(this);
        }
        return true;
    }

    public void b() {
        CharSequence b = this.b.b();
        if (!(TextUtils.isEmpty(b) || this.d == null || !this.d.getUrl().equals(b))) {
            this.c.b();
        }
        this.d = null;
        this.g = false;
    }

    public void setTextColor(@ColorInt int i) {
        this.i.setTextColor(i);
    }

    public void setMaxLines(int i) {
        if (this.i != null) {
            this.i.setTextMaxLine(i);
        }
    }
}
