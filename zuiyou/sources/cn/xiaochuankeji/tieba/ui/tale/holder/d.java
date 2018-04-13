package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuan.jsbridge.b;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.webview.WebActivity;

public class d extends b {
    public d(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(TaleComment taleComment, int i) {
        this.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                WebActivity.a(this.a.itemView.getContext(), b.a(null, a.d("https://$$/help/theme/describe")));
            }
        });
    }
}
