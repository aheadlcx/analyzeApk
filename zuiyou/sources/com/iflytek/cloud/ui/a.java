package com.iflytek.cloud.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.resource.Resource;
import com.iflytek.cloud.thirdparty.bu;
import com.iflytek.cloud.thirdparty.bw;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.cloud.thirdparty.cg;
import com.iflytek.cloud.thirdparty.cs;
import com.iflytek.cloud.thirdparty.cv;
import com.iflytek.cloud.thirdparty.cw;
import com.tencent.wcdb.database.SQLiteDatabase;

public final class a extends cv implements OnClickListener {
    public static int a = 9;
    private LinearLayout d;
    private cw e = null;
    private RotateAnimation f = null;
    private SpeechRecognizer g;
    private RecognizerDialogListener h;
    private long i = 0;
    private RecognizerListener j = new RecognizerListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onBeginOfSpeech() {
        }

        public void onEndOfSpeech() {
            this.a.j();
        }

        public void onError(SpeechError speechError) {
            if (speechError == null || !this.a.b) {
                this.a.f();
            } else {
                this.a.a(speechError);
            }
            if (this.a.h != null) {
                this.a.h.onError(speechError);
            }
        }

        public void onEvent(int i, int i2, int i3, Bundle bundle) {
        }

        public void onResult(RecognizerResult recognizerResult, boolean z) {
            if (z) {
                this.a.f();
            }
            if (this.a.h != null) {
                this.a.h.onResult(recognizerResult, z);
            }
        }

        public void onVolumeChanged(int i, byte[] bArr) {
            if (this.a.k == 1 && this.a.e != null) {
                this.a.e.setVolume((i + 2) / 5);
                this.a.e.invalidate();
            }
        }
    };
    private volatile int k;

    public class a extends ClickableSpan {
        final /* synthetic */ a a;
        private String b;

        public a(a aVar, String str) {
            this.a = aVar;
            this.b = str;
        }

        public void onClick(View view) {
            try {
                Context context = view.getContext();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.b));
                intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                intent.putExtra("com.android.browser.application_id", context.getPackageName());
                context.startActivity(intent);
            } catch (Throwable e) {
                cb.a(e);
            }
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
        }
    }

    public a(Context context, InitListener initListener) {
        super(context.getApplicationContext());
        this.g = SpeechRecognizer.createRecognizer(context.getApplicationContext(), initListener);
        a();
    }

    private void a(SpeechError speechError) {
        try {
            LinearLayout linearLayout = (LinearLayout) this.d.findViewWithTag("error");
            a((TextView) linearLayout.findViewWithTag("errtxt"), speechError);
            linearLayout.findViewWithTag("errview").setBackgroundDrawable(cs.a(getContext(), "warning"));
            setTag(speechError);
            this.k = 3;
            k();
        } catch (Throwable e) {
            cb.a(e);
        }
    }

    private void g() {
        cb.a("startRecognizing");
        long j = this.i;
        this.i = SystemClock.elapsedRealtime();
        if (this.i - j >= 300) {
            this.g.setParameter("msc.skin", "default");
            int startListening = this.g.startListening(this.j);
            if (startListening != 0) {
                a(new SpeechError(startListening));
            } else {
                i();
            }
        }
    }

    private void h() {
        if (this.d != null) {
            this.d.destroyDrawingCache();
            this.d = null;
        }
        this.e = null;
        System.gc();
    }

    private void i() {
        if (this.e == null) {
            this.e = new cw(getContext().getApplicationContext());
        }
        this.k = 1;
        k();
    }

    private void j() {
        try {
            ((FrameLayout) this.d.findViewWithTag("waiting")).findViewWithTag("control").startAnimation(this.f);
            this.k = 2;
            k();
        } catch (Throwable e) {
            cb.a(e);
        }
    }

    private void k() {
        FrameLayout frameLayout = (FrameLayout) this.d.findViewWithTag("waiting");
        TextView textView = (TextView) this.d.findViewWithTag("title");
        LinearLayout linearLayout = (LinearLayout) this.d.findViewWithTag("error");
        TextView textView2 = (TextView) frameLayout.findViewWithTag("tips");
        if (this.k == 1) {
            linearLayout.setVisibility(8);
            textView.setVisibility(0);
            frameLayout.setVisibility(8);
            textView.setText(Resource.getTitle(2));
            this.e.setVolume(0);
            this.e.invalidate();
            this.e.setVisibility(0);
        } else if (this.k == 2) {
            textView.setVisibility(8);
            this.e.setVisibility(8);
            frameLayout.setVisibility(0);
            textView2.setVisibility(0);
            textView2.setText(Resource.getTitle(3));
        } else if (this.k == 3) {
            textView.setVisibility(8);
            this.e.setVisibility(8);
            frameLayout.setVisibility(8);
            linearLayout.setVisibility(0);
        }
    }

    public void a() {
        try {
            final Context applicationContext = getContext().getApplicationContext();
            View a = cs.a(applicationContext, "recognize", this);
            a.setBackgroundDrawable(cs.a(applicationContext.getApplicationContext(), "voice_bg.9"));
            TextView textView = (TextView) a.findViewWithTag("textlink");
            textView.getPaint().setFlags(8);
            textView.setText("语音识别能力由讯飞输入法提供");
            textView.setLinksClickable(true);
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    try {
                        Uri parse = Uri.parse("http://www.xunfei.cn/?appid=" + bu.b(cg.a()));
                        cb.a(parse.toString());
                        Intent intent = new Intent("android.intent.action.VIEW", parse);
                        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                        this.b.k = 1;
                        this.b.k();
                        this.b.g.stopListening();
                        this.b.i();
                        applicationContext.getApplicationContext().startActivity(intent);
                    } catch (Exception e) {
                        cb.e("failed");
                    }
                }
            });
            this.d = (LinearLayout) a.findViewWithTag("container");
            bw.a(this);
            this.e = new cw(applicationContext.getApplicationContext());
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
            layoutParams.bottomMargin = 20;
            this.d.addView(this.e, 1, layoutParams);
            ((FrameLayout) this.d.findViewWithTag("waiting")).findViewWithTag("control").setBackgroundDrawable(cs.a(getContext(), "waiting"));
            this.f = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            this.f.setRepeatCount(-1);
            this.f.setInterpolator(new LinearInterpolator());
            this.f.setDuration(700);
        } catch (Throwable e) {
            cb.a(e);
        }
    }

    public void a(TextView textView, SpeechError speechError) {
        String parameter = this.g.getParameter("view_tips_plain");
        boolean z = parameter != null && (parameter.equalsIgnoreCase("true") || parameter.equalsIgnoreCase("1"));
        textView.setText(Html.fromHtml(speechError.getHtmlDescription(!z)));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.bringToFront();
        CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            int length = text.length();
            Spannable spannable = (Spannable) textView.getText();
            URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, length, URLSpan.class);
            CharSequence spannableStringBuilder = new SpannableStringBuilder(text);
            spannableStringBuilder.clearSpans();
            for (URLSpan uRLSpan : uRLSpanArr) {
                spannableStringBuilder.setSpan(new a(this, uRLSpan.getURL()), spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 34);
            }
            int length2 = speechError.getHtmlDescription(false).length();
            int length3 = speechError.getHtmlDescription(true).length() - "<br>".length();
            spannableStringBuilder.setSpan(new ForegroundColorSpan(cs.a()[0]), 0, length2, 34);
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(cs.b()[0], true), 0, length2, 33);
            if (!z) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(cs.a()[1]), length2 + 1, length3 + 1, 34);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(cs.b()[1], true), length2 + 1, length, 34);
            }
            textView.setText(spannableStringBuilder);
        }
    }

    public void a(String str, String str2) {
        this.g.setParameter(str, str2);
    }

    public void b() {
        super.b();
        g();
    }

    public void c() {
        if (this.g.isListening()) {
            this.g.cancel();
        }
        super.c();
    }

    protected boolean d() {
        if (!super.d()) {
            return false;
        }
        h();
        return this.g.destroy();
    }

    public void onClick(View view) {
        switch (this.k) {
            case 1:
                this.g.stopListening();
                j();
                return;
            case 3:
                if (view.getTag() == null || ((SpeechError) view.getTag()).getErrorCode() != 20001) {
                    g();
                    return;
                } else {
                    e();
                    return;
                }
            default:
                return;
        }
    }

    public void setResultListener(RecognizerDialogListener recognizerDialogListener) {
        this.h = recognizerDialogListener;
        setOnClickListener(this);
    }

    public void setTitle(CharSequence charSequence) {
        ((TextView) this.d.findViewWithTag("title")).setText(charSequence);
    }
}
