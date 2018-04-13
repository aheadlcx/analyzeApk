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
import cn.xiaochuankeji.tieba.push.a.c;
import cn.xiaochuankeji.tieba.push.b.e;
import cn.xiaochuankeji.tieba.push.d;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.aiui.AIUIConstant;
import java.io.File;
import java.util.List;
import rx.b.b;

public class SelfVoiceHolder extends a {
    @BindView
    WebImageView avatar;
    @BindView
    View container;
    @BindView
    AppCompatTextView duration;
    @BindView
    View progress;
    @BindView
    View resend;
    @BindView
    View voice_buffering;
    @BindView
    View voice_container;
    @BindView
    AppCompatImageView voice_status;

    public SelfVoiceHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(final a aVar, int i) {
        if (this.a.isAnonymous()) {
            this.avatar.setImageResource(R.drawable.default_me);
        } else {
            a(aVar, i, this.avatar);
        }
        a(this.avatar, new b(this, this.a.session_type, aVar.a, aVar.c, aVar.e));
        a(this.resend, new b<Void>(this) {
            final /* synthetic */ SelfVoiceHolder b;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                aVar.h = 1;
                this.b.b(aVar.h);
                e.a(this.b.a, aVar, aVar.j);
                d.a().a(this.b.a, aVar, new c(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a(long j, a aVar) {
                        if (aVar.equals(aVar)) {
                            aVar.h = aVar.h;
                            this.a.b.b(aVar.h);
                        }
                    }

                    public void b(long j, a aVar) {
                        if (aVar.equals(aVar)) {
                            aVar.h = aVar.h;
                            this.a.b.b(aVar.h);
                        }
                    }
                });
            }
        });
        b(aVar.h);
        Object a = a(aVar.f);
        if (a instanceof JSONObject) {
            long longValue = ((JSONObject) a).getLongValue("duration");
            a(longValue, this.voice_container);
            a(this.container, aVar, this.duration, longValue, this.voice_status);
        }
        b(this.container, new a(this, aVar, this.container.getContext()));
    }

    private void a(View view, final a aVar, AppCompatTextView appCompatTextView, final long j, AppCompatImageView appCompatImageView) {
        appCompatTextView.setText(String.format("%d''", new Object[]{Integer.valueOf((int) Math.ceil((double) (((float) j) / 1000.0f)))}));
        if (this.c.a(aVar.j)) {
            b();
        } else {
            appCompatImageView.setImageResource(R.drawable.me_message_sound_3);
        }
        a(view, new b<Void>(this) {
            final /* synthetic */ SelfVoiceHolder c;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                CharSequence charSequence;
                String str = null;
                Object a = this.c.a(aVar.f);
                if (a instanceof JSONObject) {
                    JSONObject jSONObject = (JSONObject) a;
                    CharSequence string = jSONObject.getString("url");
                    String string2 = jSONObject.getString(AIUIConstant.RES_TYPE_PATH);
                    if (!(!TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || new File(string2).exists())) {
                        Object string3 = jSONObject.getString("uri");
                        if (!TextUtils.isEmpty(string3)) {
                            List d = cn.xiaochuankeji.tieba.network.e.a().d();
                            if (!(d == null || d.isEmpty())) {
                                charSequence = "http://" + ((cn.xiaochuankeji.tieba.network.e.a) d.get(0)).a() + "/" + string3;
                            }
                        }
                    }
                    charSequence = string;
                    str = string2;
                } else {
                    charSequence = null;
                }
                if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(charSequence)) {
                    this.c.a();
                    if (TextUtils.isEmpty(str)) {
                        CharSequence charSequence2 = charSequence;
                    }
                    cn.xiaochuankeji.tieba.ui.voice.c.a aVar = new cn.xiaochuankeji.tieba.ui.voice.c.a(str, aVar.j);
                    aVar.b = j;
                    this.c.c.a(new cn.xiaochuankeji.tieba.ui.chat.a.c.a(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void a(cn.xiaochuankeji.tieba.ui.voice.c.a aVar) {
                            this.a.c.c();
                        }

                        public void a() {
                        }

                        public void b() {
                            this.a.c.b();
                        }
                    });
                    this.c.c.a(aVar);
                }
            }
        });
    }

    private void a() {
        this.voice_status.setVisibility(8);
        this.voice_buffering.setVisibility(0);
        Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(800);
        rotateAnimation.setRepeatCount(-1);
        this.voice_buffering.startAnimation(rotateAnimation);
    }

    private void b() {
        this.voice_buffering.clearAnimation();
        this.voice_buffering.setVisibility(8);
        this.voice_status.setVisibility(0);
        this.voice_status.setImageResource(R.drawable.chat_me_voice);
        Drawable drawable = this.voice_status.getDrawable();
        if (drawable instanceof AnimationDrawable) {
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
        this.voice_status.setImageResource(R.drawable.me_message_sound_3);
    }

    public void b(int i) {
        if (i == 0) {
            this.progress.setVisibility(8);
            this.resend.setVisibility(8);
        } else if (i == 1) {
            this.progress.setVisibility(0);
            this.resend.setVisibility(8);
        } else if (i == 2) {
            this.progress.setVisibility(8);
            this.resend.setVisibility(0);
        }
    }
}
