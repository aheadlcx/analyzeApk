package cn.xiaochuankeji.tieba.ui.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.MainActivity;
import org.greenrobot.eventbus.c;

public class g extends RelativeLayout {
    public g(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_recommend_list_load_more, this);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                h hVar = new h();
                hVar.a = "lastread";
                if (HomePageActivity.class.isInstance(this.a.getContext())) {
                    MainActivity.a("tab_home_page");
                }
                c.a().d(hVar);
            }
        });
    }
}
