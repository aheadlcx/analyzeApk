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
import cn.xiaochuankeji.tieba.widget.rich.LineSpaceExtraCompatTextView;
import rx.b.b;

public class SelfTextHolder extends a {
    @BindView
    WebImageView avatar;
    @BindView
    LineSpaceExtraCompatTextView content;
    @BindView
    View progres;
    @BindView
    View resend;

    public SelfTextHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(final a aVar, int i) {
        if (this.a.isAnonymous()) {
            this.avatar.setImageResource(R.drawable.default_me);
        } else {
            a(aVar, i, this.avatar);
        }
        this.content.setText(String.valueOf(aVar.f));
        b(aVar.h);
        a(this.avatar, new b(this, this.a.session_type, aVar.a, aVar.c, aVar.e));
        a(this.resend, new b<Void>(this) {
            final /* synthetic */ SelfTextHolder b;

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
        b(this.content, new a(this, aVar, this.content.getContext()));
    }

    public void b(int i) {
        if (i == 0) {
            this.progres.setVisibility(8);
            this.resend.setVisibility(8);
        } else if (i == 1) {
            this.progres.setVisibility(0);
            this.resend.setVisibility(8);
        } else if (i == 2) {
            this.progres.setVisibility(8);
            this.resend.setVisibility(0);
        }
    }
}
