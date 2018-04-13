package cn.xiaochuankeji.tieba.ui.hollow.edit;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.hollow.widget.RecordAnimView;

public class FragmentCreateAudioHollow_ViewBinding implements Unbinder {
    private FragmentCreateAudioHollow b;

    @UiThread
    public FragmentCreateAudioHollow_ViewBinding(FragmentCreateAudioHollow fragmentCreateAudioHollow, View view) {
        this.b = fragmentCreateAudioHollow;
        fragmentCreateAudioHollow.recordAnimView = (RecordAnimView) b.b(view, R.id.hollow_edit_anim, "field 'recordAnimView'", RecordAnimView.class);
        fragmentCreateAudioHollow.buttonMain = (ImageView) b.b(view, R.id.hollow_edit_bt_main, "field 'buttonMain'", ImageView.class);
        fragmentCreateAudioHollow.textPlay = (TextView) b.b(view, R.id.hollow_edit_tx_play, "field 'textPlay'", TextView.class);
        fragmentCreateAudioHollow.buttonReset = b.a(view, R.id.hollow_edit_bt_reset, "field 'buttonReset'");
        fragmentCreateAudioHollow.buttonPlay = b.a(view, R.id.hollow_edit_bt_play, "field 'buttonPlay'");
        fragmentCreateAudioHollow.hollowTime = (TextView) b.b(view, R.id.hollow_edit_time, "field 'hollowTime'", TextView.class);
        fragmentCreateAudioHollow.welcomeLayout = b.a(view, R.id.hollow_edit_ll_welcome, "field 'welcomeLayout'");
        fragmentCreateAudioHollow.timeLayout = b.a(view, R.id.hollow_edit_ll_time, "field 'timeLayout'");
    }

    @CallSuper
    public void a() {
        FragmentCreateAudioHollow fragmentCreateAudioHollow = this.b;
        if (fragmentCreateAudioHollow == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        fragmentCreateAudioHollow.recordAnimView = null;
        fragmentCreateAudioHollow.buttonMain = null;
        fragmentCreateAudioHollow.textPlay = null;
        fragmentCreateAudioHollow.buttonReset = null;
        fragmentCreateAudioHollow.buttonPlay = null;
        fragmentCreateAudioHollow.hollowTime = null;
        fragmentCreateAudioHollow.welcomeLayout = null;
        fragmentCreateAudioHollow.timeLayout = null;
    }
}
