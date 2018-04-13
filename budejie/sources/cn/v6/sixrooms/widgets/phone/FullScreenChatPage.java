package cn.v6.sixrooms.widgets.phone;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.listener.OnChatOnlickListener;
import cn.v6.sixrooms.listener.OnScrollToBottomListener;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.chat.ChatListAdapter;
import java.util.List;

public class FullScreenChatPage extends RelativeLayout implements OnClickListener {
    private boolean a;
    private BaseRoomActivity b;
    private ListView c = ((ListView) findViewById(R.id.lv_public_chat));
    private RelativeLayout d;
    private View e;
    private boolean f;
    private OnScrollToBottomListener g;
    private OnChatOnlickListener h;
    private ChatListAdapter i;
    private long j;
    private int k;
    private FullScreenChatPage$OnChatPageScrollListener l;
    private OnChatPageTouchEvent m;

    public interface OnChatPageTouchEvent {
        void onTouch(MotionEvent motionEvent);
    }

    public FullScreenChatPage(boolean z, BaseRoomActivity baseRoomActivity, List<RoommsgBean> list, String str, String str2, OnScrollToBottomListener onScrollToBottomListener) {
        super(baseRoomActivity);
        LayoutInflater.from(baseRoomActivity).inflate(R.layout.v6_phone_room_full_screen_chat_page, this, true);
        this.a = z;
        this.b = baseRoomActivity;
        this.c.setOnTouchListener(new j(this));
        this.d = (RelativeLayout) findViewById(R.id.ll_pubchat_page);
        this.e = findViewById(R.id.iv_scroll_bootom);
        this.i = new ChatListAdapter(this.a, this.k, list, this.b, str, str2, new k(this));
        this.i.setRoomType(this.k);
        this.c.setAdapter(this.i);
        this.c.setOnScrollListener(new l(this));
        this.e.setOnClickListener(new m(this));
        this.g = onScrollToBottomListener;
    }

    public void setChatPageTouchEvent(OnChatPageTouchEvent onChatPageTouchEvent) {
        this.m = onChatPageTouchEvent;
    }

    public void setRoomType(int i) {
        this.k = i;
        if (this.i != null) {
            this.i.setRoomType(i);
        }
    }

    public void setOnChatPageScrollListener(FullScreenChatPage$OnChatPageScrollListener fullScreenChatPage$OnChatPageScrollListener) {
        this.l = fullScreenChatPage$OnChatPageScrollListener;
    }

    public void setOnChatOnlickListener(OnChatOnlickListener onChatOnlickListener) {
        this.h = onChatOnlickListener;
    }

    public void setSelection() {
        if (this.c != null) {
            this.c.setSelection(this.c.getBottom());
        }
    }

    public void notifyAdapter() {
        this.i.notifyDataSetChanged();
    }

    public void setFansPageVisible(int i) {
        this.d.setVisibility(i);
    }

    public void onClick(View view) {
    }

    public void notifyAdapter(boolean z) {
        if (z) {
            if (System.currentTimeMillis() - this.j >= 1000) {
                this.j = System.currentTimeMillis();
            } else {
                return;
            }
        }
        notifyAdapter();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.m != null) {
            this.m.onTouch(motionEvent);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
