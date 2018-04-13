package com.r0adkll.slidr;

import android.animation.ArgbEvaluator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrInterface;
import com.r0adkll.slidr.widget.SliderPanel;
import com.r0adkll.slidr.widget.SliderPanel.OnPanelSlideListener;

public class Slidr {

    /* renamed from: com.r0adkll.slidr.Slidr$1 */
    final class AnonymousClass1 implements OnPanelSlideListener {
        private final ArgbEvaluator mEvaluator = new ArgbEvaluator();
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ int val$statusBarColor1;
        final /* synthetic */ int val$statusBarColor2;

        AnonymousClass1(Activity activity, int i, int i2) {
            this.val$activity = activity;
            this.val$statusBarColor1 = i;
            this.val$statusBarColor2 = i2;
        }

        public void onStateChanged(int i) {
        }

        public void onClosed() {
            this.val$activity.finish();
            this.val$activity.overridePendingTransition(0, 0);
        }

        public void onOpened() {
        }

        @TargetApi(21)
        public void onSlideChange(float f) {
            if (VERSION.SDK_INT >= 21 && this.val$statusBarColor1 != -1 && this.val$statusBarColor2 != -1) {
                this.val$activity.getWindow().setStatusBarColor(((Integer) this.mEvaluator.evaluate(f, Integer.valueOf(this.val$statusBarColor1), Integer.valueOf(this.val$statusBarColor2))).intValue());
            }
        }
    }

    /* renamed from: com.r0adkll.slidr.Slidr$2 */
    final class AnonymousClass2 implements OnPanelSlideListener {
        private final ArgbEvaluator mEvaluator = new ArgbEvaluator();
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ SlidrConfig val$config;

        AnonymousClass2(SlidrConfig slidrConfig, Activity activity) {
            this.val$config = slidrConfig;
            this.val$activity = activity;
        }

        public void onStateChanged(int i) {
            if (this.val$config.getListener() != null) {
                this.val$config.getListener().onSlideStateChanged(i);
            }
        }

        public void onClosed() {
            if (this.val$config.getListener() != null) {
                this.val$config.getListener().onSlideClosed();
            }
            this.val$activity.finish();
            this.val$activity.overridePendingTransition(0, 0);
        }

        public void onOpened() {
            if (this.val$config.getListener() != null) {
                this.val$config.getListener().onSlideOpened();
            }
        }

        @TargetApi(21)
        public void onSlideChange(float f) {
            if (VERSION.SDK_INT >= 21 && this.val$config.areStatusBarColorsValid()) {
                this.val$activity.getWindow().setStatusBarColor(((Integer) this.mEvaluator.evaluate(f, Integer.valueOf(this.val$config.getPrimaryColor()), Integer.valueOf(this.val$config.getSecondaryColor()))).intValue());
            }
            if (this.val$config.getListener() != null) {
                this.val$config.getListener().onSlideChange(f);
            }
        }
    }

    /* renamed from: com.r0adkll.slidr.Slidr$3 */
    final class AnonymousClass3 implements SlidrInterface {
        final /* synthetic */ SliderPanel val$panel;

        AnonymousClass3(SliderPanel sliderPanel) {
            this.val$panel = sliderPanel;
        }

        public void lock() {
            this.val$panel.lock();
        }

        public void unlock() {
            this.val$panel.unlock();
        }
    }

    public static SlidrInterface attach(Activity activity) {
        return attach(activity, -1, -1);
    }

    public static SlidrInterface attach(Activity activity, int i, int i2) {
        SliderPanel initSliderPanel = initSliderPanel(activity, null);
        initSliderPanel.setOnPanelSlideListener(new AnonymousClass1(activity, i, i2));
        return initInterface(initSliderPanel);
    }

    public static SlidrInterface attach(Activity activity, SlidrConfig slidrConfig) {
        SliderPanel initSliderPanel = initSliderPanel(activity, slidrConfig);
        initSliderPanel.setOnPanelSlideListener(new AnonymousClass2(slidrConfig, activity));
        return initInterface(initSliderPanel);
    }

    private static SliderPanel initSliderPanel(Activity activity, SlidrConfig slidrConfig) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        View childAt = viewGroup.getChildAt(0);
        viewGroup.removeViewAt(0);
        View sliderPanel = new SliderPanel(activity, childAt, slidrConfig);
        sliderPanel.setId(R.id.slidable_panel);
        childAt.setId(R.id.slidable_content);
        sliderPanel.addView(childAt);
        viewGroup.addView(sliderPanel, 0);
        return sliderPanel;
    }

    private static SlidrInterface initInterface(SliderPanel sliderPanel) {
        return new AnonymousClass3(sliderPanel);
    }
}
