package cn.xiaochuankeji.tieba.ui.tale;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.dreamtobe.kpswitch.widget.KPSwitchPanelFrameLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.widget.rich.RichTextEditor;

public class TaleCreateActivity_ViewBinding implements Unbinder {
    private TaleCreateActivity b;
    private View c;
    private View d;
    private View e;
    private View f;

    @UiThread
    public TaleCreateActivity_ViewBinding(final TaleCreateActivity taleCreateActivity, View view) {
        this.b = taleCreateActivity;
        taleCreateActivity.theme_counter = (AppCompatTextView) b.b(view, R.id.theme_counter, "field 'theme_counter'", AppCompatTextView.class);
        taleCreateActivity.theme_editor = (EditText) b.b(view, R.id.theme, "field 'theme_editor'", EditText.class);
        taleCreateActivity.article_editor = (RichTextEditor) b.b(view, R.id.editor, "field 'article_editor'", RichTextEditor.class);
        View a = b.a(view, R.id.album, "field 'album' and method 'album'");
        taleCreateActivity.album = a;
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ TaleCreateActivity_ViewBinding c;

            public void a(View view) {
                taleCreateActivity.album();
            }
        });
        a = b.a(view, R.id.hide_keyboard, "field 'hide_keyboard' and method 'hideKeyboard'");
        taleCreateActivity.hide_keyboard = a;
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ TaleCreateActivity_ViewBinding c;

            public void a(View view) {
                taleCreateActivity.hideKeyboard();
            }
        });
        taleCreateActivity.panel_root = (KPSwitchPanelFrameLayout) b.b(view, R.id.panel_root, "field 'panel_root'", KPSwitchPanelFrameLayout.class);
        a = b.a(view, R.id.back, "method 'onClick'");
        this.e = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ TaleCreateActivity_ViewBinding c;

            public void a(View view) {
                taleCreateActivity.onClick(view);
            }
        });
        a = b.a(view, R.id.tv_publish, "method 'send'");
        this.f = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ TaleCreateActivity_ViewBinding c;

            public void a(View view) {
                taleCreateActivity.send();
            }
        });
    }

    @CallSuper
    public void a() {
        TaleCreateActivity taleCreateActivity = this.b;
        if (taleCreateActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleCreateActivity.theme_counter = null;
        taleCreateActivity.theme_editor = null;
        taleCreateActivity.article_editor = null;
        taleCreateActivity.album = null;
        taleCreateActivity.hide_keyboard = null;
        taleCreateActivity.panel_root = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
        this.f.setOnClickListener(null);
        this.f = null;
    }
}
