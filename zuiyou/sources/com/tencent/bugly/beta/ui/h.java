package com.tencent.bugly.beta.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.download.a;
import com.tencent.bugly.beta.global.ResBean;
import com.tencent.bugly.beta.global.b;
import com.tencent.bugly.beta.global.d;
import com.tencent.bugly.beta.global.e;
import com.tencent.bugly.beta.global.f;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.y;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class h extends a {
    public static h v = new h();
    DownloadListener A = new a(2, new Object[]{this});
    private d B = null;
    protected TextView n;
    protected TextView o;
    public y p;
    public DownloadTask q;
    public Runnable r;
    public Runnable s;
    protected Bitmap t = null;
    public BitmapDrawable u;
    public UILifecycleListener w;
    OnClickListener x = new b(4, new Object[]{this});
    OnClickListener y = new b(5, new Object[]{this});
    OnClickListener z = new b(6, new Object[]{this});

    public void onStart() {
        super.onStart();
        if (this.w != null) {
            this.w.onStart(this.a, this.b, this.p != null ? new UpgradeInfo(this.p) : null);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.l != 0) {
            this.n = (TextView) onCreateView.findViewWithTag(Beta.TAG_UPGRADE_INFO);
            this.o = (TextView) onCreateView.findViewWithTag(Beta.TAG_UPGRADE_FEATURE);
        } else {
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            View linearLayout = new LinearLayout(this.a);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(1);
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
            ResBean resBean = ResBean.a;
            if (e.E.T) {
                this.n = new TextView(this.a);
                this.n.setLayoutParams(layoutParams);
                TextView textView = this.n;
                resBean.getClass();
                textView.setTextColor(Color.parseColor("#757575"));
                this.n.setTextSize((float) 14);
                this.n.setTag(Beta.TAG_UPGRADE_INFO);
                this.n.setLineSpacing(15.0f, 1.0f);
                linearLayout.addView(this.n);
            }
            View textView2 = new TextView(this.a);
            textView2.setLayoutParams(layoutParams);
            resBean.getClass();
            textView2.setTextColor(Color.parseColor("#273238"));
            textView2.setTextSize((float) 14);
            textView2.setSingleLine();
            textView2.setEllipsize(TruncateAt.END);
            textView2.setText(String.valueOf(Beta.strUpgradeDialogFeatureLabel + ": "));
            textView2.setPadding(0, com.tencent.bugly.beta.global.a.a(this.a, 8.0f), 0, 0);
            linearLayout.addView(textView2);
            this.o = new TextView(this.a);
            this.o.setLayoutParams(layoutParams);
            TextView textView3 = this.o;
            resBean.getClass();
            textView3.setTextColor(Color.parseColor("#273238"));
            this.o.setTextSize((float) 14);
            this.o.setTag(Beta.TAG_UPGRADE_FEATURE);
            this.o.setMaxHeight(com.tencent.bugly.beta.global.a.a(this.a, 200.0f));
            this.o.setLineSpacing(15.0f, 1.0f);
            linearLayout.addView(this.o);
            this.i.addView(linearLayout);
        }
        if (this.w != null) {
            this.w.onCreate(getActivity(), onCreateView, this.p != null ? new UpgradeInfo(this.p) : null);
        }
        return onCreateView;
    }

    public void onDestroyView() {
        Object obj = null;
        try {
            super.onDestroyView();
            this.n = null;
            this.o = null;
            synchronized (this) {
                this.B = null;
            }
            if (this.u != null) {
                this.u.setCallback(null);
            }
            if (this.w != null) {
                UILifecycleListener uILifecycleListener = this.w;
                Context context = this.a;
                View view = this.b;
                if (this.p != null) {
                    obj = new UpgradeInfo(this.p);
                }
                uILifecycleListener.onDestroy(context, view, obj);
            }
        } catch (Exception e) {
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.l = e.E.i;
        this.w = e.E.k;
        try {
            this.k = Integer.parseInt(ResBean.a.a("VAL_style"));
        } catch (Exception e) {
            an.a(e.getMessage(), new Object[0]);
            this.k = 0;
        }
    }

    public void a(DownloadTask downloadTask) {
        String str;
        OnClickListener onClickListener;
        String str2 = "";
        switch (downloadTask.getStatus()) {
            case 0:
            case 4:
                str = Beta.strUpgradeDialogUpgradeBtn;
                onClickListener = this.x;
                break;
            case 1:
                str = Beta.strUpgradeDialogInstallBtn;
                onClickListener = this.x;
                break;
            case 2:
                str = String.format(Locale.getDefault(), "%.1f%%", new Object[]{Float.valueOf((((float) downloadTask.getSavedLength()) / ((float) downloadTask.getTotalLength())) * 100.0f)});
                onClickListener = this.y;
                break;
            case 3:
                str = Beta.strUpgradeDialogContinueBtn;
                onClickListener = this.x;
                break;
            case 5:
                str = Beta.strUpgradeDialogRetryBtn;
                onClickListener = this.x;
                break;
            default:
                str = str2;
                onClickListener = null;
                break;
        }
        if (this.p.g != (byte) 2) {
            a(Beta.strUpgradeDialogCancelBtn, this.z, str, onClickListener);
        } else {
            a(null, null, str, onClickListener);
        }
    }

    public synchronized void c() {
        Bitmap bitmap = null;
        synchronized (this) {
            try {
                if (!(this.b == null || this.p == null || this.q == null)) {
                    CharSequence substring;
                    if (this.l != 0) {
                        this.f.setText(this.p.a);
                        if (this.e != null) {
                            this.e.setAdjustViewBounds(true);
                            if (this.k != 0) {
                                this.t = com.tencent.bugly.beta.global.a.a(this.a, 0, this.j.a("IMG_title"));
                                this.u = null;
                                if (this.t != null) {
                                    bitmap = this.t;
                                } else if (e.E.h != 0) {
                                    bitmap = com.tencent.bugly.beta.global.a.a(this.a, 1, Integer.valueOf(e.E.h));
                                }
                                this.e.setImageBitmap(bitmap);
                            }
                        }
                    } else if (this.k != 0) {
                        this.t = com.tencent.bugly.beta.global.a.a(this.a, 0, this.j.a("IMG_title"));
                        this.u = null;
                        if (this.t != null) {
                            bitmap = this.t;
                        } else if (e.E.h != 0) {
                            bitmap = com.tencent.bugly.beta.global.a.a(this.a, 1, Integer.valueOf(e.E.h));
                        }
                        this.f.getViewTreeObserver().removeOnPreDrawListener(this.B);
                        this.B = new d(1, new Object[]{this, this.f, bitmap, Integer.valueOf(this.k)});
                        this.f.getViewTreeObserver().addOnPreDrawListener(this.B);
                    } else {
                        this.f.setHeight(com.tencent.bugly.beta.global.a.a(this.a, 42.0f));
                        this.f.setText(this.p.a);
                    }
                    TextView textView = this.o;
                    if (this.p.b.length() > 500) {
                        substring = this.p.b.substring(0, 500);
                    } else {
                        substring = this.p.b;
                    }
                    textView.setText(substring);
                    if (e.E.T) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(Beta.strUpgradeDialogVersionLabel).append(": ").append(this.p.e.d).append("\n");
                        stringBuilder.append(Beta.strUpgradeDialogFileSizeLabel).append(": ");
                        float f = (float) this.p.f.d;
                        if (f >= 1048576.0f) {
                            stringBuilder.append(String.format(Locale.getDefault(), "%.1f", new Object[]{Float.valueOf(f / 1048576.0f)}));
                            stringBuilder.append("M");
                        } else if (f >= 1024.0f) {
                            stringBuilder.append(String.format(Locale.getDefault(), "%.1f", new Object[]{Float.valueOf(f / 1024.0f)}));
                            stringBuilder.append("K");
                        } else {
                            stringBuilder.append(String.format(Locale.getDefault(), "%.1f", new Object[]{Float.valueOf(f)}));
                            stringBuilder.append("B");
                        }
                        stringBuilder.append("\n");
                        stringBuilder.append(Beta.strUpgradeDialogUpdateTimeLabel).append(": ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(new Date(this.p.o)));
                        this.n.setText(stringBuilder);
                    }
                    a(this.q);
                }
            } catch (Throwable e) {
                if (this.l != 0) {
                    an.e("please confirm your argument: [Beta.upgradeDialogLayoutId] is correct", new Object[0]);
                }
                if (!an.b(e)) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void a(y yVar, DownloadTask downloadTask) {
        this.p = yVar;
        this.q = downloadTask;
        this.q.addListener(this.A);
        com.tencent.bugly.beta.utils.e.a(new d(7, this));
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        if (this.p.g != (byte) 2) {
            if (this.s != null) {
                this.s.run();
            }
            a();
        }
        return true;
    }

    public void onPause() {
        super.onPause();
        if (!(this.A == null || this.q == null)) {
            this.q.removeListener(this.A);
        }
        if (this.w != null) {
            this.w.onPause(this.a, this.b, this.p != null ? new UpgradeInfo(this.p) : null);
        }
    }

    public void onResume() {
        super.onResume();
        if (!(this.A == null || this.q == null)) {
            this.q.addListener(this.A);
        }
        c();
        if (this.k != 0 && this.t == null) {
            f.a.a(new d(7, this));
        }
        if (this.w != null) {
            this.w.onResume(this.a, this.b, this.p != null ? new UpgradeInfo(this.p) : null);
        }
    }

    public void onStop() {
        super.onStop();
        if (this.w != null) {
            this.w.onStop(this.a, this.b, this.p != null ? new UpgradeInfo(this.p) : null);
        }
    }
}
