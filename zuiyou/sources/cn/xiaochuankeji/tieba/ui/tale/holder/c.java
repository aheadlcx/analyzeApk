package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;

public class c extends b {
    public c(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(TaleComment taleComment, final int i) {
        this.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.tale.a.c(i));
            }
        });
    }
}
