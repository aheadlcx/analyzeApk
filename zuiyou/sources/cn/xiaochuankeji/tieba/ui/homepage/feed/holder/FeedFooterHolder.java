package cn.xiaochuankeji.tieba.ui.homepage.feed.holder;

import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import com.jakewharton.a.b.a;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.c;
import rx.b.b;

public class FeedFooterHolder extends b<FeedFooterHolder> {
    @BindView
    View reload;

    public FeedFooterHolder(View view) {
        super(view);
        ButterKnife.a(this, view);
    }

    public void a(FeedFooterHolder feedFooterHolder, int i, MemberInfoBean memberInfoBean) {
        a.a(feedFooterHolder.reload).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new b<Void>(this) {
            final /* synthetic */ FeedFooterHolder a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                c.a().d(new cn.xiaochuankeji.tieba.ui.homepage.feed.b.a());
            }
        });
    }
}
