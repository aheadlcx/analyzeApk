package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.FrameAnimationView;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.ui.helper.LiveMessageListener;
import qsbk.app.live.utils.FontUtils;

public class GiftLayout extends RelativeLayout {
    private String a;
    private int b;
    private int c;
    private ArrayList<LiveGiftMessage> d;
    private boolean e;
    private int f;
    private int g;
    private LiveMessageListener h;

    public class GiftInfo extends RelativeLayout {
        final /* synthetic */ GiftLayout a;
        private ImageView b;
        private TextView c;
        private TextView d;
        private LinearLayout e;
        private TextView f;
        private TextView g;
        private RelativeLayout h;
        private FrameAnimationView i;
        public boolean isAvailable;
        public ImageView iv_gift;
        private LiveGiftMessage j;

        public GiftInfo(GiftLayout giftLayout, Context context) {
            this(giftLayout, context, null);
        }

        public GiftInfo(GiftLayout giftLayout, Context context, AttributeSet attributeSet) {
            this(giftLayout, context, attributeSet, 0);
        }

        public GiftInfo(GiftLayout giftLayout, Context context, AttributeSet attributeSet, int i) {
            this.a = giftLayout;
            super(context, attributeSet, i);
            this.isAvailable = true;
            a(context);
        }

        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
        }

        private void a(Context context) {
            View inflate = View.inflate(context, R.layout.live_gift_animation_layout, this);
            this.b = (ImageView) inflate.findViewById(R.id.live_gift_avatar);
            this.iv_gift = (ImageView) inflate.findViewById(R.id.live_gift_image);
            this.c = (TextView) inflate.findViewById(R.id.live_gift_username);
            this.d = (TextView) inflate.findViewById(R.id.live_gift_name);
            this.e = (LinearLayout) inflate.findViewById(R.id.live_ll_gift_count);
            this.f = (TextView) inflate.findViewById(R.id.live_tv_gift_count);
            this.i = (FrameAnimationView) inflate.findViewById(R.id.live_gift_anim);
            this.i.loop(true);
            this.f.setTypeface(FontUtils.getBloggerSansFontBoldItalic());
            this.b.setOnClickListener(new eb(this));
            this.g = (TextView) inflate.findViewById(R.id.tv_gift_x);
            this.h = (RelativeLayout) inflate.findViewById(R.id.part_without_text);
        }

        public void setContent(LiveGiftMessage liveGiftMessage, boolean z) {
            this.isAvailable = false;
            this.j = liveGiftMessage;
            this.j.count = liveGiftMessage.getGiftCount();
            int parseColor = Color.parseColor(ConfigInfoUtil.instance().getHitNumColor(liveGiftMessage.count));
            this.f.setTextColor(parseColor);
            this.g.setTextColor(parseColor);
            if (z) {
                this.f.setText(liveGiftMessage.count + "");
                this.d.setText(liveGiftMessage.getGiftShowContent());
                AppUtils.getInstance().getImageProvider().loadGift(this.iv_gift, liveGiftMessage.getGiftUrl());
            } else if (!(TextUtils.isEmpty(liveGiftMessage.getUserName()) || TextUtils.isEmpty(liveGiftMessage.getUserAvatar()) || TextUtils.isEmpty(liveGiftMessage.getGiftName()))) {
                AppUtils.getInstance().getImageProvider().loadAvatar(this.b, liveGiftMessage.getUserAvatar(), true);
                AppUtils.getInstance().getImageProvider().loadGift(this.iv_gift, liveGiftMessage.getGiftUrl());
                this.c.setText(liveGiftMessage.getUserName());
                this.d.setText(liveGiftMessage.getGiftShowContent());
                this.f.setText(liveGiftMessage.count + "");
                this.e.setVisibility(8);
                setVisibility(0);
                File file = new File(Environment.getExternalStorageDirectory() + "/Remix/.Animation/" + liveGiftMessage.getGiftId());
                String[] list = file.list();
                if (!file.exists() || list == null || list.length <= 0) {
                    this.iv_gift.setVisibility(0);
                } else {
                    this.i.setVisibility(0);
                    this.i.setFramesInSdCard(Environment.getExternalStorageDirectory() + "/Remix/.Animation/" + liveGiftMessage.getGiftId());
                    this.i.play();
                    this.iv_gift.setVisibility(4);
                }
            }
            if (liveGiftMessage.getMessageFamilyLevel() > 9) {
                this.h.setBackgroundResource(FamilyLevelView.getFamilyLevelGiftDrawableResource(liveGiftMessage.getMessageFamilyLevel()));
            } else {
                this.h.setBackgroundResource(R.drawable.live_gift_bg);
            }
        }
    }

    public GiftLayout(Context context) {
        this(context, null);
    }

    public GiftLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GiftLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = GiftLayout.class.getSimpleName();
        this.b = 0;
        this.c = 2;
        this.d = new ArrayList();
        this.e = false;
        setGravity(80);
        a();
    }

    private void a() {
        for (int i = 0; i < this.c; i++) {
            setGift().setVisibility(4);
        }
    }

    public void setLiveMessageListener(LiveMessageListener liveMessageListener) {
        this.h = liveMessageListener;
    }

    public void addGift(LiveGiftMessage liveGiftMessage) {
        GiftInfo availableGiftInfo;
        boolean z;
        Object obj = 1;
        GiftInfo a = this.e ? null : a(liveGiftMessage.getUserId(), liveGiftMessage.getGiftId());
        if (a == null) {
            availableGiftInfo = getAvailableGiftInfo();
        } else {
            availableGiftInfo = a;
        }
        if (availableGiftInfo == null || availableGiftInfo.isAvailable) {
            z = false;
        } else {
            z = true;
        }
        if (!z || liveGiftMessage.getGiftId() != availableGiftInfo.j.getGiftId() || availableGiftInfo.j.count <= liveGiftMessage.getGiftCount()) {
            if (!(liveGiftMessage.getUserId() == AppUtils.getInstance().getUserInfoProvider().getUserId() || this.d.size() == 0 || !z)) {
                obj = null;
            }
            if (availableGiftInfo == null || r0 == null) {
                a(liveGiftMessage);
                return;
            }
            availableGiftInfo.setContent(liveGiftMessage, z);
            a(availableGiftInfo, z);
            a(liveGiftMessage, availableGiftInfo);
        }
    }

    private boolean a(int i, long j) {
        long giftPriceById = ConfigInfoUtil.instance().getGiftPriceById(j);
        List giftBlossomHitValues = ConfigInfoUtil.instance().getGiftBlossomHitValues();
        for (int i2 = 0; i2 < giftBlossomHitValues.size(); i2++) {
            int intValue = ((Integer) giftBlossomHitValues.get(i2)).intValue();
            if (((long) (i - 1)) * giftPriceById < ((long) intValue) && ((long) i) * giftPriceById >= ((long) intValue)) {
                return true;
            }
        }
        return false;
    }

    private void a(LiveGiftMessage liveGiftMessage, GiftInfo giftInfo) {
        if (a(liveGiftMessage.getGiftId()) && a(liveGiftMessage.getGiftCount(), liveGiftMessage.getGiftId()) && this.h != null && !this.h.isMessageOverloadOrLowDevice()) {
            int[] iArr = new int[2];
            giftInfo.getLocationOnScreen(iArr);
            if (this.f == 0 && this.g == 0) {
                this.f = giftInfo.iv_gift.getHeight() / 2;
                this.g = giftInfo.iv_gift.getLeft() + this.f;
            }
            this.h.onShowSpecialAnimWhenMeetACertainNumber(iArr[1] + this.f, this.g, liveGiftMessage.getGiftId());
            a(liveGiftMessage.getGiftId(), giftInfo.iv_gift);
        }
    }

    private void a(long j, View view) {
        ObjectAnimator ofFloat;
        ObjectAnimator ofFloat2;
        ObjectAnimator ofFloat3;
        if (j == 11 || j == 32) {
            ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 1.2f});
            ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 1.2f});
            ofFloat3 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.2f, 1.0f});
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.2f, 1.0f});
            new AnimatorSet().playTogether(new Animator[]{ofFloat, ofFloat2});
            new AnimatorSet().playTogether(new Animator[]{ofFloat3, ofFloat4});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{r4, r0});
            animatorSet.setDuration(200);
            animatorSet.start();
        } else if (j == 26) {
            float dp2Px = (float) WindowUtils.dp2Px(5);
            ofFloat2 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{0.0f, dp2Px});
            ofFloat3 = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{dp2Px, -dp2Px});
            ofFloat = ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{-dp2Px, 0.0f});
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playSequentially(new Animator[]{ofFloat2, ofFloat3, ofFloat});
            animatorSet2.setDuration(100);
            animatorSet2.start();
        }
    }

    private boolean a(long j) {
        return j == 9 || j == 11 || j == 26 || j == 32;
    }

    private void a(LiveGiftMessage liveGiftMessage) {
        int i = 0;
        while (i < this.d.size()) {
            if (((LiveGiftMessage) this.d.get(i)).getUserId() != liveGiftMessage.getUserId() || ((LiveGiftMessage) this.d.get(i)).getGiftId() != liveGiftMessage.getGiftId()) {
                i++;
            } else if (((LiveGiftMessage) this.d.get(i)).getGiftCount() < liveGiftMessage.getGiftCount()) {
                ((LiveGiftMessage) this.d.get(i)).setGiftCount(liveGiftMessage.getGiftCount());
                return;
            } else {
                return;
            }
        }
        this.d.add(liveGiftMessage);
    }

    private void a(GiftInfo giftInfo, boolean z) {
        Animation animationSet = new AnimationSet(false);
        animationSet.setDuration(200);
        animationSet.addAnimation(new AlphaAnimation(0.0f, 1.0f));
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(2500);
        AnimationSet animationSet2 = new AnimationSet(false);
        animationSet2.setDuration(200);
        animationSet2.addAnimation(new AlphaAnimation(1.0f, 0.0f));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(giftInfo.e, View.SCALE_X, new float[]{0.92f, 0.98f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(giftInfo.e, View.SCALE_Y, new float[]{0.6f, 0.9f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(giftInfo.e, View.SCALE_X, new float[]{0.98f, 1.04f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(giftInfo.e, View.SCALE_Y, new float[]{0.9f, 1.2f});
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(giftInfo.e, View.SCALE_X, new float[]{1.04f, 1.0f});
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(giftInfo.e, View.SCALE_Y, new float[]{1.2f, 1.0f});
        new AnimatorSet().playTogether(new Animator[]{ofFloat, ofFloat2});
        new AnimatorSet().playTogether(new Animator[]{ofFloat3, ofFloat4});
        new AnimatorSet().playTogether(new Animator[]{ofFloat5, ofFloat6});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(50);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playSequentially(new Animator[]{r9, r3, r4});
        animationSet.setAnimationListener(new dy(this, giftInfo, translateAnimation));
        translateAnimation.setAnimationListener(new dz(this, animatorSet, giftInfo, animationSet2));
        animationSet2.setAnimationListener(new ea(this, giftInfo));
        if (z) {
            giftInfo.startAnimation(translateAnimation);
        } else {
            giftInfo.startAnimation(animationSet);
        }
    }

    private GiftInfo a(long j, long j2) {
        int i = 0;
        int i2 = 0;
        GiftInfo giftInfo = null;
        while (i < getChildCount()) {
            int i3;
            GiftInfo giftInfo2 = (GiftInfo) getChildAt(i);
            if (giftInfo2 == null || giftInfo2.isAvailable) {
                i3 = i2;
            } else if (giftInfo2.j.getUserId() == j && giftInfo2.j.getGiftId() == j2) {
                return giftInfo2;
            } else {
                if (!(giftInfo2.j.getUserId() == j && giftInfo == null)) {
                    giftInfo2 = giftInfo;
                }
                giftInfo = giftInfo2;
                i3 = i2 + 1;
            }
            i++;
            i2 = i3;
        }
        if (i2 == getChildCount()) {
            return giftInfo;
        }
        return null;
    }

    private GiftInfo getAvailableGiftInfo() {
        for (int i = 0; i < getChildCount(); i++) {
            GiftInfo giftInfo = (GiftInfo) getChildAt(i);
            if (giftInfo != null && giftInfo.isAvailable) {
                return giftInfo;
            }
        }
        return null;
    }

    public GiftInfo setGift() {
        View giftInfo = new GiftInfo(this, getContext());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, WindowUtils.dp2Px(60));
        layoutParams.addRule(9, -1);
        int i = this.b + 1;
        this.b = i;
        giftInfo.setId(i % this.c);
        if (getChildCount() != 0) {
            layoutParams.addRule(2, getChildAt(getChildCount() - 1).getId());
            giftInfo.requestLayout();
            layoutParams.setMargins(0, 0, 0, 8);
            LogUtils.d(this.a, "view的个数" + getChildCount());
        } else {
            layoutParams.addRule(12, -1);
        }
        addView(giftInfo, layoutParams);
        return giftInfo;
    }

    public void releaseResource() {
        this.h = null;
        this.d.clear();
        removeAllViews();
    }

    public void hideGiftCount(boolean z) {
        this.e = z;
    }
}
