package qsbk.app.live.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.adapter.LiveRecommendAdapter;

public class LivePullEndDialog extends LiveEndDialog {
    private Button e;
    private Button f;
    private RecyclerView g;
    private LinearLayout h;
    private ImageView i;
    private TextView j;
    private User k;
    private long l;

    public LivePullEndDialog(Context context, User user, OnClickListener onClickListener, long j) {
        super(context, onClickListener);
        this.k = user;
        this.l = j;
    }

    protected void d() {
        super.d();
        this.e = (Button) findViewById(R.id.btn_followed);
        this.f = (Button) findViewById(R.id.btn_follow);
        this.g = (RecyclerView) findViewById(R.id.recommendRecylerView);
        this.h = (LinearLayout) findViewById(R.id.recommendArea);
        this.i = (ImageView) findViewById(R.id.iv_avatar);
        this.j = (TextView) findViewById(R.id.name);
        this.f.setOnClickListener(new hf(this));
        this.i.setOnClickListener(new hg(this));
    }

    protected void e() {
        if (this.k != null) {
            if (this.k.isFollow()) {
                hideFollowBtn();
                showFollowedBtn();
            } else {
                showFollowBtn();
                hideFollowedBtn();
            }
            showAnchor(this.k.headurl, this.k.name);
        } else {
            hideFollowBtn();
            hideFollowedBtn();
            ajustConfirmTopDist(WindowUtils.dp2Px(65));
        }
        showRecommendLive();
    }

    protected int c() {
        return R.layout.live_pull_end_dialog;
    }

    private void j() {
        if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            hideFollowBtn();
            showFollowedBtn();
            NetRequest.getInstance().post(UrlConstants.USER_FOLLOW_NEW, new hh(this), "follow", true);
            return;
        }
        AppUtils.getInstance().getUserInfoProvider().toLogin((Activity) this.a, 1001);
    }

    public void showFollowBtn() {
        this.f.setVisibility(0);
    }

    public void hideFollowBtn() {
        this.f.setVisibility(8);
    }

    public void showFollowedBtn() {
        this.e.setVisibility(0);
    }

    public void hideFollowedBtn() {
        this.e.setVisibility(8);
    }

    public void ajustConfirmTopDist(int i) {
        ((LayoutParams) this.c.getLayoutParams()).topMargin = i;
    }

    public void showAnchor(String str, String str2) {
        this.i.setVisibility(0);
        this.j.setVisibility(0);
        AppUtils.getInstance().getImageProvider().loadAvatar(this.i, str, true);
        this.j.setText(str2);
    }

    public void showRecommendLive() {
        List arrayList = new ArrayList();
        arrayList.add(new CommonVideo());
        arrayList.add(new CommonVideo());
        Adapter liveRecommendAdapter = new LiveRecommendAdapter(this.a, arrayList);
        this.g.setLayoutManager(new GridLayoutManager(this.a, 2));
        liveRecommendAdapter.setOnItemClickListener(new hi(this, arrayList));
        this.g.setAdapter(liveRecommendAdapter);
        this.g.setItemAnimator(new DefaultItemAnimator());
        this.g.setHasFixedSize(true);
        Map hashMap = new HashMap();
        hashMap.put("room", this.l + "");
        AppUtils.getInstance().getUserInfoProvider().networkRequest("recommendLive", hashMap, new hj(this, hashMap, arrayList, liveRecommendAdapter));
    }
}
