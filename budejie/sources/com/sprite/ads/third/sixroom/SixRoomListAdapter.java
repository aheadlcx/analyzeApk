package com.sprite.ads.third.sixroom;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sprite.ads.R;
import com.sprite.ads.internal.bean.data.LiveItem;
import com.sprite.ads.internal.log.ADLog;
import java.util.List;

public class SixRoomListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    Context mContext;
    List<LiveItem> mLiveItems;

    class ViewHolder {
        public ImageView pic;
        public TextView userCount;
        public TextView username;

        ViewHolder() {
        }
    }

    public SixRoomListAdapter(Context context, List<LiveItem> list) {
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mLiveItems = list;
    }

    public int getCount() {
        return this.mLiveItems.size();
    }

    public Object getItem(int i) {
        return this.mLiveItems.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        LiveItem liveItem = (LiveItem) getItem(i);
        if (view == null) {
            view = this.inflater.inflate(R.layout.ad_six_rooms_item, null);
            ViewHolder viewHolder2 = new ViewHolder();
            viewHolder2.userCount = (TextView) view.findViewById(R.id.ad_six_room_user_count);
            viewHolder2.username = (TextView) view.findViewById(R.id.ad_six_room_username);
            viewHolder2.pic = (ImageView) view.findViewById(R.id.ad_six_room_pic);
            view.setTag(viewHolder2);
            viewHolder = viewHolder2;
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.userCount.setText(liveItem.count + "人正在看");
        viewHolder.username.setText(liveItem.username);
        if (TextUtils.isEmpty(liveItem.pospic)) {
            Object obj = liveItem.pic;
        } else {
            String str = liveItem.pospic;
        }
        if (TextUtils.isEmpty(obj) || !(this.mContext instanceof Activity) || ((Activity) this.mContext).isFinishing()) {
            viewHolder.pic.setImageURI(Uri.parse(""));
            ADLog.d("spritead", "六间房直播，Glide未能加载图片，当前上下文为：" + this.mContext.toString() + " 是否正在关闭：" + ((Activity) this.mContext).isFinishing());
        } else {
            i.b(this.mContext).a(obj).a(DiskCacheStrategy.SOURCE).a().a(viewHolder.pic);
        }
        return view;
    }

    public void setLiveItems(List<LiveItem> list) {
        this.mLiveItems = list;
    }
}
