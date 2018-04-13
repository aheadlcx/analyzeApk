package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.htjyb.ui.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Subject;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.post.s;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.post.SubjectActivity;
import cn.xiaochuankeji.tieba.ui.widget.customtv.EllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.k;
import cn.xiaochuankeji.tieba.ui.widget.k.b;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.util.ArrayList;
import org.greenrobot.eventbus.c;
import org.json.JSONObject;

public class SubjectItem extends j implements a {
    private Subject a;
    @BindView
    AppCompatImageView close;
    @BindView
    WebImageView cover;
    @BindView
    AppCompatImageView subject_cover;
    @BindView
    EllipsisTextView title;
    @BindView
    View view_divide;

    public SubjectItem(Context context) {
        super(context);
    }

    protected View a(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.view_item_subject, null);
        ButterKnife.a(this, inflate);
        return inflate;
    }

    @OnClick
    public void close() {
        k kVar = new k(e_());
        kVar.a(s.a(), new b(this) {
            final /* synthetic */ SubjectItem a;

            {
                this.a = r1;
            }

            public void a(ArrayList<String> arrayList, String str) {
                MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_DELETE);
                messageEvent.setData(this.a.a);
                c.a().d(messageEvent);
                this.a.a((ArrayList) arrayList);
            }
        });
        kVar.show();
        h.a(e_(), "zy_event_theme", "tag3");
    }

    protected void a(View view) {
        d();
    }

    @OnClick
    public void openSubject() {
        if (this.a != null) {
            Context e_ = e_();
            Intent intent = new Intent(e_, SubjectActivity.class);
            if (!(e_ instanceof Activity)) {
                intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            }
            Parcelable a = cn.xiaochuan.jsbridge.b.a(this.a.title, this.a.url);
            a.d = this.a.cover_id;
            a.a = this.a.id;
            intent.putExtra("web_data", a);
            e_.startActivity(intent);
            h.a(e_(), "zy_event_theme", "tag2");
        }
    }

    public void c() {
    }

    private void d() {
    }

    private void a(ArrayList<String> arrayList) {
        new s(s.a(this.a.id, cn.xiaochuankeji.tieba.background.a.g().q().getId(), arrayList), new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ SubjectItem a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ SubjectItem a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }
}
