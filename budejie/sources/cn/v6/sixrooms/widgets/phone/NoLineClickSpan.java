package cn.v6.sixrooms.widgets.phone;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.listener.SetClickableSpanListener;

public class NoLineClickSpan extends ClickableSpan {
    private static final String a = NoLineClickSpan.class.getSimpleName();
    private UserInfoBean b;
    private RoommsgBean c;
    private SetClickableSpanListener d;
    private int e = 0;
    private UserInfoBean f;
    private int g = R.color.pad_room_anchor_name_tv_roomnum_color;
    public boolean showBgColor;

    public NoLineClickSpan(RoommsgBean roommsgBean, int i, SetClickableSpanListener setClickableSpanListener) {
        this.c = roommsgBean;
        this.e = i;
        this.d = setClickableSpanListener;
    }

    public NoLineClickSpan(UserInfoBean userInfoBean, SetClickableSpanListener setClickableSpanListener) {
        this.f = userInfoBean;
        this.d = setClickableSpanListener;
    }

    public NoLineClickSpan(RoommsgBean roommsgBean, int i, SetClickableSpanListener setClickableSpanListener, int i2) {
        this.g = i2;
        this.c = roommsgBean;
        this.e = i;
        this.d = setClickableSpanListener;
    }

    public NoLineClickSpan(UserInfoBean userInfoBean, SetClickableSpanListener setClickableSpanListener, int i) {
        this.g = i;
        this.f = userInfoBean;
        this.d = setClickableSpanListener;
    }

    public void onClick(View view) {
        ((TextView) view).setHighlightColor(view.getResources().getColor(17170445));
        CharSequence text = ((TextView) view).getText();
        int selectionStart = ((TextView) view).getSelectionStart();
        int selectionEnd = ((TextView) view).getSelectionEnd();
        if (selectionStart != -1 && selectionEnd != -1) {
            try {
                CharSequence subSequence = text.subSequence(selectionStart, selectionEnd);
                if (this.f != null) {
                    this.d.setClickableSpan(this.f, subSequence.toString());
                } else if (!"我".equals(subSequence.toString())) {
                    this.b = new UserInfoBean();
                    if (this.e == 0) {
                        this.b.setUid(this.c.getFid());
                        this.b.setUname(this.c.getFrom());
                        this.b.setUrid(this.c.getFrid());
                    } else if (1 == this.e) {
                        this.b.setUid(this.c.getToid());
                        this.b.setUname(this.c.getTo());
                        this.b.setUrid(this.c.getTorid());
                    }
                    this.d.setClickableSpan(this.b, subSequence.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(V6Coop.getInstance().getContext().getResources().getColor(this.g));
        textPaint.setUnderlineText(false);
    }
}
