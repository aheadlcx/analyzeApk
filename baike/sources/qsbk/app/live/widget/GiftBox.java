package qsbk.app.live.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import qsbk.app.core.model.GiftData;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.core.utils.PrefrenceKeys;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;
import qsbk.app.live.R;

public class GiftBox extends FrameLayout implements OnClickListener, OnEmptyClickListener {
    public static final int GIFT_TYPE_LIVE = 1;
    public static final int GIFT_TYPE_VIDEO = 2;
    private Context a;
    private EmptyPlaceholderView b;
    private LinearLayout c;
    private LinearLayout d;
    private TextView e;
    private TextView f;
    private GiftViewPager g;
    private DotView h;
    private GiftData i;
    private ProgressBar j;
    private SimpleDraweeView k;
    private boolean l;
    private boolean m;
    public Handler mHandler;
    private GiftBoxListener n;
    private int o;
    private int p;
    private final int q;

    public interface GiftBoxListener {
        void doSendGift();

        void onGiftItemClick(GiftData giftData);

        void toPayActivity();
    }

    public GiftBox(Context context) {
        this(context, null);
    }

    public GiftBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new Handler();
        this.l = false;
        this.m = true;
        this.o = 1;
        this.p = 0;
        this.q = 3;
        this.a = context;
        b();
    }

    private void b() {
        View inflate = LayoutInflater.from(this.a).inflate(R.layout.gift_box, null);
        this.b = (EmptyPlaceholderView) inflate.findViewById(R.id.gift_box_empty);
        this.c = (LinearLayout) inflate.findViewById(R.id.ll_gifts);
        this.d = (LinearLayout) inflate.findViewById(R.id.ll_pay);
        this.e = (TextView) inflate.findViewById(R.id.tv_balance);
        this.f = (TextView) inflate.findViewById(R.id.tv_send_gift);
        this.k = (SimpleDraweeView) inflate.findViewById(R.id.iv_first_charge);
        if (ConfigInfoUtil.instance().isFirstCharge()) {
            this.k.setVisibility(0);
            this.k.setOnClickListener(this);
            AppUtils.getInstance().getImageProvider().loadWebpImage(this.k, "res://raw/" + R.raw.live_gift_box_first_charge);
        } else {
            this.k.setVisibility(8);
        }
        this.g = (GiftViewPager) inflate.findViewById(R.id.gift_viewpager);
        this.h = (DotView) inflate.findViewById(R.id.dot_gifts);
        this.j = (ProgressBar) inflate.findViewById(R.id.gift_progress_bar);
        this.d.setOnClickListener(this);
        this.f.setOnClickListener(this);
        addView(inflate);
    }

    public void setGiftType(int i) {
        this.o = i;
    }

    public void initGiftView() {
        Collection giftList;
        if (this.o == 1) {
            giftList = ConfigInfoUtil.instance().getGiftList();
        } else {
            giftList = ConfigInfoUtil.instance().getVideoGiftList();
        }
        if (giftList == null || giftList.size() == 0) {
            ConfigInfoUtil.instance().setUpdateConfigCallback(new ds(this));
            if (this.p < 3) {
                ConfigInfoUtil.instance().updateConfigInfo();
                this.b.showProgressBar();
                this.p++;
                return;
            }
            this.b.setEmptyTextAndAction(R.string.live_load_fail, (OnEmptyClickListener) this);
            return;
        }
        this.l = true;
        this.b.hide();
        Map hashMap = new HashMap();
        List arrayList = new ArrayList();
        arrayList.addAll(giftList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            GiftData giftData = (GiftData) it.next();
            if (!(giftData.ga == null || giftData.ga.length <= 0 || PreferenceUtils.instance().getString(PrefrenceKeys.KEY_GIFT_ANIM + giftData.gd, "md5").equals(giftData.ga[0].m))) {
                it.remove();
            }
        }
        hashMap.put("remix", arrayList);
        this.g.setOnGiftItemClickListener(new dt(this));
        this.g.setDatas(hashMap);
        this.g.setDotView(this.h);
        clearGiftCheck();
        setBalanceView(AppUtils.getInstance().getUserInfoProvider().getBalance());
    }

    public void clearGiftCheck() {
        this.g.clearGiftCheck();
        this.i = null;
        c();
    }

    private void c() {
        TextView textView = this.f;
        boolean z = this.i != null && this.i.selected;
        textView.setEnabled(z);
    }

    public void showProgressBar() {
        if (this.j != null) {
            this.j.setVisibility(0);
            this.f.setEnabled(false);
        }
    }

    public void hideProgressBar() {
        if (this.j != null) {
            this.j.setVisibility(8);
            this.f.setEnabled(true);
        }
    }

    protected void a() {
        if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            NetRequest.getInstance().get(UrlConstants.GET_BALANCE, new du(this), "request_balance", true);
        }
    }

    public void setBalanceView(long j) {
        TextView textView = this.e;
        if (j <= 0) {
            j = 0;
        }
        textView.setText(String.valueOf(j));
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            if (!this.l) {
                ToastUtil.Short(R.string.live_gift_loading);
                this.b.showProgressBar();
                initGiftView();
            }
            if (this.m) {
                a();
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.ll_pay) {
            if (this.n != null) {
                this.n.toPayActivity();
            }
        } else if (view.getId() == R.id.tv_send_gift) {
            if (this.n != null) {
                this.n.doSendGift();
            }
        } else if (view.getId() == R.id.iv_first_charge) {
            AppUtils.getInstance().getUserInfoProvider().toPay((Activity) this.a, 103);
        }
    }

    public void onEmptyClick(View view) {
        this.p = 0;
        initGiftView();
    }

    public void setGiftBoxListener(GiftBoxListener giftBoxListener) {
        this.n = giftBoxListener;
    }

    public void hideFirstCharge() {
        this.k.setVisibility(8);
    }

    public void releaseResource() {
        this.n = null;
        removeAllViews();
    }

    public boolean isShowing() {
        return getVisibility() == 0;
    }
}
