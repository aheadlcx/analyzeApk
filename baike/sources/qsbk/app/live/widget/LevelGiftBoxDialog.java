package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.model.GiftData;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.core.widget.FrameAnimationView;
import qsbk.app.live.R;
import qsbk.app.live.adapter.LiveLevelBoxAdapter;
import qsbk.app.live.model.LiveLevelBox;

public class LevelGiftBoxDialog extends BaseDialog implements OnClickListener {
    private Activity c;
    private int d;
    private FrameAnimationView e;
    private FrameAnimationView f;
    private FrameLayout g;
    private SimpleDraweeView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private FrameLayout l;
    private TextView m;
    private FrameLayout n;
    private ImageView o;
    private RecyclerView p;
    private FrameLayout q;
    private AnimatorSet r;
    private AnimatorSet s;
    private LiveLevelBox t;
    private List<GiftData> u = new ArrayList();
    private LiveLevelBoxAdapter v;
    private int w;

    public LevelGiftBoxDialog(Activity activity, LiveLevelBox liveLevelBox) {
        super(activity);
        this.c = activity;
        this.t = liveLevelBox;
        this.d = liveLevelBox.gifts != null ? liveLevelBox.gifts.size() : 1;
    }

    protected int c() {
        return R.layout.level_gift_box_dialog;
    }

    protected void d() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -1;
            window.setAttributes(attributes);
        }
        this.e = (FrameAnimationView) a(R.id.level_gift_open_box);
        this.e.setFramesInAssets("level_gift_box/openBox");
        this.f = (FrameAnimationView) a(R.id.level_gift_light_effect);
        this.f.setFramesInAssets("level_gift_box/lightEffect");
        this.f.loop(true);
        this.g = (FrameLayout) a(R.id.level_gift);
        this.h = (SimpleDraweeView) a(R.id.level_gift_icon);
        this.i = (TextView) a(R.id.level_gift_num);
        this.j = (TextView) a(R.id.level_gift_text);
        this.k = (TextView) a(R.id.level_gift_quest);
        this.l = (FrameLayout) a(R.id.level_gift_card);
        this.m = (TextView) a(R.id.level_gift_card_num);
        this.n = (FrameLayout) a(R.id.level_gift_total);
        this.o = (ImageView) a(R.id.level_gift_box_full);
        this.p = (RecyclerView) a(R.id.recyclerview_gifts);
        this.v = new LiveLevelBoxAdapter(getContext(), this.u);
        this.p.setAdapter(this.v);
        this.p.setLayoutManager(new GridLayoutManager(getContext(), 3));
        this.p.setHasFixedSize(false);
        this.p.setItemAnimator(new DefaultItemAnimator());
        this.q = (FrameLayout) a(R.id.container);
        this.q.setOnClickListener(this);
        this.q.setClickable(false);
        this.m.setText(String.valueOf(this.d));
    }

    protected void e() {
        a(new gi(this));
        j();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.l, View.SCALE_X, new float[]{1.0f, 1.2f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.l, View.SCALE_Y, new float[]{1.0f, 1.2f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.l, View.SCALE_X, new float[]{1.2f, 1.0f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.l, View.SCALE_Y, new float[]{1.2f, 1.0f});
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.l, View.ALPHA, new float[]{0.5f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat5});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(200);
        animatorSet2.playTogether(new Animator[]{ofFloat3, ofFloat4});
        this.s = new AnimatorSet();
        this.s.playSequentially(new Animator[]{animatorSet, animatorSet2});
        ofFloat = ObjectAnimator.ofFloat(this.j, View.TRANSLATION_Y, new float[]{(float) WindowUtils.dp2Px(20), 0.0f});
        ofFloat2 = ObjectAnimator.ofFloat(this.j, View.SCALE_X, new float[]{0.1f, 1.0f});
        ofFloat3 = ObjectAnimator.ofFloat(this.j, View.SCALE_Y, new float[]{0.1f, 1.0f});
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet3.setDuration(400);
        ObjectAnimator.ofFloat(this.g, View.TRANSLATION_Y, new float[]{(float) WindowUtils.dp2Px(200), 0.0f}).setDuration(400);
        ObjectAnimator.ofFloat(this.g, View.SCALE_X, new float[]{0.5f, 1.0f}).setDuration(400);
        ObjectAnimator.ofFloat(this.g, View.SCALE_Y, new float[]{0.5f, 1.0f}).setDuration(400);
        ofFloat5 = ObjectAnimator.ofFloat(this.g, View.ALPHA, new float[]{0.5f, 1.0f});
        ofFloat5.setDuration(400);
        ofFloat5.addListener(new gj(this, animatorSet3));
        ObjectAnimator.ofFloat(this.g, View.ROTATION_Y, new float[]{-90.0f, 90.0f}).setDuration(400);
        ObjectAnimator.ofFloat(this.g, View.ROTATION_Y, new float[]{90.0f, 0.0f}).setDuration(400);
        new AnimatorSet().playSequentially(new Animator[]{ofFloat4, r5});
        this.r = new AnimatorSet();
        this.r.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat5, r6});
        this.e.setAnimationListener(new gk(this));
    }

    public void onClick(View view) {
        if (!AppUtils.isFastDoubleClick() && view.getId() == R.id.container) {
            if (this.d > 0) {
                TextView textView = this.m;
                int i = this.d - 1;
                this.d = i;
                textView.setText(String.valueOf(i));
                this.w++;
                j();
                this.e.play();
                this.r.cancel();
                this.r.start();
                this.s.start();
            } else if (this.p.getVisibility() == 0) {
                dismiss();
            } else {
                this.g.setVisibility(8);
                this.j.setVisibility(8);
                this.e.setVisibility(8);
                this.f.setVisibility(8);
                this.l.setVisibility(8);
                this.p.setVisibility(0);
                int i2 = -WindowUtils.dp2Px(120);
                if (this.t.gifts != null) {
                    i2 = this.t.gifts.size();
                    if (i2 <= 1) {
                        i2 = -WindowUtils.dp2Px(120);
                    } else if (i2 <= 1 || i2 > 4) {
                        i2 = -WindowUtils.dp2Px(180);
                    } else {
                        i2 = -WindowUtils.dp2Px(150);
                    }
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.n, View.TRANSLATION_Y, new float[]{0.0f, (float) i2});
                ofFloat.setDuration(200);
                ofFloat.start();
                ofFloat.addListener(new gm(this));
            }
        }
    }

    private void b(int i) {
        if (this.t.gifts != null && i - 2 < this.t.gifts.size()) {
            Object giftData;
            if (i == 0) {
                giftData = new GiftData();
                giftData.c = this.t.loveheart;
                giftData.gn = getContext().getString(R.string.live_gift_heart);
            } else if (i == 1) {
                giftData = new GiftData();
                giftData.c = this.t.coin;
                giftData.gn = getContext().getString(R.string.live_gift_diamond);
            } else {
                GiftData giftData2 = (GiftData) this.t.gifts.get(i - 2);
            }
            this.u.add(giftData);
            this.v.notifyItemInserted(i);
            a(new gn(this, i), 200);
        }
    }

    public void dismiss() {
        this.e.release();
        this.f.release();
        super.dismiss();
    }

    protected boolean g() {
        return false;
    }

    private void j() {
        if (this.w == 0) {
            this.h.setImageResource(R.drawable.level_gift_heart);
            this.i.setText(this.t.loveheart + "");
            this.j.setText(R.string.live_gift_heart);
        } else if (this.w == 1) {
            this.h.setImageResource(R.drawable.level_gift_diamond);
            this.i.setText(this.t.coin + "");
            this.j.setText(R.string.live_gift_diamond);
        } else if (this.w >= 2) {
            AppUtils.getInstance().getImageProvider().loadGift(this.h, ConfigInfoUtil.instance().getGiftUrlById(((GiftData) this.t.gifts.get(this.w - 2)).gd));
            this.i.setText(((GiftData) this.t.gifts.get(this.w - 2)).c + "");
            this.j.setText(((GiftData) this.t.gifts.get(this.w - 2)).gn);
        }
    }
}
