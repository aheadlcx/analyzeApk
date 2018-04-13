package cn.v6.sixrooms.room.chat;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.listener.SetClickableSpanListener;
import cn.v6.sixrooms.room.bean.OnHeadlineBean;
import cn.v6.sixrooms.room.bean.OnHeadlineBeans;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.BecomeGodTextUtils;
import cn.v6.sixrooms.widgets.phone.NoLineClickSpan;

public class OnHeadLineChatStyle implements IChatStyle {
    private SetClickableSpanListener a;

    public OnHeadLineChatStyle(SetClickableSpanListener setClickableSpanListener) {
        this.a = setClickableSpanListener;
    }

    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        OnHeadlineBeans headlineBeans = roommsgBean.getHeadlineBeans();
        Object stringBuilder = new StringBuilder(BecomeGodTextUtils.s1).append(((OnHeadlineBean) headlineBeans.getTop8().get(0)).getAlias()).append("、").append(((OnHeadlineBean) headlineBeans.getTop8().get(1)).getAlias()).append("、").append(((OnHeadlineBean) headlineBeans.getTop8().get(2)).getAlias()).append("上头条了！").toString();
        CharSequence spannableStringBuilder = new SpannableStringBuilder(stringBuilder);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(draweeTextView.getResources().getColor(R.color.white)), 0, spannableStringBuilder.toString().length(), 33);
        for (int i = 0; i < 3; i++) {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUid(((OnHeadlineBean) headlineBeans.getTop8().get(i)).getUid());
            userInfoBean.setUname(((OnHeadlineBean) headlineBeans.getTop8().get(i)).getAlias());
            userInfoBean.setUrid(((OnHeadlineBean) headlineBeans.getTop8().get(i)).getRid());
            int indexOf = stringBuilder.indexOf(((OnHeadlineBean) headlineBeans.getTop8().get(i)).getAlias());
            spannableStringBuilder.setSpan(new NoLineClickSpan(userInfoBean, this.a, R.color.full_chat_name), indexOf, ((OnHeadlineBean) headlineBeans.getTop8().get(i)).getAlias().length() + indexOf, 0);
        }
        draweeTextView.setText(spannableStringBuilder);
    }
}
