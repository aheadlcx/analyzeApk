package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class UnSupportHolder extends a {
    @BindView
    WebImageView avatar;
    @BindView
    TextView content;

    public UnSupportHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(a aVar, int i) {
        this.avatar.setWebImage(b.a(aVar.a, aVar.c));
        this.content.setText(String.valueOf(aVar.l));
        a(this.avatar, new b(this, this.a.session_type, aVar.a, aVar.c, aVar.e));
    }
}
