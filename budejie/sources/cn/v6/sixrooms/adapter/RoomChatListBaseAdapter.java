package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.v6.sixrooms.bean.UserInfoBean;
import java.util.List;

public abstract class RoomChatListBaseAdapter extends BaseAdapter {
    public List<UserInfoBean> allList;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public abstract View getView(int i, View view, ViewGroup viewGroup);

    public RoomChatListBaseAdapter(List<UserInfoBean> list, Context context) {
        this.allList = list;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mContext = context;
    }

    public void setData(List<UserInfoBean> list) {
        this.allList = list;
    }

    public List<UserInfoBean> getData() {
        return this.allList;
    }

    public int getCount() {
        return this.allList.size();
    }

    public Object getItem(int i) {
        return this.allList.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }
}
