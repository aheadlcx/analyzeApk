package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class TextHolder extends a {
    @BindView
    WebImageView avatar;
    @BindView
    TextView content;

    public TextHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(a aVar, int i) {
        a(aVar, i, this.avatar);
        this.content.setText(String.valueOf(aVar.f));
        a(this.avatar, new b(this, this.a.session_type, aVar.a, aVar.c, aVar.e));
        b(this.content, new a(this, aVar, this.content.getContext()));
    }
}
