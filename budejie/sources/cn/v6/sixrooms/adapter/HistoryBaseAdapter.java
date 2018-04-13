package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pojo.HistroyWatch;
import cn.v6.sixrooms.utils.DrawableResourceUtils;
import cn.v6.sixrooms.utils.phone.HistoryDbTool;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.a;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryBaseAdapter extends BaseAdapter implements SectionIndexer, StickyListHeadersAdapter {
    private String[] a;
    private LayoutInflater b;
    private Context c;
    private List<HistroyWatch> d;
    private boolean e = false;
    private CallBack f;

    public interface CallBack {
        void delete();
    }

    public HistoryBaseAdapter(Context context, CallBack callBack) {
        this.c = context;
        this.f = callBack;
        this.b = LayoutInflater.from(context);
        setRes();
    }

    public int getCount() {
        return this.a.length;
    }

    public HistroyWatch getItem(int i) {
        if (this.d == null || this.d.isEmpty()) {
            return null;
        }
        return (HistroyWatch) this.d.get(i);
    }

    public long getItemId(int i) {
        if (this.d != null) {
            return Long.parseLong(((HistroyWatch) this.d.get(i)).getRid());
        }
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        HistoryBaseAdapter$b historyBaseAdapter$b = new HistoryBaseAdapter$b(this);
        View inflate = this.b.inflate(R.layout.lv_sticky_history_item_layout, viewGroup, false);
        historyBaseAdapter$b.c = (SimpleDraweeView) inflate.findViewById(R.id.iv_lv_sticky_history_item_pic);
        historyBaseAdapter$b.a = (TextView) inflate.findViewById(R.id.tv_lv_sticky_history_item_name);
        historyBaseAdapter$b.b = (ImageView) inflate.findViewById(R.id.iv_lv_sticky_history_item_level);
        historyBaseAdapter$b.d = (LinearLayout) inflate.findViewById(R.id.ll_lv_sticky_history_item);
        historyBaseAdapter$b.e = (LinearLayout) inflate.findViewById(R.id.ll_lv_sticky_history_item_delete);
        inflate.setTag(historyBaseAdapter$b);
        if (this.d.size() > i) {
            int parseInt;
            historyBaseAdapter$b.a.setText(((HistroyWatch) this.d.get(i)).getUsername());
            if (((HistroyWatch) this.d.get(i)).getLevel() != null) {
                parseInt = Integer.parseInt(((HistroyWatch) this.d.get(i)).getLevel());
            } else {
                parseInt = -1;
            }
            parseInt = DrawableResourceUtils.getStarLevelImageResource(parseInt);
            if (parseInt != -1) {
                historyBaseAdapter$b.b.setBackgroundResource(parseInt);
            }
            historyBaseAdapter$b.c.setImageURI(((HistroyWatch) this.d.get(i)).getPic());
            historyBaseAdapter$b.e.setOnClickListener(new e(this, i));
        }
        return inflate;
    }

    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        HistoryBaseAdapter$a historyBaseAdapter$a;
        if (view == null) {
            HistoryBaseAdapter$a historyBaseAdapter$a2 = new HistoryBaseAdapter$a(this);
            view = this.b.inflate(R.layout.lv_sticky_history_item_header, viewGroup, false);
            historyBaseAdapter$a2.a = (TextView) view.findViewById(R.id.tv_history_title);
            view.setTag(historyBaseAdapter$a2);
            historyBaseAdapter$a = historyBaseAdapter$a2;
        } else {
            historyBaseAdapter$a = (HistoryBaseAdapter$a) view.getTag();
        }
        if (this.a.length > i) {
            String substring = this.a[i].substring(0, 1);
            if (substring.equals("A")) {
                historyBaseAdapter$a.a.setText("今天");
            } else if (substring.equals("B")) {
                historyBaseAdapter$a.a.setText("昨天");
            } else {
                historyBaseAdapter$a.a.setText("更早");
            }
        }
        return view;
    }

    public long getHeaderId(int i) {
        return (long) this.a[i].subSequence(0, 1).charAt(0);
    }

    public int getPositionForSection(int i) {
        return i;
    }

    public int getSectionForPosition(int i) {
        return i;
    }

    public Object[] getSections() {
        return null;
    }

    public void setFlag(boolean z) {
        this.e = z;
    }

    public void setRes() {
        this.d = HistoryDbTool.query(this.c);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long time = simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime();
            if (this.d == null || this.d.size() == 0) {
                this.a = new String[0];
                return;
            }
            this.a = new String[this.d.size()];
            for (int size = this.d.size() - 1; size >= 0; size--) {
                this.a[size] = "A" + ((HistroyWatch) this.d.get(size)).getUsername();
                if (((HistroyWatch) this.d.get(size)).getDate() > time) {
                    this.a[size] = "A" + ((HistroyWatch) this.d.get(size)).getUsername();
                } else if (((HistroyWatch) this.d.get(size)).getDate() > time - a.i) {
                    this.a[size] = "B" + ((HistroyWatch) this.d.get(size)).getUsername();
                } else {
                    this.a[size] = "C" + ((HistroyWatch) this.d.get(size)).getUsername();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
