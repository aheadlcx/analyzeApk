package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.customtv.EllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class SubjectItem_ViewBinding implements Unbinder {
    private SubjectItem b;
    private View c;
    private View d;

    @UiThread
    public SubjectItem_ViewBinding(final SubjectItem subjectItem, View view) {
        this.b = subjectItem;
        subjectItem.view_divide = b.a(view, R.id.view_divide, "field 'view_divide'");
        subjectItem.title = (EllipsisTextView) b.b(view, R.id.title, "field 'title'", EllipsisTextView.class);
        View a = b.a(view, R.id.close, "field 'close' and method 'close'");
        subjectItem.close = (AppCompatImageView) b.c(a, R.id.close, "field 'close'", AppCompatImageView.class);
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ SubjectItem_ViewBinding c;

            public void a(View view) {
                subjectItem.close();
            }
        });
        subjectItem.subject_cover = (AppCompatImageView) b.b(view, R.id.subject_cover, "field 'subject_cover'", AppCompatImageView.class);
        subjectItem.cover = (WebImageView) b.b(view, R.id.cover, "field 'cover'", WebImageView.class);
        View a2 = b.a(view, R.id.open_subject, "method 'openSubject'");
        this.d = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ SubjectItem_ViewBinding c;

            public void a(View view) {
                subjectItem.openSubject();
            }
        });
    }

    @CallSuper
    public void a() {
        SubjectItem subjectItem = this.b;
        if (subjectItem == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        subjectItem.view_divide = null;
        subjectItem.title = null;
        subjectItem.close = null;
        subjectItem.subject_cover = null;
        subjectItem.cover = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
    }
}
