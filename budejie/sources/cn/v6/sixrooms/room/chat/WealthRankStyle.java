package cn.v6.sixrooms.room.chat;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.room.view.DraweeSpan.Builder;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;
import cn.v6.sixrooms.surfaceanim.specialenterframe.FortuneUtils;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.DrawableResourceUtils;
import cn.v6.sixrooms.utils.Html2Text;
import cn.v6.sixrooms.widgets.phone.ImageSpanCenter;

public class WealthRankStyle implements IChatStyle {
    private Context a;

    public WealthRankStyle(Context context) {
        this.a = context;
    }

    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        String Html2Text = Html2Text.Html2Text(roommsgBean.getContent());
        CharSequence spannableStringBuilder = new SpannableStringBuilder(Html2Text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(this.a.getResources().getColor(R.color.white));
        if (roommsgBean.isRankFlag()) {
            int rank = roommsgBean.getRank();
            Object obj = null;
            if (rank < 27 && rank > 0) {
                obj = new ImageSpanCenter(this.a, FortuneUtils.getFortuneLevelUrl(String.valueOf(roommsgBean.getRank())));
            } else if (rank >= 27) {
                obj = new Builder(DrawableResourceUtils.getCustomWealthRankImg(roommsgBean.getFid())).setLayout(DensityUtil.dip2px(36.0f), DensityUtil.dip2px(11.0f)).setPlaceHolderImage(this.a.getResources().getDrawable(R.drawable.custom_wealth_default)).build();
            }
            if (obj != null) {
                spannableStringBuilder.insert(Html2Text.indexOf("距") + 1, " *");
                rank = spannableStringBuilder.toString().indexOf("*");
                spannableStringBuilder.setSpan(obj, rank, rank + 1, 17);
            }
        }
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, Html2Text.length(), 33);
        draweeTextView.setText(spannableStringBuilder);
    }
}
