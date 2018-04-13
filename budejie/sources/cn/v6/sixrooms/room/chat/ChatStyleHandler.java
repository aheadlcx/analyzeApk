package cn.v6.sixrooms.room.chat;

import android.util.SparseArray;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;
import java.util.ArrayList;
import java.util.List;

public abstract class ChatStyleHandler {
    private List<IChatStyle> a = new ArrayList(3);
    private SparseArray<IChatStyle> b = new SparseArray();

    public abstract void processBean(DraweeTextView draweeTextView, RoommsgBean roommsgBean);

    public void handleStyle(int i, RoommsgBean roommsgBean, DraweeTextView draweeTextView, boolean z) {
        if (z) {
            for (IChatStyle a : this.a) {
                a(roommsgBean, draweeTextView, a);
            }
        }
        a(roommsgBean, draweeTextView, (IChatStyle) this.b.get(i));
    }

    public void handleStyle(int i, RoommsgBean roommsgBean, DraweeTextView draweeTextView) {
        handleStyle(i, roommsgBean, draweeTextView, true);
    }

    public void registerChatStyle(int i, IChatStyle iChatStyle) {
        if (iChatStyle != null) {
            this.b.put(i, iChatStyle);
        }
    }

    public void registerChatStyle(IChatStyle iChatStyle) {
        if (iChatStyle != null) {
            this.a.add(iChatStyle);
        }
    }

    public void unRegisterChatStyle(int i) {
        this.b.remove(i);
    }

    public void unRegisterChatStyle(IChatStyle iChatStyle) {
        if (iChatStyle != null) {
            this.a.remove(iChatStyle);
        }
    }

    private void a(RoommsgBean roommsgBean, DraweeTextView draweeTextView, IChatStyle iChatStyle) {
        if (iChatStyle instanceof IChatStyleDependOn) {
            List<Integer> dependOnList = ((IChatStyleDependOn) iChatStyle).getDependOnList();
            if (dependOnList != null) {
                for (Integer intValue : dependOnList) {
                    handleStyle(intValue.intValue(), roommsgBean, draweeTextView, false);
                }
            }
        }
        if (iChatStyle != null) {
            iChatStyle.onStyle(draweeTextView, roommsgBean);
        }
    }
}
