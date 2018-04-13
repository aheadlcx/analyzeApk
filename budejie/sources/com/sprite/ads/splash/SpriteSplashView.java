package com.sprite.ads.splash;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.c.b;
import com.sprite.ads.R;
import com.sprite.ads.internal.a.c.a;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.imageCache.AdImageLoader;
import com.sprite.ads.internal.imageCache.AdImageLoadingListener;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.nati.reporter.SelfReporter;
import pl.droidsonroids.gif.GifDrawable;

public class SpriteSplashView extends RelativeLayout implements OnClickListener, SplashADListener {
    private long countDownInterval;
    private CountDownTimer countDownTimer;
    private Activity mActivity;
    private ADConfig mAdConfig;
    private AdItem mAdItem;
    private boolean mClicked;
    private Handler mHandler;
    private SplashADListener mListener;
    private ViewGroup mParentLayout;
    private long millisInFuture;
    private SelfReporter reporter;

    public static class Builder {
        public Activity activity;
        public ADConfig adConfig;
        public AdItem adItem;
        public SplashADListener listener;
        public ViewGroup parentLayout;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public SpriteSplashView build() {
            return new SpriteSplashView(this);
        }

        public Builder setConfig(ADConfig aDConfig) {
            this.adConfig = aDConfig;
            return this;
        }

        public Builder setData(AdItem adItem) {
            this.adItem = adItem;
            return this;
        }

        public Builder setParentLayout(ViewGroup viewGroup) {
            this.parentLayout = viewGroup;
            return this;
        }

        public Builder setSplashADListener(SplashADListener splashADListener) {
            this.listener = splashADListener;
            return this;
        }
    }

    public SpriteSplashView(Context context) {
        super(context);
        this.millisInFuture = 5000;
        this.countDownInterval = 1000;
        this.mClicked = false;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public SpriteSplashView(Builder builder) {
        this(builder.activity);
        this.mActivity = builder.activity;
        this.mListener = builder.listener;
        this.mParentLayout = builder.parentLayout;
        this.mAdItem = builder.adItem;
        this.mAdConfig = builder.adConfig;
        this.reporter = new SelfReporter(this.mAdItem);
        this.reporter.setDownwarn(this.mAdItem.downwarn);
    }

    private void addSkipView() {
        View.inflate(this.mActivity, R.layout.skip_ad_button_view, this);
        ((TextView) findViewById(R.id.skipButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SpriteSplashView.this.countDownTimer != null) {
                    SpriteSplashView.this.countDownTimer.cancel();
                }
                SpriteSplashView.this.onADDismissed();
            }
        });
    }

    public void onADDismissed() {
        if (this.mListener != null) {
            this.mListener.onADDismissed();
        }
        setVisibility(8);
    }

    public void onADPresent(AdItem adItem) {
        if (this.mListener != null) {
            this.mListener.onADPresent(adItem);
        }
    }

    public void onADSkip(AdItem adItem) {
        if (this.mListener != null) {
            this.mListener.onADSkip(adItem);
        }
    }

    public void onClick(View view) {
        if (!this.mClicked) {
            this.mClicked = true;
            if (this.countDownTimer != null) {
                this.countDownTimer.cancel();
            }
            this.reporter.onClicked(view, new a() {
                public void onClick() {
                    SpriteSplashView.this.onADSkip(SpriteSplashView.this.mAdItem);
                }

                public void onDismiss() {
                    SpriteSplashView.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            SpriteSplashView.this.onADDismissed();
                        }
                    }, 1000);
                }

                public void onPositive() {
                }
            });
        }
    }

    public void onNoAD(int i) {
        if (this.mListener != null) {
            this.mListener.onNoAD(i);
        }
        setVisibility(8);
    }

    public void onWindowFocusChanged(boolean z) {
        if (z && this.mClicked) {
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    SpriteSplashView.this.onADDismissed();
                }
            }, 1000);
        }
        ADLog.d("onWindowFocusChanged hasWindowFocus:" + z);
    }

    public void show() {
        this.reporter.onExposured(this);
        this.mParentLayout.addView(this, -1, -1);
        View imageView = new ImageView(this.mActivity);
        imageView.setOnClickListener(this);
        imageView.setScaleType(ScaleType.FIT_XY);
        addView(imageView, -1, -1);
        AdImageLoader.getInstance().displayImage(this.mAdItem.getPic(), new b(imageView, false), AdImageLoader.getInstance().SpritedisplayImageOptions(), new AdImageLoadingListener() {
            public void onLoadingCancelled(String str, View view) {
                SpriteSplashView.this.onNoAD(14);
            }

            public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
                SpriteSplashView.this.addSkipView();
                SpriteSplashView.this.onADPresent(SpriteSplashView.this.mAdItem);
            }

            public void onLoadingFailed(String str, View view, FailReason failReason) {
                SpriteSplashView.this.onNoAD(14);
            }

            public void onLoadingStarted(String str, View view) {
            }
        });
        this.countDownTimer = new CountDownTimer(this.millisInFuture, this.countDownInterval) {
            public void onFinish() {
                if (!SpriteSplashView.this.mClicked) {
                    SpriteSplashView.this.onADDismissed();
                }
            }

            public void onTick(long j) {
                ADLog.d("开屏倒计时:" + (j / 1000));
            }
        }.start();
    }
}
