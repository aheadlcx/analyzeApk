package cn.v6.sixrooms.room.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.ChangzhanFinalUserBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class SingWarFinalUserAdapter extends BaseAdapter {
    private LayoutInflater a;
    private List<ChangzhanFinalUserBean> b;
    private int c = 0;
    private NotifyDataListener d;

    public interface NotifyDataListener {
        void onClickTempToBeEnable();

        void onClickTempToBeOut();
    }

    static class a {
        TextView a;
        TextView b;
        TextView c;
        ImageView d;
        RelativeLayout e;
        SimpleDraweeView f;

        a() {
        }
    }

    public SingWarFinalUserAdapter(Context context, List<ChangzhanFinalUserBean> list, NotifyDataListener notifyDataListener) {
        this.a = LayoutInflater.from(context);
        this.b = list;
        this.d = notifyDataListener;
    }

    public void setSeclection(int i) {
        this.c = i;
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
        a aVar;
        if (view == null) {
            view = this.a.inflate(R.layout.singwar_final_banner_item, null);
            aVar = new a();
            aVar.e = (RelativeLayout) view.findViewById(R.id.item_bg);
            aVar.a = (TextView) view.findViewById(R.id.singwar_player_name);
            aVar.b = (TextView) view.findViewById(R.id.singwar_final_ticket);
            aVar.c = (TextView) view.findViewById(R.id.singwar_final_rank);
            aVar.f = (SimpleDraweeView) view.findViewById(R.id.iv_avatar);
            aVar.d = (ImageView) view.findViewById(R.id.singwar_final_nopass);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (this.c == i) {
            aVar.e.setBackgroundResource(R.drawable.singwar_final_item_checked_bg);
        } else {
            aVar.e.setBackgroundColor(0);
        }
        aVar.a.setText(((ChangzhanFinalUserBean) this.b.get(i)).getAlias());
        aVar.b.setText(((ChangzhanFinalUserBean) this.b.get(i)).getNum() + " 票");
        aVar.c.setText(String.valueOf(i + 1));
        aVar.f.setImageURI(Uri.parse(((ChangzhanFinalUserBean) this.b.get(i)).getPic()));
        if ("1".equals(((ChangzhanFinalUserBean) this.b.get(i)).getOut())) {
            aVar.d.setVisibility(0);
            if (this.c == i) {
                this.d.onClickTempToBeOut();
            }
        } else {
            aVar.d.setVisibility(8);
            if (this.c == i) {
                this.d.onClickTempToBeEnable();
            }
        }
        return view;
    }
}
