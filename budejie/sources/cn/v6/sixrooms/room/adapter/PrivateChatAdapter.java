package cn.v6.sixrooms.room.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.utils.Html2Text;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.V6StringUtils;
import cn.v6.sixrooms.utils.phone.PhoneSmileyParser;
import java.util.List;

public class PrivateChatAdapter extends BaseAdapter {
    private Context a;
    private List<RoommsgBean> b;
    private ForegroundColorSpan c = new ForegroundColorSpan(Color.parseColor("#64cbc0"));
    private PhoneSmileyParser d = PhoneSmileyParser.getInstance(V6Coop.getInstance().getContext());

    static class a {
        TextView a;
        TextView b;

        a() {
        }
    }

    public PrivateChatAdapter(Context context) {
        this.a = context;
    }

    public void setData(List<RoommsgBean> list) {
        this.b = list;
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a();
            view = View.inflate(this.a, R.layout.adapter_private_chat_item, null);
            aVar.a = (TextView) view.findViewById(R.id.tv_from);
            aVar.b = (TextView) view.findViewById(R.id.tv_content);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (this.b != null && this.b.size() > 0) {
            CharSequence spannableStringBuilder;
            RoommsgBean roommsgBean = (RoommsgBean) this.b.get(i);
            String removeSpecialCharacter = V6StringUtils.removeSpecialCharacter(roommsgBean.getFrom());
            String removeSpecialCharacter2 = V6StringUtils.removeSpecialCharacter(LoginUtils.getLoginUserBean().getAlias());
            if (removeSpecialCharacter2.equals(removeSpecialCharacter)) {
                if ("我".equals(roommsgBean.getTo())) {
                    roommsgBean.setTo(removeSpecialCharacter2);
                    roommsgBean.setTorid(roommsgBean.getFrid());
                    roommsgBean.setToid(roommsgBean.getFid());
                }
                spannableStringBuilder = new SpannableStringBuilder("我对" + roommsgBean.getTo() + "(" + roommsgBean.getTorid() + ")说");
                spannableStringBuilder.setSpan(this.c, 2, roommsgBean.getTo().length() + 2, 33);
            } else {
                spannableStringBuilder = new SpannableStringBuilder(roommsgBean.getFrom() + "(" + roommsgBean.getFrid() + ")");
                spannableStringBuilder.setSpan(this.c, 0, roommsgBean.getFrom().length(), 33);
            }
            aVar.a.setText(spannableStringBuilder);
            aVar.b.setText(new SpannableStringBuilder(this.d.addSmileySpans(Html2Text.Html2Text(roommsgBean.getContent()), null, null)));
        }
        return view;
    }
}
