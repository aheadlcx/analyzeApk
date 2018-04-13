package com.budejie.www.adapter.g.b;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import com.budejie.www.R;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.i;
import com.budejie.www.util.an;
import com.sprite.ads.internal.bean.data.LiveItem;
import com.sprite.ads.third.sixroom.SixRoomAdData;
import com.sprite.ads.third.sixroom.SixRoomListAdapter;
import java.util.List;

public class d extends a<ListItemObject> {
    private View e;
    private GridView f;
    private SixRoomListAdapter g;
    private List<LiveItem> h;

    public d(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        this.e = View.inflate(this.a, R.layout.ad_six_rooms_list_item, viewGroup);
        int a = an.a(this.a, 9);
        int a2 = an.a(this.a, 10);
        ((RelativeLayout) this.e.findViewById(R.id.ad_six_room_container)).setPadding(a2, a, a2, 0);
        this.f = (GridView) this.e.findViewById(R.id.gv_room_list);
        this.f.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                LiveItem liveItem = (LiveItem) this.a.h.get(i);
                i.a("点击广告", "帖子流|点击直播间");
                String valueOf = String.valueOf(liveItem.uid);
                com.d.a.a((Activity) this.a.a, String.valueOf(liveItem.rid), valueOf);
            }
        });
        this.e.findViewById(R.id.ad_six_room_view_all_text).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("点击广告", "信息流|点击查看全部");
                com.d.a.a((Activity) this.a.a);
            }
        });
        return this.e;
    }

    public void c() {
        SixRoomAdData sixRoomAdData = (SixRoomAdData) ((ListItemObject) this.c).getAdItemData();
        if (sixRoomAdData != null && sixRoomAdData.getLiveItems() != null) {
            this.h = sixRoomAdData.getLiveItems();
            if (this.g == null) {
                this.g = new SixRoomListAdapter(this.a, this.h);
                this.f.setAdapter(this.g);
                return;
            }
            this.g.setLiveItems(this.h);
            this.g.notifyDataSetChanged();
        }
    }

    public void onClick(View view) {
    }
}
