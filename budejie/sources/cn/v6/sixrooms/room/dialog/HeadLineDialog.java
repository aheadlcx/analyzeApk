package cn.v6.sixrooms.room.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.adapter.DialogHeadLineAdapter;
import cn.v6.sixrooms.room.bean.OnHeadlineBean;
import cn.v6.sixrooms.room.interfaces.HeadLineViewable;
import cn.v6.sixrooms.room.presenter.HeadLinePresenter;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import java.util.List;

public class HeadLineDialog extends BaseDialog implements OnClickListener, HeadLineViewable {
    private static final String a = HeadLineDialog.class.getSimpleName();
    private HeadLinePresenter b = HeadLinePresenter.getInstance();
    private DialogHeadLineAdapter c;
    private View d;
    private View e;
    private View f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private ListView m;
    private HeadLineRuleDialog n;

    public HeadLineRuleDialog getmHeadLineRuleDialog() {
        return this.n;
    }

    public void setmHeadLineRuleDialog(HeadLineRuleDialog headLineRuleDialog) {
        this.n = headLineRuleDialog;
    }

    public HeadLineDialog(BaseRoomActivity baseRoomActivity, WrapRoomInfo wrapRoomInfo) {
        super(baseRoomActivity, wrapRoomInfo);
        this.b.setHeadLineViewable(this);
        this.i = (TextView) this.d.findViewById(R.id.tv_rule);
        this.j = (TextView) this.d.findViewById(R.id.tv_count_down_time);
        this.k = (TextView) this.d.findViewById(R.id.tv_desc);
        this.l = (TextView) this.d.findViewById(R.id.tv_ended);
        this.g = (TextView) this.d.findViewById(R.id.tv_title_left);
        this.h = (TextView) this.d.findViewById(R.id.tv_title_right);
        this.m = (ListView) this.d.findViewById(R.id.lv_head_line);
        this.e = this.d.findViewById(R.id.bar_left);
        this.f = this.d.findViewById(R.id.bar_right);
        a(true, false);
        a(0);
        initListener();
    }

    protected View getDialogContentView() {
        this.d = View.inflate(this.mBaseRoomActivity, R.layout.dialog_headline, null);
        return this.d;
    }

    private void a(int i) {
        this.b.getTop8Info(String.valueOf(i), LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
    }

    public void initListener() {
        this.i.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
    }

    private void a(boolean z, boolean z2) {
        this.g.setSelected(z);
        this.e.setSelected(z);
        this.h.setSelected(z2);
        this.f.setSelected(z2);
        if (z) {
            this.j.setVisibility(0);
            this.k.setVisibility(0);
            this.l.setVisibility(8);
        }
        if (z2) {
            this.j.setVisibility(8);
            this.k.setVisibility(8);
            this.l.setVisibility(0);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_title_left) {
            a(true, false);
            if (!this.b.ismIsInitThisList() || this.c == null) {
                LogUtils.e(a, "---initTop8Info");
                a(0);
                return;
            }
            LogUtils.e(a, "---notifyDataSetChanged");
            this.b.changeToThisList();
            this.c.notifyDataSetChanged();
        } else if (id == R.id.tv_title_right) {
            a(false, true);
            if (!this.b.ismIsInitLastList() || this.c == null) {
                LogUtils.e(a, "initTop8Info---");
                a(1);
                return;
            }
            LogUtils.e(a, "notifyDataSetChanged---");
            this.b.changeToLastList();
            this.c.notifyDataSetChanged();
        } else if (id == R.id.tv_rule) {
            this.b.showHeadLineDetail();
        }
    }

    public void updateTop8View(List<OnHeadlineBean> list) {
        if (this.c != null) {
            this.c.notifyDataSetChanged();
            return;
        }
        this.c = new DialogHeadLineAdapter(this.mBaseRoomActivity, list);
        this.m.setAdapter(this.c);
    }

    public void updateCountDownTime(String str) {
        this.j.setText(str);
    }

    public void showHeadLineDetail() {
        if (this.n == null) {
            this.n = new HeadLineRuleDialog(this.mBaseRoomActivity);
        }
        this.n.show();
    }

    public void showErrorDialog(String str, String str2) {
        this.mBaseRoomActivity.handleErrorResult(str, str2, this.mBaseRoomActivity);
    }

    public void showLoginDialog() {
        this.mBaseRoomActivity.showLoginDialog();
    }

    public void showErrorToast(int i) {
        this.mBaseRoomActivity.showErrorToast(i);
    }

    public void dismiss() {
        super.dismiss();
    }
}
