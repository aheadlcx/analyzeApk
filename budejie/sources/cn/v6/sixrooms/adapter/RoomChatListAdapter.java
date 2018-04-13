package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserInfoBean;
import java.util.List;

public class RoomChatListAdapter extends RoomChatListBaseAdapter {

    static class a {
        public TextView a;

        a() {
        }
    }

    public RoomChatListAdapter(List<UserInfoBean> list, Context context) {
        super(list, context);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        UserInfoBean userInfoBean = (UserInfoBean) this.allList.get(i);
        if (view == null) {
            view = this.mInflater.inflate(R.layout.phone_room_chat_list_item, null);
            a aVar2 = new a();
            aVar2.a = (TextView) view.findViewById(R.id.tv_username);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(userInfoBean.getUname());
        return view;
    }
}
