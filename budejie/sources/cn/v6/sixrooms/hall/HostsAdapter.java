package cn.v6.sixrooms.hall;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import cn.v6.sixrooms.R;
import com.c.a.c;
import com.c.b;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class HostsAdapter extends b<LiveItemBean> {
    private final String a;
    private List<LiveItemBean> b;
    private a c;

    interface a {
        void a(LiveItemBean liveItemBean);
    }

    HostsAdapter(String str, Context context, List<LiveItemBean> list, a aVar) {
        super(context, R.layout.phone_item_hosts, list);
        this.mContext = context;
        this.b = list;
        this.c = aVar;
        this.a = str;
    }

    protected void convert(c cVar, LiveItemBean liveItemBean, int i) {
        if (TextUtils.isEmpty(liveItemBean.getPospic())) {
            ((SimpleDraweeView) cVar.a(R.id.simpleDraweeView)).setImageResource(R.drawable.phone_hall_def_common_bg);
        } else {
            ((SimpleDraweeView) cVar.a(R.id.simpleDraweeView)).setImageURI(Uri.parse(liveItemBean.getPospic()));
        }
        cVar.a(R.id.numTv, String.format("%s人", new Object[]{liveItemBean.getCount()}));
        cVar.a(R.id.nameTv, liveItemBean.getUsername());
        if (!TextUtils.isEmpty(liveItemBean.getTagname())) {
            if (cVar.a(R.id.tagTv).getVisibility() == 8) {
                cVar.a(R.id.tagTv).setVisibility(0);
            }
            cVar.a(R.id.tagTv, liveItemBean.getTagname());
        } else if (cVar.a(R.id.tagTv).getVisibility() == 0) {
            cVar.a(R.id.tagTv).setVisibility(8);
        }
        cVar.a().setOnClickListener(new m(this, cVar));
    }
}
