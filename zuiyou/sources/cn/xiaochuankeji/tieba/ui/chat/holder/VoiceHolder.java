package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.chat.a.c;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.text.BadgeTextView;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.aiui.AIUIConstant;
import com.izuiyou.a.a.b;

public class VoiceHolder extends a {
    @BindView
    WebImageView avatar;
    @BindView
    BadgeTextView badge_new;
    @BindView
    View container;
    @BindView
    AppCompatTextView duration;
    @BindView
    View voice_buffering;
    @BindView
    AppCompatImageView voice_status;

    public VoiceHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(a aVar, int i) {
        a(aVar, i, this.avatar);
        a(this.avatar, new b(this, this.a.session_type, aVar.a, aVar.c, aVar.e));
        this.badge_new.setVisibility(8);
        Object a = a(aVar.f);
        if (a instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) a;
            String string = jSONObject.getString("url");
            String string2 = jSONObject.getString(AIUIConstant.RES_TYPE_PATH);
            long longValue = jSONObject.getLongValue("duration");
            String string3 = jSONObject.getString("fmt");
            a(longValue, this.container);
            a(this.container, aVar.j, string2, string, string3, this.duration, longValue);
        }
        b(this.container, new a(this, aVar, this.container.getContext()));
    }

    private void a(View view, long j, String str, String str2, String str3, AppCompatTextView appCompatTextView, long j2) {
        AppCompatTextView appCompatTextView2 = appCompatTextView;
        appCompatTextView2.setText(String.format("%d''", new Object[]{Integer.valueOf((int) Math.ceil((double) (((float) j2) / 1000.0f)))}));
        if (this.c.a(j)) {
            b();
        } else {
            this.voice_status.setImageResource(R.drawable.user_message_sound_3);
        }
        b.c("proxy playing id " + j + " " + this.c.a(j));
        final long j3 = j;
        final String str4 = str;
        final String str5 = str2;
        final long j4 = j2;
        a(view, new rx.b.b<Void>(this) {
            final /* synthetic */ VoiceHolder e;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                this.e.a();
                cn.xiaochuankeji.tieba.ui.voice.c.a aVar = new cn.xiaochuankeji.tieba.ui.voice.c.a(!TextUtils.isEmpty(str4) ? str4 : str5, j3);
                aVar.b = j4;
                this.e.c.a(new c.a(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a(cn.xiaochuankeji.tieba.ui.voice.c.a aVar) {
                        this.a.e.c();
                    }

                    public void a() {
                    }

                    public void b() {
                        this.a.e.b();
                    }
                });
                this.e.c.a(aVar);
            }
        });
    }

    private void a() {
        this.voice_status.setVisibility(8);
        this.voice_buffering.setVisibility(0);
        Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(400);
        rotateAnimation.setRepeatCount(-1);
        this.voice_buffering.startAnimation(rotateAnimation);
    }

    private void b() {
        this.voice_buffering.clearAnimation();
        this.voice_buffering.setVisibility(8);
        this.voice_status.setVisibility(0);
        this.voice_status.setImageResource(R.drawable.chat_other_voice);
        Drawable drawable = this.voice_status.getDrawable();
        if ((drawable instanceof AnimationDrawable) && !((AnimationDrawable) drawable).isRunning()) {
            drawable.setCallback(this.voice_status);
            drawable.setVisible(true, true);
            ((AnimationDrawable) drawable).start();
        }
    }

    private void c() {
        this.voice_buffering.clearAnimation();
        this.voice_buffering.setVisibility(8);
        this.voice_status.setVisibility(0);
        Drawable drawable = this.voice_status.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).stop();
        }
        this.voice_status.setImageResource(R.drawable.user_message_sound_3);
    }
}
