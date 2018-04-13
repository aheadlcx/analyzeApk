package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.model.GiftData;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.live.R;
import qsbk.app.live.ui.LiveBaseActivity;

public class GameWinDialog extends BaseDialog implements OnClickListener {
    public static final int SHOW_WIN_MILLIS = 5000;
    private int A;
    private Runnable B;
    private List<Integer> C;
    private TextView D;
    LiveBaseActivity c;
    private FrameLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private LinearLayout g;
    private LinearLayout h;
    private LinearLayout i;
    private LinearLayout j;
    private SimpleDraweeView k;
    private SimpleDraweeView l;
    private SimpleDraweeView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private long r;
    private long s;
    private TextView t;
    private TextView u;
    private CountDownTimer v;
    private MediaPlayer w;
    private List<GiftData> x;
    private Runnable y;
    private boolean z;

    public GameWinDialog(LiveBaseActivity liveBaseActivity, long j, long j2, int i, List<Integer> list) {
        this(liveBaseActivity, j, j2, list);
        this.A = i;
    }

    public GameWinDialog(LiveBaseActivity liveBaseActivity, long j, long j2, List<Integer> list) {
        super(liveBaseActivity);
        this.z = false;
        this.c = liveBaseActivity;
        this.s = j;
        this.r = j2;
        this.C = list;
    }

    protected int c() {
        switch ((int) this.s) {
            case 1:
                return R.layout.activity_game_hlnb_win_dialog;
            case 2:
                return R.layout.activity_game_ypdx_win_dialog;
            case 3:
                return R.layout.activity_game_catanddog_win_dialog;
            case 4:
                return R.layout.activity_game_fanfanle_win_dialog;
            case 5:
                return R.layout.activity_game_rolltable_win_dialog;
            case 6:
                return R.layout.activity_game_rolltable_win_dialog;
            default:
                return 0;
        }
    }

    protected void d() {
        int i = 0;
        i();
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.SimpleDialog_RollUp);
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -1;
            window.setAttributes(attributes);
        }
        this.d = (FrameLayout) a(R.id.iv_up);
        this.e = (LinearLayout) a(R.id.game_gift_1);
        this.f = (LinearLayout) a(R.id.game_gift_2);
        this.g = (LinearLayout) a(R.id.game_gift_3);
        this.h = (LinearLayout) a(R.id.game_gift_send);
        this.i = (LinearLayout) a(R.id.game_gift_close);
        this.j = (LinearLayout) a(R.id.game_gift_next);
        if (this.j != null) {
            this.j.setVisibility(this.A == 7 ? 8 : 0);
        }
        this.k = (SimpleDraweeView) a(R.id.game_gift_iv_1);
        this.l = (SimpleDraweeView) a(R.id.game_gift_iv_2);
        this.m = (SimpleDraweeView) a(R.id.game_gift_iv_3);
        this.n = (TextView) a(R.id.game_gift_tv_1);
        this.o = (TextView) a(R.id.game_gift_tv_2);
        this.p = (TextView) a(R.id.game_gift_tv_3);
        this.t = (TextView) a(R.id.game_time_count);
        this.u = (TextView) a(R.id.game_time_count_close);
        if (this.u != null) {
            TextView textView = this.u;
            if (this.A != 7) {
                i = 8;
            }
            textView.setVisibility(i);
        }
        this.q = (TextView) a(R.id.num_win);
        if (this.e != null) {
            this.e.setOnClickListener(this);
        }
        if (this.f != null) {
            this.f.setOnClickListener(this);
        }
        if (this.g != null) {
            this.g.setOnClickListener(this);
        }
        this.D = (TextView) a(R.id.tv_send_gift);
    }

    private void a(TextView textView, long j) {
        if (j > 0) {
            NumberAnimTextView numberAnimTextView = (NumberAnimTextView) textView;
            numberAnimTextView.setDuration(520);
            numberAnimTextView.setNumberString("0", String.valueOf(j));
            return;
        }
        textView.setText("0");
    }

    protected void e() {
        if (this.r < 1000000) {
            this.b.postDelayed(new dj(this), 300);
        } else {
            this.q.setText((this.r / 10000) + "万");
        }
        this.x = ConfigInfoUtil.instance().getGameGiftList(this.s + "");
        if (this.x == null || this.x.isEmpty()) {
            this.x = new ArrayList();
            GiftData giftData = new GiftData();
            giftData.gd = 19;
            giftData.gn = "啤酒";
            giftData.ig = "https://livegift.app-remix.com/98ffe21ae01201.png";
            giftData.pr = 6;
            this.x.add(giftData);
            giftData = new GiftData();
            giftData.gd = 6;
            giftData.gn = "钻戒";
            giftData.ig = "https://livegift.app-remix.com/92abf3b360120d.png";
            giftData.pr = 266;
            this.x.add(giftData);
            giftData = new GiftData();
            giftData.gd = 7;
            giftData.gn = "豪车";
            giftData.ig = "https://livegift.app-remix.com/b0550a3ce03201.png";
            giftData.pr = 1200;
            this.x.add(giftData);
        }
        for (int i = 0; i < this.x.size(); i++) {
            giftData = (GiftData) this.x.get(i);
            if (i == 0) {
                this.n.setText(giftData.pr + "钻");
                AppUtils.getInstance().getImageProvider().loadGift(this.k, giftData.ig);
                if (this.C.contains(Integer.valueOf(i))) {
                    this.e.setSelected(true);
                }
            } else if (i == 1) {
                this.f.setVisibility(0);
                this.o.setText(giftData.pr + "钻");
                AppUtils.getInstance().getImageProvider().loadGift(this.l, giftData.ig);
                if (this.C.contains(Integer.valueOf(i))) {
                    this.f.setSelected(true);
                }
            } else if (i == 2) {
                this.g.setVisibility(0);
                this.p.setText(giftData.pr + "钻");
                AppUtils.getInstance().getImageProvider().loadGift(this.m, giftData.ig);
                if (this.C.contains(Integer.valueOf(i))) {
                    this.g.setSelected(true);
                }
            }
        }
        this.v = new dk(this, Config.BPLUS_DELAY_TIME, 100);
        this.v.start();
        this.w = MediaPlayer.create(this.a, R.raw.coins_fly_in);
        this.w.setOnCompletionListener(new dl(this));
        this.b.postDelayed(new dm(this), 800);
        if (this.s != 4) {
            for (int i2 = 1; i2 < 7; i2++) {
                this.b.postDelayed(new dn(this), (long) ((i2 * 80) + ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE));
            }
        }
        if (this.s == 4) {
            this.i.setOnClickListener(this);
            this.j.setOnClickListener(this);
            return;
        }
        m();
    }

    private void j() {
        int[] iArr = new int[2];
        findViewById(R.id.heart_win).getLocationOnScreen(iArr);
        View imageView = new ImageView(getContext());
        imageView.setScaleType(ScaleType.FIT_XY);
        imageView.setImageResource(R.drawable.live_game_win_heart);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(WindowUtils.dp2Px(43), WindowUtils.dp2Px(35));
        layoutParams.leftMargin = iArr[0];
        layoutParams.topMargin = iArr[1];
        imageView.setLayoutParams(layoutParams);
        this.d.addView(imageView);
        int screenHeight = (WindowUtils.getScreenHeight() - iArr[1]) - WindowUtils.dp2Px(60);
        if (!this.z && this.s == 4 && this.A <= 6) {
            screenHeight = ((WindowUtils.getScreenHeight() - iArr[1]) - WindowUtils.dp2Px(201)) + WindowUtils.dp2Px(10);
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, new float[]{0.0f, (float) ((-iArr[0]) + WindowUtils.dp2Px(30))});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{0.0f, (float) screenHeight});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView, View.SCALE_X, new float[]{1.0f, 0.3f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{1.0f, 0.3f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
        animatorSet.setDuration(500);
        animatorSet.addListener(new do(this, imageView));
        animatorSet.start();
    }

    public void onClick(View view) {
        if (view == this.e) {
            a(0, this.e);
        } else if (view == this.f) {
            a(1, this.f);
        } else if (view == this.g) {
            a(2, this.g);
        } else if (view == this.h) {
            k();
        } else if (view == this.i) {
            this.z = true;
            this.i.setClickable(false);
            k();
        } else if (view == this.j) {
            this.j.setClickable(false);
            k();
        }
    }

    private void k() {
        if (this.e.isSelected()) {
            this.c.sendGift(((GiftData) this.x.get(0)).gd);
            this.C.add(Integer.valueOf(0));
        }
        if (this.f.isSelected()) {
            this.c.sendGift(((GiftData) this.x.get(1)).gd);
            this.C.add(Integer.valueOf(1));
        }
        if (this.g.isSelected()) {
            this.c.sendGift(((GiftData) this.x.get(2)).gd);
            this.C.add(Integer.valueOf(2));
        }
        dismiss();
    }

    private void a(int i, LinearLayout linearLayout) {
        if (AppUtils.getInstance().getUserInfoProvider().getBalance() < ((GiftData) this.x.get(i)).pr) {
            l();
            return;
        }
        linearLayout.setSelected(!linearLayout.isSelected());
        m();
    }

    private void l() {
        Builder dpVar = new dp(this, R.style.SimpleDialog);
        dpVar.message(this.c.getString(R.string.live_balance_not_sufficient_hint)).positiveAction(this.c.getString(R.string.live_charge)).negativeAction(this.c.getString(R.string.setting_cancel));
        AppUtils.showDialogFragment(this.c, dpVar);
    }

    protected int a() {
        return 48;
    }

    public void dismiss() {
        int i = 1;
        if (this.c != null && !this.c.isFinishing() && this.c.isOnResume) {
            if (this.v != null) {
                this.v.cancel();
            }
            if (this.w != null) {
                this.w.release();
            }
            if (this.s == 4) {
                if (this.z && this.B != null) {
                    this.B.run();
                } else if (this.y != null) {
                    this.y.run();
                }
                while (i < 7) {
                    this.b.postDelayed(new dq(this), (long) ((i * 80) + ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE));
                    i++;
                }
                this.b.postDelayed(new dr(this), 1500);
                return;
            }
            if (!this.e.isSelected()) {
                this.C.remove(Integer.valueOf(0));
            }
            if (!this.f.isSelected()) {
                this.C.remove(Integer.valueOf(1));
            }
            if (!this.g.isSelected()) {
                this.C.remove(Integer.valueOf(2));
            }
            super.dismiss();
        }
    }

    public void setCloseListener(Runnable runnable) {
        this.B = runnable;
    }

    public void setNextListener(Runnable runnable) {
        this.y = runnable;
    }

    private void m() {
        if ((this.e == null || !this.e.isSelected()) && ((this.f == null || !this.f.isSelected()) && (this.g == null || !this.g.isSelected()))) {
            if (this.h != null) {
                this.h.setEnabled(false);
            }
            if (this.D != null) {
                this.D.setTextColor(-1);
            }
            if (this.t != null) {
                this.t.setTextColor(-1);
                return;
            }
            return;
        }
        if (this.h != null) {
            this.h.setEnabled(true);
            this.h.setOnClickListener(this);
        }
        if (this.D != null) {
            this.D.setTextColor(Color.parseColor("#FFE503"));
        }
        if (this.t != null) {
            this.t.setTextColor(Color.parseColor("#FFE503"));
        }
    }
}
