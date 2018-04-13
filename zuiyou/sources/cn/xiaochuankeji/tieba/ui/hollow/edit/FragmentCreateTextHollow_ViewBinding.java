package cn.xiaochuankeji.tieba.ui.hollow.edit;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

public class FragmentCreateTextHollow_ViewBinding implements Unbinder {
    private FragmentCreateTextHollow b;

    @UiThread
    public FragmentCreateTextHollow_ViewBinding(FragmentCreateTextHollow fragmentCreateTextHollow, View view) {
        this.b = fragmentCreateTextHollow;
        fragmentCreateTextHollow.refreshFun = b.a(view, R.id.tree_publish_refresh_fun, "field 'refreshFun'");
        fragmentCreateTextHollow.refreshView = b.a(view, R.id.tree_publish_refresh, "field 'refreshView'");
        fragmentCreateTextHollow.editText = (EditText) b.b(view, R.id.tree_publish_edit, "field 'editText'", EditText.class);
        fragmentCreateTextHollow.changeName = b.a(view, R.id.btn_change_name, "field 'changeName'");
        fragmentCreateTextHollow.nicknameText = (TextView) b.b(view, R.id.tv_nickname, "field 'nicknameText'", TextView.class);
        fragmentCreateTextHollow.scrollView = (DiscreteScrollView) b.b(view, R.id.tree_publish_emotion, "field 'scrollView'", DiscreteScrollView.class);
        fragmentCreateTextHollow.editFunView = b.a(view, R.id.tree_publish_edit_fun, "field 'editFunView'");
        fragmentCreateTextHollow.editWarnInfo = (TextView) b.b(view, R.id.edit_warn_info, "field 'editWarnInfo'", TextView.class);
    }

    @CallSuper
    public void a() {
        FragmentCreateTextHollow fragmentCreateTextHollow = this.b;
        if (fragmentCreateTextHollow == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        fragmentCreateTextHollow.refreshFun = null;
        fragmentCreateTextHollow.refreshView = null;
        fragmentCreateTextHollow.editText = null;
        fragmentCreateTextHollow.changeName = null;
        fragmentCreateTextHollow.nicknameText = null;
        fragmentCreateTextHollow.scrollView = null;
        fragmentCreateTextHollow.editFunView = null;
        fragmentCreateTextHollow.editWarnInfo = null;
    }
}
