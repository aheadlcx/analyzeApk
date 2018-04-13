package cn.v6.sixrooms.ui.phone;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.HistoryBaseAdapter;
import cn.v6.sixrooms.adapter.HistoryBaseAdapter.CallBack;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersListView;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

public class HistoryActivity extends SlidingActivity {
    private int a;
    private RelativeLayout b;
    private HistoryBaseAdapter c;
    private StickyListHeadersListView d;
    private Dialog e;
    private CallBack f;
    private TextView g;

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_history_main);
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), getResources().getString(R.string.str_clear), null, getResources().getString(R.string.histoy_watch), new az(this), new ba(this));
        this.g = (TextView) findViewById(R.id.history_tv);
        this.f = new bc(this);
        this.b = (RelativeLayout) findViewById(R.id.mHistoryView);
        this.a = getWindowManager().getDefaultDisplay().getWidth();
        this.d = (StickyListHeadersListView) findViewById(R.id.lv_sticky_history);
        this.c = new HistoryBaseAdapter(this, this.f);
        this.d.setAdapter(this.c);
        this.d.setOnItemClickListener(new bd(this));
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setShadowWidth(20);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new be(this));
        Animation translateAnimation = new TranslateAnimation((float) this.a, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.b.startAnimation(translateAnimation);
    }

    protected void onResume() {
        super.onResume();
        this.c.setRes();
        this.c.notifyDataSetChanged();
        this.c.setFlag(false);
        a();
    }

    private void a() {
        if (this.c.getCount() > 0) {
            getTitleBarRight().setTextColor(getResources().getColor(R.color.white));
            getTitleBarRightFrame().setClickable(true);
            this.g.setVisibility(8);
            this.d.setVisibility(0);
            return;
        }
        b();
    }

    private void b() {
        getTitleBarRightFrame().setClickable(false);
        getTitleBarRight().setTextColor(getResources().getColor(R.color.gray));
        this.g.setVisibility(0);
        this.d.setVisibility(8);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        showMenu();
        return true;
    }
}
