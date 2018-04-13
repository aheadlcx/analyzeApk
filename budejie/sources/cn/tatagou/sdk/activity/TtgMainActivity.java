package cn.tatagou.sdk.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.util.Log;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.e.a.b;
import cn.tatagou.sdk.fragment.TtgMainFragment;
import cn.tatagou.sdk.pojo.AppHomeData;
import cn.tatagou.sdk.pojo.TtgTitleBar;
import cn.tatagou.sdk.pojo.TtgUrl;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.view.IUpdateView;
import cn.tatagou.sdk.view.IUpdateViewManager;
import cn.tatagou.sdk.view.UpdateView;

public class TtgMainActivity extends SingleFragmentActivity {
    public static boolean sIsActShow = false;
    private TtgMainFragment mTtgMainFragment;

    protected Fragment createFragment() {
        sIsActShow = true;
        this.mTtgMainFragment = TtgMainFragment.newInstance(true);
        b.ttgStatEvent(String.valueOf(TtgConfig.getInstance().getPid()));
        b.cateStatEvent("1");
        initBackTtgMainNotify();
        return this.mTtgMainFragment;
    }

    private void initBackTtgMainNotify() {
        IUpdateViewManager.getInstance().registIUpdateView(new UpdateView("ttgGoBackTabHome", new IUpdateView<Boolean>(this) {
            final /* synthetic */ TtgMainActivity a;

            {
                this.a = r1;
            }

            public void updateView(Boolean bool) {
                if (!bool.booleanValue()) {
                    this.a.onTtgUrlParse(AppHomeData.getInstance().getTtgUrl());
                }
            }
        }));
    }

    private void onTtgUrlParse(TtgUrl ttgUrl) {
        if (ttgUrl != null) {
            Uri parseUrl = ttgUrl.parseUrl();
            if (!p.isEmpty(ttgUrl.getHost()) && parseUrl != null) {
                String queryParameter = parseUrl.getQueryParameter("notify");
                Log.d("TTG", "onTtgUrlParseTTG: " + queryParameter);
                if ("notify".equals(queryParameter)) {
                    IUpdateViewManager.getInstance().notifyIUpdateView(TtgConfigKey.KEY_TTGMAIN_COLSE_NOTIFY, Boolean.valueOf(true));
                }
            }
        }
    }

    protected void setStatusBar() {
        if (TtgTitleBar.getInstance().isBackIconShown()) {
            super.setStatusBar();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mTtgMainFragment != null) {
            this.mTtgMainFragment.onActivityResult(i, i2, intent);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.mTtgMainFragment != null) {
            this.mTtgMainFragment.onNewIntent(intent);
        }
    }

    public void onBackPressed() {
        if (this.mTtgMainFragment == null || this.mTtgMainFragment.mAppCate == null || this.mTtgMainFragment.mScrollCatViewPage == null || this.mTtgMainFragment.mAppCate.getPosition() == 0) {
            onTtgUrlParse(AppHomeData.getInstance().getTtgUrl());
            super.onBackPressed();
            return;
        }
        this.mTtgMainFragment.mScrollCatViewPage.setCurrentItem(0);
        this.mTtgMainFragment.mAppCate.setPosition(0);
    }

    protected void onResume() {
        super.onResume();
        sIsActShow = true;
    }

    protected void onPause() {
        super.onPause();
        sIsActShow = false;
    }

    protected void onDestroy() {
        IUpdateViewManager.getInstance().unRegistIUpdateView("ttgGoBackTabHome");
        super.onDestroy();
    }
}
