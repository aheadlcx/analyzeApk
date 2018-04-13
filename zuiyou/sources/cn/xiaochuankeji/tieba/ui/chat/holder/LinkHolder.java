package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSONObject;
import rx.b.b;

public class LinkHolder extends a {
    @BindView
    WebImageView avatar;
    @BindView
    View click_area;
    @BindView
    TextView content;
    @BindView
    WebImageView thumb;
    @BindView
    TextView title;

    public LinkHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(a aVar, int i) {
        a(aVar, i, this.avatar);
        Object a = a(aVar.f);
        if (a instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) a;
            Object string = jSONObject.getString("title");
            String string2 = jSONObject.getString("link");
            String string3 = jSONObject.getString("img");
            CharSequence string4 = jSONObject.getString("msg");
            if (TextUtils.isEmpty(string4)) {
                this.content.setVisibility(8);
            } else {
                this.content.setVisibility(0);
                this.content.setText(string4);
            }
            this.thumb.setImageURI(string3);
            this.title.setText(string);
            a(this.click_area, new c(this, string, string2));
            return;
        }
        if (a == null) {
            this.content.setVisibility(8);
        } else {
            this.content.setVisibility(0);
            this.content.setText(String.valueOf(a));
        }
        a(this.click_area, new b<Void>(this) {
            final /* synthetic */ LinkHolder a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
            }
        });
    }
}
