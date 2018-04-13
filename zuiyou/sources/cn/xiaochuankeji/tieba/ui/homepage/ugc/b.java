package cn.xiaochuankeji.tieba.ui.homepage.ugc;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.tieba.background.utils.h;
import org.greenrobot.eventbus.c;

public class b extends ViewHolder {
    public b(View view) {
        super(view);
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                c.a().d(new a());
                h.a("zy_event_ugcvideo_recommend", "点击蓝条刷新次数");
            }
        });
    }
}
