package cn.xiaochuankeji.tieba.ui.voice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class VoiceSelectTopicActivity_ViewBinding implements Unbinder {
    private VoiceSelectTopicActivity b;
    private View c;

    @UiThread
    public VoiceSelectTopicActivity_ViewBinding(final VoiceSelectTopicActivity voiceSelectTopicActivity, View view) {
        this.b = voiceSelectTopicActivity;
        View a = b.a(view, R.id.back, "field 'back' and method 'onClick'");
        voiceSelectTopicActivity.back = a;
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ VoiceSelectTopicActivity_ViewBinding c;

            public void a(View view) {
                voiceSelectTopicActivity.onClick(view);
            }
        });
        voiceSelectTopicActivity.recycler_view = (RecyclerView) b.b(view, R.id.recycler_view, "field 'recycler_view'", RecyclerView.class);
        voiceSelectTopicActivity.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.root, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void a() {
        VoiceSelectTopicActivity voiceSelectTopicActivity = this.b;
        if (voiceSelectTopicActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        voiceSelectTopicActivity.back = null;
        voiceSelectTopicActivity.recycler_view = null;
        voiceSelectTopicActivity.refreshLayout = null;
        this.c.setOnClickListener(null);
        this.c = null;
    }
}
