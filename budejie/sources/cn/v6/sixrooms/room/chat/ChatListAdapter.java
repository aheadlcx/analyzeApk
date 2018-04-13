package cn.v6.sixrooms.room.chat;

import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.listener.SetClickableSpanListener;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.view.DraweeTextView;
import java.util.List;

public class ChatListAdapter extends BaseAdapter implements SetClickableSpanListener {
    private boolean a;
    private List<RoommsgBean> b;
    private BaseRoomActivity c;
    private LayoutInflater d;
    private a e;
    private String f;
    private String g;
    private CallBack h;
    private int i;
    private RoomChatStyleHandler j = new RoomChatStyleHandler(this);

    public interface CallBack {
        void onSetClickableSpan(UserInfoBean userInfoBean, String str);
    }

    private static class a {
        public DraweeTextView a;

        private a() {
        }
    }

    public ChatListAdapter(boolean z, int i, List<RoommsgBean> list, BaseRoomActivity baseRoomActivity, String str, String str2, CallBack callBack) {
        this.a = z;
        this.b = list;
        this.c = baseRoomActivity;
        this.i = i;
        this.d = (LayoutInflater) baseRoomActivity.getSystemService("layout_inflater");
        this.f = str;
        this.g = str2;
        this.h = callBack;
    }

    public String getFrid() {
        return this.f;
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.d.inflate(R.layout.phone_room_chat_item_text_full_screen, null);
            this.e = new a();
            this.e.a = (DraweeTextView) view.findViewById(R.id.phone_room_chat_item_tv);
            view.setTag(this.e);
        } else {
            this.e = (a) view.getTag();
        }
        RoommsgBean roommsgBean = (RoommsgBean) this.b.get(i);
        roommsgBean.setPosition(i);
        if (this.a) {
            roommsgBean.setWealth(0);
        }
        this.j.processBean(this.e.a, roommsgBean);
        return view;
    }

    public ClickableSpan typeClick(String str, String str2) {
        return new a(this, str, str2);
    }

    public void setClickableSpan(UserInfoBean userInfoBean, String str) {
        this.h.onSetClickableSpan(userInfoBean, str);
    }

    public BaseRoomActivity getBaseActivity() {
        return this.c;
    }

    public void setRoomType(int i) {
        this.i = i;
    }

    public int getRoomType() {
        return this.i;
    }
}
