package com.budejie.www.adapter.g.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.androidex.widget.RoundAsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.DingTopicListActivity;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.HeadPortraitItem;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.an;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;

public class w extends a<ListItemObject> {
    private int[] e = new int[]{R.id.portrait0, R.id.portrait1, R.id.portrait2, R.id.portrait3, R.id.portrait4};
    private View f;
    private TextView g;

    public w(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.comment_item_head_portrait, viewGroup);
        this.f = inflate.findViewById(R.id.praise_header_wall_layout);
        this.g = (TextView) inflate.findViewById(R.id.tv_praise_num);
        return inflate;
    }

    public void c() {
        ArrayList headPortraitItems = ((ListItemObject) this.c).getHeadPortraitItems();
        if (headPortraitItems == null || headPortraitItems.size() <= 0) {
            this.f.setVisibility(8);
            return;
        }
        this.f.setVisibility(0);
        this.g.setOnClickListener(this);
        for (int i = 0; i < headPortraitItems.size(); i++) {
            RoundAsyncImageView roundAsyncImageView = (RoundAsyncImageView) this.f.findViewById(this.e[i]);
            roundAsyncImageView.setVisibility(0);
            HeadPortraitItem headPortraitItem = (HeadPortraitItem) headPortraitItems.get(i);
            roundAsyncImageView.setAsyncCacheImage(headPortraitItem.getProfile_image(), "f".equals(headPortraitItem.getSex()) ? R.color.head_portrait_female_round : R.color.head_portrait_male_round);
            final Object userid = headPortraitItem.getUserid();
            if (!TextUtils.isEmpty(userid)) {
                roundAsyncImageView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ w b;

                    public void onClick(View view) {
                        an.b((Activity) this.b.a, userid);
                    }
                });
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.tv_praise_num) {
            Log.i("CommendDetail", "点击了更多点赞的人");
            MobclickAgent.onEvent(this.a, "E02-A05", "更多点赞的人");
            Intent intent = new Intent(this.a, DingTopicListActivity.class);
            if (an.a(this.a.getSharedPreferences("weiboprefer", 0))) {
                intent.putExtra("dataid", ((ListItemObject) this.c).getWid());
                this.a.startActivity(intent);
                return;
            }
            an.a((Activity) this.a, intent);
        }
    }
}
