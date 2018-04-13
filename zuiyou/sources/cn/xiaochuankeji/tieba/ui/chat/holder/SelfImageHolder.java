package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.push.a.c;
import cn.xiaochuankeji.tieba.push.b.e;
import cn.xiaochuankeji.tieba.push.d;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.aiui.AIUIConstant;
import rx.b.b;

public class SelfImageHolder extends a {
    @BindView
    WebImageView avatar;
    @BindView
    WebImageView image;
    @BindView
    View resend;

    public SelfImageHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(final a aVar, int i) {
        if (this.a.isAnonymous()) {
            this.avatar.setImageResource(R.drawable.default_me);
        } else {
            a(aVar, i, this.avatar);
        }
        b(aVar.h);
        Object a = a(aVar.f);
        if (a instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) a;
            long longValue = jSONObject.getLongValue("id");
            String string = jSONObject.getString(AIUIConstant.RES_TYPE_PATH);
            int intValue = jSONObject.getIntValue("w");
            int intValue2 = jSONObject.getIntValue("h");
            a(this.image, jSONObject.getString("fmt"), longValue, string, (float) intValue, (float) intValue2);
        }
        a(this.avatar, new b(this, this.a.session_type, aVar.a, aVar.c, aVar.e));
        a(this.resend, new b<Void>(this) {
            final /* synthetic */ SelfImageHolder b;

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
        b(this.image, new a(this, aVar, this.image.getContext()));
    }

    public void b(int i) {
        if (i == 0) {
            this.resend.setVisibility(8);
        } else if (i == 1) {
            this.resend.setVisibility(8);
        } else if (i == 2) {
            this.resend.setVisibility(0);
        }
    }
}
