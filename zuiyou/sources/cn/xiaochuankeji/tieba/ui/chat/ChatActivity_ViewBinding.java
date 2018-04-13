package cn.xiaochuankeji.tieba.ui.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.dreamtobe.kpswitch.widget.KPSwitchPanelLinearLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.widget.record.ChatRecordLayout;
import cn.xiaochuankeji.tieba.widget.ripple.RippleBackground;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class ChatActivity_ViewBinding implements Unbinder {
    private ChatActivity b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;

    @UiThread
    public ChatActivity_ViewBinding(final ChatActivity chatActivity, View view) {
        this.b = chatActivity;
        View a = b.a(view, R.id.more, "field 'more' and method 'navClickEvent'");
        chatActivity.more = a;
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ ChatActivity_ViewBinding c;

            public void a(View view) {
                chatActivity.navClickEvent(view);
            }
        });
        chatActivity.start_voice = b.a(view, R.id.start_voice, "field 'start_voice'");
        chatActivity.chatRecordLayout = (ChatRecordLayout) b.b(view, R.id.record_status_layout, "field 'chatRecordLayout'", ChatRecordLayout.class);
        chatActivity.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.refresh, "field 'refreshLayout'", SmartRefreshLayout.class);
        chatActivity.recycler = (RecyclerView) b.b(view, R.id.recycler, "field 'recycler'", RecyclerView.class);
        chatActivity.input = (EditText) b.b(view, R.id.input, "field 'input'", EditText.class);
        chatActivity.voice_recorder = (AppCompatImageView) b.b(view, R.id.voice_recorder, "field 'voice_recorder'", AppCompatImageView.class);
        chatActivity.title = (AppCompatTextView) b.b(view, R.id.title, "field 'title'", AppCompatTextView.class);
        chatActivity.title_extra = (AppCompatTextView) b.b(view, R.id.title_extra, "field 'title_extra'", AppCompatTextView.class);
        chatActivity.panelLayout = (KPSwitchPanelLinearLayout) b.b(view, R.id.panel_root, "field 'panelLayout'", KPSwitchPanelLinearLayout.class);
        chatActivity.voiceNotifyMsg = (AppCompatTextView) b.b(view, R.id.voice_notify_msg, "field 'voiceNotifyMsg'", AppCompatTextView.class);
        chatActivity.rippleBackground = (RippleBackground) b.b(view, R.id.ripple_background, "field 'rippleBackground'", RippleBackground.class);
        chatActivity.voiceTouchNotify = (TextView) b.b(view, R.id.voice_touch_notify, "field 'voiceTouchNotify'", TextView.class);
        a = b.a(view, R.id.back, "method 'navClickEvent'");
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ ChatActivity_ViewBinding c;

            public void a(View view) {
                chatActivity.navClickEvent(view);
            }
        });
        a = b.a(view, R.id.title_container, "method 'navClickEvent'");
        this.e = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ ChatActivity_ViewBinding c;

            public void a(View view) {
                chatActivity.navClickEvent(view);
            }
        });
        a = b.a(view, R.id.send, "method 'sendText'");
        this.f = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ ChatActivity_ViewBinding c;

            public void a(View view) {
                chatActivity.sendText();
            }
        });
        a = b.a(view, R.id.send_image, "method 'sendImage'");
        this.g = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ ChatActivity_ViewBinding c;

            public void a(View view) {
                chatActivity.sendImage();
            }
        });
    }

    @CallSuper
    public void a() {
        ChatActivity chatActivity = this.b;
        if (chatActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        chatActivity.more = null;
        chatActivity.start_voice = null;
        chatActivity.chatRecordLayout = null;
        chatActivity.refreshLayout = null;
        chatActivity.recycler = null;
        chatActivity.input = null;
        chatActivity.voice_recorder = null;
        chatActivity.title = null;
        chatActivity.title_extra = null;
        chatActivity.panelLayout = null;
        chatActivity.voiceNotifyMsg = null;
        chatActivity.rippleBackground = null;
        chatActivity.voiceTouchNotify = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
        this.f.setOnClickListener(null);
        this.f = null;
        this.g.setOnClickListener(null);
        this.g = null;
    }
}
